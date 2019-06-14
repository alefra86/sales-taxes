package com.franchini;

import com.franchini.datamodel.Receipt;
import com.franchini.datamodel.ReceiptItem;
import com.franchini.datamodel.ShoppingBasket;

/**
 * Service for building a receipt
 */
public class SalesTaxes {

  public Receipt createReceipt(ShoppingBasket shoppingBasket) {
    Receipt receipt = new Receipt();
    shoppingBasket.getShoppingBasketItems().forEach(i -> receipt.addReceiptItem(ReceiptItem.from(i)));
    return receipt;
  }

}
