package com.franchini.datamodel;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

  private List<Purchase> purchases;
  private BigDecimal salesTaxes;
  private BigDecimal total;

  public void addPurchase(Purchase purchase) {
    purchases.add(purchase);
    total = total.add(purchase.getPrice());
  }

}
