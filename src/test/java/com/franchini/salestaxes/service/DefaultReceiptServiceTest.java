package com.franchini.salestaxes.service;

import static org.junit.Assert.assertEquals;

import com.franchini.salestaxes.datamodel.DefaultReceiptItemFactory;
import com.franchini.salestaxes.datamodel.Item;
import com.franchini.salestaxes.datamodel.Receipt;
import com.franchini.salestaxes.datamodel.ShoppingCart;
import com.franchini.salestaxes.datamodel.ShoppingCartItem;
import com.franchini.salestaxes.repository.StubCategoryRepository;
import java.math.BigDecimal;
import org.junit.Test;

public class DefaultReceiptServiceTest {

  public static final String BASICTAX_FREE_ITEM = "book";
  public static final String TAXED_ITEM = "music CD";

  private DefaultReceiptService sut = new DefaultReceiptService(new DefaultReceiptItemFactory(new StubCategoryRepository()));

  @Test(expected = IllegalArgumentException.class)
  public void nullShoppingCartThrowsException() {
    sut.createReceipt(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyShoppingCartThrowsException() {
    sut.createReceipt(new ShoppingCart());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shoppingCartWithUnnkownCategoryThrowsException() {
    sut.createReceipt(new ShoppingCart());
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
    Receipt receipt = sut.createReceipt(shoppingCart);
    assertEquals(BigDecimal.ONE, receipt.getTotal());
  }

  @Test
  public void testImportedExemptItems() {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newImportedItem(BASICTAX_FREE_ITEM), new BigDecimal("11.25")));
    Receipt receipt = sut.createReceipt(shoppingCart);
    assertEquals(new BigDecimal("11.85"), receipt.getTotal());
  }

  @Test
  public void testBasicSalesTaxItems() {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newItem(TAXED_ITEM), new BigDecimal("18.99")));
    Receipt receipt = sut.createReceipt(shoppingCart);
    assertEquals(new BigDecimal("20.89"), receipt.getTotal());
  }

  @Test
  public void testImportedItemsNotExempt() {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newImportedItem(TAXED_ITEM), new BigDecimal("47.50")));
    Receipt receipt = sut.createReceipt(shoppingCart);
    assertEquals(new BigDecimal("54.65"), receipt.getTotal());
  }
}
