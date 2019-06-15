package com.franchini.parser;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.franchini.datamodel.Item;
import com.franchini.datamodel.ShoppingCart;
import com.franchini.datamodel.ShoppingCartItem;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import org.junit.Test;

public class DefaultShoppingCartParserTest {

  private DefaultShoppingCartParser sut = new DefaultShoppingCartParser();

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

  private InputStream loadFileAsInputStream(String name) throws IOException {
    String path = "input" + File.separator + name;
    InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
    if (stream == null) {
      throw new IOException("File: '" + path + "' cannot be found");
    }
    return stream;
  }
}