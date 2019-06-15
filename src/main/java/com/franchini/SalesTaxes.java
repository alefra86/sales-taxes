package com.franchini;

import com.franchini.parser.ShoppingCartParser;
import com.franchini.service.ReceiptService;
import java.io.InputStream;

/**
 * Print a receipt
 */
public class SalesTaxes {

  private final ShoppingCartParser shoppingCartParser;
  private final ReceiptService receiptService;

  public SalesTaxes(ShoppingCartParser shoppingCartParser, ReceiptService receiptService) {
    this.shoppingCartParser = shoppingCartParser;
    this.receiptService = receiptService;
  }

  public String printReceipt(InputStream inputStream) {
    return receiptService.createReceipt(shoppingCartParser.parse(inputStream)).toString();
  }
}
