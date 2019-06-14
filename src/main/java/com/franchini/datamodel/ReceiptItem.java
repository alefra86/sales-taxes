package com.franchini.datamodel;

import java.math.BigDecimal;

public class ReceiptItem {

  private final ShoppingCartItem shoppingCartItem;

  private ReceiptItem(ShoppingCartItem shoppingCartItem) {
    this.shoppingCartItem = shoppingCartItem;
  }

  public static ReceiptItem from(ShoppingCartItem shoppingCartItem) {
    return new ReceiptItem(shoppingCartItem);
  }

  public BigDecimal getTax() {
    return BigDecimal.ZERO;
  }

  public BigDecimal getTotalPrice() {
    return shoppingCartItem.getPrice();
  }
}
