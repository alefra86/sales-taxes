package com.franchini;

import com.franchini.datamodel.Receipt;
import com.franchini.datamodel.ShoppingBasket;

/**
 * Service for building a receipt
 */
public class SalesTaxes {

  public Receipt getReceipt(ShoppingBasket shoppingBasket) {
    Receipt receipt = new Receipt();
    shoppingBasket.getPurchases().forEach(p -> {

    });
    return receipt;
  }

}
