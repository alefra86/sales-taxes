package com.franchini.salestaxes.parser;

import com.franchini.salestaxes.datamodel.Item;
import com.franchini.salestaxes.datamodel.ShoppingCart;
import com.franchini.salestaxes.datamodel.ShoppingCartItem;
import com.franchini.salestaxes.repository.CategoryRepository;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Default implementation that read each line of the input stream, extract the items and checks if they are imported and belong to
 * any of recognized categories.<br>
 * <br>
 * Example of input data:
 * <pre>
 * 1 imported bottle of perfume at 27.99
 * 1 bottle of perfume at 18.99
 * 1 packet of headache pills at 9.75
 * 1 box of imported chocolates at 11.25
 * </pre>
 */
public class DefaultShoppingCartParser implements ShoppingCartParser {

  private final static Pattern PATTERN = Pattern.compile("^(\\d+) (.+) at (\\S+)$");
  private final CategoryRepository categoryRepository;

  public DefaultShoppingCartParser(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public ShoppingCart parse(InputStream inputStream) {
    List<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.toList());
    if (lines.isEmpty()) {
      throw new IllegalArgumentException("Empty shopping cart. Please provide a correct one.");
    }
    ShoppingCart shoppingCart = new ShoppingCart();
    lines.forEach(line -> {
      Matcher matcher = PATTERN.matcher(line);
      if (!matcher.matches()) {
        throw new IllegalArgumentException("Wrong content. Please provide a correct one.");
      }
      int quantity = Integer.valueOf(matcher.group(1));
      Item item = getItem(matcher.group(2));
      BigDecimal price = new BigDecimal(matcher.group(3));
      shoppingCart.addItem(ShoppingCartItem.of(quantity, item, price));
    });
    return shoppingCart;
  }

  private Item getItem(String itemDesc) {
    boolean isImported = itemDesc.contains(Item.IMPORTED_LABEL);
    String desc = checkAndClearDesc(itemDesc);
    return isImported ? Item.newImportedItem(desc) : Item.newItem(desc);
  }

  private String checkAndClearDesc(String itemDesc) {
    itemDesc = itemDesc.replace(Item.IMPORTED_LABEL + " ", "").trim();
    if (categoryRepository.findByProductName(itemDesc) == null) {
      throw new IllegalArgumentException(String.format("No Category found for %s", itemDesc));
    }
    return itemDesc;
  }
}
