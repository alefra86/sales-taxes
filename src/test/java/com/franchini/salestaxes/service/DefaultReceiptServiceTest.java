package com.franchini.salestaxes.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.franchini.salestaxes.datamodel.Item;
import com.franchini.salestaxes.datamodel.Receipt;
import com.franchini.salestaxes.datamodel.ReceiptItemFactory;
import com.franchini.salestaxes.datamodel.ShoppingCart;
import com.franchini.salestaxes.datamodel.ShoppingCartItem;
import com.franchini.salestaxes.datamodel.StubReceiptItem;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultReceiptServiceTest {

  public static final String BOOK = "book";
  public static final String MUSIC_CD = "music CD";
  @InjectMocks
  private DefaultReceiptService sut;
  @Mock
  private ReceiptItemFactory receiptItemFactory;

  @Before
  public void setUp() {
    when(receiptItemFactory.getReceiptItem(any(ShoppingCartItem.class))).thenReturn(new StubReceiptItem("ITEM"));
  }

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
  public void testReceiptItemAreGeneratedThroughTheFactoryAndAddedToTheReceipt() {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newItem(BOOK), BigDecimal.ZERO));
    shoppingCart.addItem(ShoppingCartItem.of(1, Item.newItem(MUSIC_CD), BigDecimal.ZERO));
    Receipt receipt = sut.createReceipt(shoppingCart);
    assertEquals(2, receipt.size());
    Mockito.verify(receiptItemFactory, times(2)).getReceiptItem(any(ShoppingCartItem.class));
  }

}
