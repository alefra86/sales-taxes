package com.franchini.datamodel;

import java.math.BigDecimal;
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

  public BigDecimal getTaxes() {
    return receiptItems.stream().map(ReceiptItem::getTax).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal getTotal() {
    return receiptItems.stream().map(ReceiptItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    receiptItems.forEach(i -> {
      stringBuilder.append(i.getQuantity()).append(" ").append(i.getItem()).append(": ").append(i.getTotalPrice()).append("\n");
    });
    stringBuilder.append("Sales Taxes: ").append(getTaxes()).append("\n");
    stringBuilder.append("Total: ").append(getTotal());
    return stringBuilder.toString();
  }
}
