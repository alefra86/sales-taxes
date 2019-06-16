package com.franchini.salestaxes.datamodel;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class TaxedReceiptItemDecorator implements ReceiptItem {

  public static final String ROUNDING_VALUE = "0.05";
  private final ReceiptItem receiptItem;
  private BigDecimal tax;
  private BigDecimal totalPrice;

  TaxedReceiptItemDecorator(ReceiptItem receiptItem) {
    this.receiptItem = receiptItem;
  }

  @Override
  public int getQuantity() {
    return receiptItem.getQuantity();
  }

  @Override
  public Item getItem() {
    return receiptItem.getItem();
  }

  @Override
  public BigDecimal getPrice() {
    return receiptItem.getPrice();
  }

  @Override
  public BigDecimal getTax() {
    if (tax == null) {
      tax = round(getPrice().multiply(getTaxRate()).divide(new BigDecimal("100"))).add(receiptItem.getTax());
    }
    return tax;
  }

  @Override
  public BigDecimal getTotalPrice() {
    if (totalPrice == null) {
      totalPrice = getPrice().add(getTax());
    }
    return totalPrice;
  }

  protected abstract BigDecimal getTaxRate();

  private BigDecimal round(BigDecimal numberToRound) {
    return numberToRound.divide(new BigDecimal(ROUNDING_VALUE), 0, RoundingMode.UP).multiply(new BigDecimal(ROUNDING_VALUE));
  }
}
