package com.franchini;

import static org.junit.Assert.assertEquals;

import com.franchini.datamodel.Item;
import com.franchini.datamodel.Receipt;
import com.franchini.datamodel.ShoppingCart;
import com.franchini.datamodel.ShoppingCartItem;
import java.math.BigDecimal;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SalesTaxesTest {

  public static final String BASICTAX_FREE_ITEM = "book";
  public static final String TAXED_ITEM = "music CD";
  public static final String IMPORTED_BASIC_TAX_FREE_ITEM = "imported box of chocolates";
  public static final String IMPORTED_TAXED_ITEM = "imported bottle of parfume";

  private SalesTaxes sut = new SalesTaxes();

  @Test(expected = IllegalArgumentException.class)
  public void nullShoppingCartReturnsThrowException() {
    sut.createReceipt(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyShoppingCartReturnsThrowException() {
    sut.createReceipt(null);
  }

  @Test
  public void receiveItemsSizeIsEqualToShoppingCartSize() {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newItem(BASICTAX_FREE_ITEM), BigDecimal.ZERO));
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newItem(TAXED_ITEM), BigDecimal.ZERO));
    Receipt receipt = sut.createReceipt(shoppingCart);
    assertEquals(shoppingCart.size(), receipt.size());
  }

  @Test
  public void testItemsWithoutTaxesApplied() {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newItem(BASICTAX_FREE_ITEM), BigDecimal.ONE));
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newItem(TAXED_ITEM), BigDecimal.ONE));
    Receipt receipt = sut.createReceipt(shoppingCart);
    assertEquals(new BigDecimal("2"), receipt.getTotal());
  }

  @Test
  public void testImportedExemptItems() {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newImportedItem(IMPORTED_BASIC_TAX_FREE_ITEM), new BigDecimal("11.25")));
    Receipt receipt = sut.createReceipt(shoppingCart);
    assertEquals(new BigDecimal("11.85"), receipt.getTotal());
  }
}
