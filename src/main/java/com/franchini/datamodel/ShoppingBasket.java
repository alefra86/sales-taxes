package com.franchini.datamodel;

import java.util.Collections;
import java.util.List;

/**
 *
 */
public class ShoppingBasket {

  private List<Purchase> purchases;

  public void addPurchase(Purchase purchase) {
    purchases.add(purchase);
  }

  public List<Purchase> getPurchases() {
    return Collections.unmodifiableList(purchases);
  }
}
