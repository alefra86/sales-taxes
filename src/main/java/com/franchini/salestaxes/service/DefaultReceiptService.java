package com.franchini.salestaxes.service;

import com.franchini.salestaxes.datamodel.Receipt;
import com.franchini.salestaxes.datamodel.ReceiptItemFactory;
import com.franchini.salestaxes.datamodel.ShoppingCart;

/**
 * Service for building a receipt
 */
public class DefaultReceiptService implements ReceiptService {

  private final ReceiptItemFactory receiptItemFactory;

  public DefaultReceiptService(ReceiptItemFactory receiptItemFactory) {
    this.receiptItemFactory = receiptItemFactory;
  }

  @Override
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
