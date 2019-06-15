package com.franchini.datamodel;

import java.math.BigDecimal;

public class ImportedReceiptItem extends TaxedReceiptItemDecorator {

  public ImportedReceiptItem(ReceiptItem receiptItem) {
    super(receiptItem);
  }

  @Override
  protected BigDecimal getTaxRate() {
    return new BigDecimal("5");
  }
}
