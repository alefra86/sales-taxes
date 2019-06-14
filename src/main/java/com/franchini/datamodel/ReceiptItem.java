package com.franchini.datamodel;

import java.math.BigDecimal;

public class ReceiptItem {

  private final ShoppingBasketItem shoppingBasketItem;

  private ReceiptItem(ShoppingBasketItem shoppingBasketItem) {
    this.shoppingBasketItem = shoppingBasketItem;
  }

  public static ReceiptItem from(ShoppingBasketItem shoppingBasketItem) {
    return new ReceiptItem(shoppingBasketItem);
  }

  public BigDecimal getTax() {
    return BigDecimal.ZERO;
  }

}
