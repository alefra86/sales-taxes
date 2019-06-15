package com.franchini.datamodel;

import java.math.BigDecimal;

public class TaxFreeReceiptItem implements ReceiptItem {

  protected final ShoppingCartItem shoppingCartItem;

  public TaxFreeReceiptItem(ShoppingCartItem shoppingCartItem) {
    this.shoppingCartItem = shoppingCartItem;
  }

  @Override
  public BigDecimal getPrice() {
    return shoppingCartItem.getPrice();
  }

  @Override
  public BigDecimal getTax() {
    return BigDecimal.ZERO;
  }

  @Override
  public BigDecimal getTotalPrice() {
    return getPrice().add(getTax());
  }
}
