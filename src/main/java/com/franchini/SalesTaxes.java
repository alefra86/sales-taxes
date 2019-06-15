package com.franchini;

import com.franchini.datamodel.BasicTaxReceiptItem;
import com.franchini.datamodel.ImportedReceiptItem;
import com.franchini.datamodel.Receipt;
import com.franchini.datamodel.ReceiptItem;
import com.franchini.datamodel.ReceiptItemImpl;
import com.franchini.datamodel.ShoppingCart;
import com.franchini.datamodel.ShoppingCartItem;
import com.franchini.repository.CategoryRepository;

/**
 * Service for building a receipt
 */
public class SalesTaxes {

  private final CategoryRepository categoryRepository;

  public SalesTaxes(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Receipt createReceipt(ShoppingCart shoppingCart) {
    if (shoppingCart == null || shoppingCart.isEmpty()) {
      throw new IllegalArgumentException("Receipt not available. No items in your shopping cart.");
    }
    Receipt receipt = new Receipt();
    shoppingCart.getItems().forEach(shoppingCartItem -> receipt.addReceiptItem(getReceiptItem(shoppingCartItem)));
    return receipt;
  }

  public ReceiptItem getReceiptItem(ShoppingCartItem shoppingCartItem) {
    ReceiptItem receiptItem = new ReceiptItemImpl(shoppingCartItem);
    if (shoppingCartItem.getItem().isImported()) {
      receiptItem = new ImportedReceiptItem(receiptItem);
    }
    if (!categoryRepository.findByProductName(shoppingCartItem.getItem().getDesc()).isExempt()) {
      receiptItem = new BasicTaxReceiptItem(receiptItem);
    }
    return receiptItem;

  }

}
