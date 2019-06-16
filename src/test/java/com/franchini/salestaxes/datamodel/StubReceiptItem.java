package com.franchini.salestaxes.datamodel;

import java.math.BigDecimal;

public class StubReceiptItem implements ReceiptItem {

  private final String itemDesc;

  public StubReceiptItem(String itemDesc) {
    this.itemDesc = itemDesc;
  }

  @Override
  public int getQuantity() {
    return 1;
  }

  @Override
  public Item getItem() {
    return Item.newItem(itemDesc);
  }

  @Override
  public BigDecimal getPrice() {
    return new BigDecimal("10.05");
  }

  @Override
  public BigDecimal getTax() {
    return new BigDecimal("0.95");
  }

  @Override
  public BigDecimal getTotalPrice() {
    return new BigDecimal("11.00");
  }
}
