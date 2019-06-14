package com.franchini.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

  private List<ReceiptItem> receiptItems = new ArrayList<>();

  public void addReceiptItem(ReceiptItem receiptItem) {
    receiptItems.add(receiptItem);
  }

  public int size() {
    return receiptItems.size();
  }

}
