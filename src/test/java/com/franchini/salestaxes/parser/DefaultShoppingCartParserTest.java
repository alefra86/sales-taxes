package com.franchini.salestaxes.parser;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.franchini.salestaxes.datamodel.Item;
import com.franchini.salestaxes.datamodel.ShoppingCart;
import com.franchini.salestaxes.datamodel.ShoppingCartItem;
import com.franchini.salestaxes.repository.StubCategoryRepository;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import org.junit.Test;

public class DefaultShoppingCartParserTest {

  private DefaultShoppingCartParser sut = new DefaultShoppingCartParser(new StubCategoryRepository());

  @Test(expected = IllegalArgumentException.class)
  public void emptyStreamReturnsException() throws IOException {
    sut.parse(loadFileAsInputStream("empty.txt"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongContentReturnsException() throws IOException {
    sut.parse(loadFileAsInputStream("wrong.txt"));
  }

  @Test
  public void correctContentReturnsShoppingCart() throws IOException {
    ShoppingCart shoppingCart = sut.parse(loadFileAsInputStream("correct.txt"));
    assertEquals(1, shoppingCart.size());
    assertThat(shoppingCart.getItems().get(0),
      samePropertyValuesAs(ShoppingCartItem.of(1, Item.newItem("bottle of perfume"), new BigDecimal("27.99"))));
  }

  @Test
  public void importedContentCreatedCorrectItems() throws IOException {
    ShoppingCart shoppingCart = sut.parse(loadFileAsInputStream("imported.txt"));
    assertEquals(2, shoppingCart.size());
    assertThat(shoppingCart.getItems(),
      contains(samePropertyValuesAs(ShoppingCartItem.of(1, Item.newImportedItem("bottle of perfume"), new BigDecimal("27.99"))),
        samePropertyValuesAs(ShoppingCartItem.of(1, Item.newImportedItem("box of chocolates"), new BigDecimal("11.25")))));
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongCategoryReturnsException() throws IOException {
    sut.parse(loadFileAsInputStream("wrong-category.txt"));
  }

  @Test
  public void testInput1() throws IOException {
    ShoppingCart shoppingCart = sut.parse(loadFileAsInputStream("input1.txt"));
    assertEquals(3, shoppingCart.size());
    assertThat(shoppingCart.getItems(),
      contains(samePropertyValuesAs(ShoppingCartItem.of(1, Item.newItem("book"), new BigDecimal("12.49"))),
        samePropertyValuesAs(ShoppingCartItem.of(1, Item.newItem("music CD"), new BigDecimal("14.99"))),
        samePropertyValuesAs(ShoppingCartItem.of(1, Item.newItem("chocolate bar"), new BigDecimal("0.85")))));
  }

  @Test
  public void testInput2() throws IOException {
    ShoppingCart shoppingCart = sut.parse(loadFileAsInputStream("input2.txt"));
    assertEquals(2, shoppingCart.size());
    assertThat(shoppingCart.getItems(),
      contains(samePropertyValuesAs(ShoppingCartItem.of(1, Item.newImportedItem("box of chocolates"), new BigDecimal("10.00"))),
        samePropertyValuesAs(ShoppingCartItem.of(1, Item.newImportedItem("bottle of perfume"), new BigDecimal("47.50")))));
  }

  @Test
  public void testInput3() throws IOException {
    ShoppingCart shoppingCart = sut.parse(loadFileAsInputStream("input3.txt"));
    assertEquals(4, shoppingCart.size());
    assertThat(shoppingCart.getItems(),
      contains(samePropertyValuesAs(ShoppingCartItem.of(1, Item.newImportedItem("bottle of perfume"), new BigDecimal("27.99"))),
        samePropertyValuesAs(ShoppingCartItem.of(1, Item.newItem("bottle of perfume"), new BigDecimal("18.99"))),
        samePropertyValuesAs(ShoppingCartItem.of(1, Item.newItem("packet of headache pills"), new BigDecimal("9.75"))),
        samePropertyValuesAs(ShoppingCartItem.of(1, Item.newImportedItem("box of chocolates"), new BigDecimal("11.25")))));
  }

  private InputStream loadFileAsInputStream(String name) throws IOException {
    String path = "input" + File.separator + name;
    InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
    if (stream == null) {
      throw new IOException("File: '" + path + "' cannot be found");
    }
    return stream;
  }
}