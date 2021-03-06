package com.franchini.salestaxes.datamodel;

import com.franchini.salestaxes.repository.CategoryRepository;

/**
 * Default implementation that create a {@link ReceiptItem} and decorate it depending on its tax status.
 */
public class DefaultReceiptItemFactory implements ReceiptItemFactory {

  private final CategoryRepository categoryRepository;

  public DefaultReceiptItemFactory(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public ReceiptItem getReceiptItem(ShoppingCartItem shoppingCartItem) {
    ReceiptItem receiptItem = new TaxFreeReceiptItem(shoppingCartItem);
    if (shoppingCartItem.getItem().isImported()) {
      receiptItem = new ImportedReceiptItem(receiptItem);
    }
    if (!categoryRepository.findByProductName(shoppingCartItem.getItem().getDesc()).isExempt()) {
      receiptItem = new BasicTaxReceiptItem(receiptItem);
    }
    return receiptItem;

  }

}
