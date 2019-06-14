package com.franchini.datamodel;

import java.util.List;

public class Receipt {

  private List<ReceiptItem> receiptItems;

  public void addReceiptItem(ReceiptItem receiptItem) {
    receiptItems.add(receiptItem);
  }

}
