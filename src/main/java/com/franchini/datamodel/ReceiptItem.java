package com.franchini.datamodel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class ReceiptItem {

  private final ShoppingCartItem shoppingCartItem;
  private final Map<String, Category> itemsCategory = new HashMap<>();

  {
    {
      itemsCategory.put("book", Category.of("books", true));
      itemsCategory.put("music CD", Category.of("music", false));
      itemsCategory.put("chocolade bar", Category.of("food", true));
      itemsCategory.put("box of chocolates", Category.of("food", true));
      itemsCategory.put("bootle of perfume", Category.of("cosmetics", false));
      itemsCategory.put("packet of headache pills", Category.of("medical products", true));
    }
  }

  private ReceiptItem(ShoppingCartItem shoppingCartItem) {
    this.shoppingCartItem = shoppingCartItem;
  }

  public static ReceiptItem from(ShoppingCartItem shoppingCartItem) {
    return new ReceiptItem(shoppingCartItem);
  }

  public BigDecimal getTax() {
    BigDecimal tax = BigDecimal.ZERO;
    if (shoppingCartItem.getItem().isImported()) {
      tax = tax.add(round(shoppingCartItem.getPrice().multiply(new BigDecimal("5").divide(new BigDecimal("100")))));
    }
    if (!itemsCategory.get(shoppingCartItem.getItem().getDesc()).isExempt()) {
      tax = tax.add(round(shoppingCartItem.getPrice().multiply(new BigDecimal("10").divide(new BigDecimal("100")))));
    }
    return tax;
  }

  private BigDecimal round(BigDecimal numberToRound) {
    return numberToRound.divide(new BigDecimal("0.05"), 0, RoundingMode.UP).multiply(new BigDecimal("0.05"));
  }

  public BigDecimal getTotalPrice() {
    return shoppingCartItem.getPrice().add(getTax());
  }
}
