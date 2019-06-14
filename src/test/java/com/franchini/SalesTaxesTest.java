package com.franchini;

import com.franchini.datamodel.Item;
import com.franchini.datamodel.Receipt;
import com.franchini.datamodel.ShoppingCart;
import com.franchini.datamodel.ShoppingCartItem;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SalesTaxesTest {

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
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newItem("item1"), BigDecimal.ZERO));
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newItem("item2"), BigDecimal.ZERO));
    Receipt receipt = sut.createReceipt(shoppingCart);
    Assert.assertEquals(shoppingCart.size(), receipt.size());
  }

  @Test
  public void itemsWithoutTaxesReturnsTheSumOfTheirPrices() {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newItem("item1"), BigDecimal.ONE));
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newItem("item2"), BigDecimal.ONE));
    Receipt receipt = sut.createReceipt(shoppingCart);
    Assert.assertEquals(new BigDecimal("2"), receipt.getTotal());
  }
}
