package com.franchini;

import com.franchini.datamodel.Receipt;
import com.franchini.datamodel.ReceiptItem;
import com.franchini.datamodel.ShoppingCart;

/**
 * Service for building a receipt
 */
public class SalesTaxes {

  public Receipt createReceipt(ShoppingCart shoppingCart) {
    if (shoppingCart == null || shoppingCart.isEmpty()) {
      throw new IllegalArgumentException("Receipt not available. No items in your shopping cart.");
    }
    Receipt receipt = new Receipt();
    shoppingCart.getItems().forEach(i -> receipt.addReceiptItem(ReceiptItem.from(i)));
    return receipt;
  }

}
