package com.franchini.salestaxes.datamodel;

import java.math.BigDecimal;

public class BasicTaxReceiptItem extends TaxedReceiptItemDecorator {

  public BasicTaxReceiptItem(ReceiptItem receiptItem) {
    super(receiptItem);
  }

  @Override
  protected BigDecimal getTaxRate() {
    return new BigDecimal("10");
  }
}
