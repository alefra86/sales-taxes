package com.franchini.datamodel;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReceiptItem {

  private final ShoppingCartItem shoppingCartItem;

  private ReceiptItem(ShoppingCartItem shoppingCartItem) {
    this.shoppingCartItem = shoppingCartItem;
  }

  public static ReceiptItem from(ShoppingCartItem shoppingCartItem) {
    return new ReceiptItem(shoppingCartItem);
  }

  public BigDecimal getTax() {
    if (shoppingCartItem.getItem().isImported()) {
      return round(shoppingCartItem.getPrice().multiply(new BigDecimal("5").divide(new BigDecimal("100"))));
    }
    return BigDecimal.ZERO;
  }

  private BigDecimal round(BigDecimal numberToRound) {
    return numberToRound.divide(new BigDecimal("0.05"), 0, RoundingMode.UP).multiply(new BigDecimal("0.05"));
  }

  public BigDecimal getTotalPrice() {
    return shoppingCartItem.getPrice().add(getTax());
  }
}
