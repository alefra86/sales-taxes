package com.franchini;

import com.franchini.datamodel.Receipt;
import com.franchini.datamodel.ReceiptItemFactory;
import com.franchini.datamodel.ShoppingCart;

/**
 * Service for building a receipt
 */
public class SalesTaxes {

  private final ReceiptItemFactory receiptItemFactory;

  public SalesTaxes(ReceiptItemFactory receiptItemFactory) {
    this.receiptItemFactory = receiptItemFactory;
  }

  public Receipt createReceipt(ShoppingCart shoppingCart) {
    if (shoppingCart == null || shoppingCart.isEmpty()) {
      throw new IllegalArgumentException("Receipt not available. No items in your shopping cart.");
    }
    Receipt receipt = new Receipt();
    shoppingCart.getItems()
      .forEach(shoppingCartItem -> receipt.addReceiptItem(receiptItemFactory.getReceiptItem(shoppingCartItem)));
    return receipt;
  }

}
