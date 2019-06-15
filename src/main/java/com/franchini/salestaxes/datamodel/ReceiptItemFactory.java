package com.franchini.salestaxes.datamodel;

/**
 * Creates {@link ReceiptItem} starting from a {@link ShoppingCartItem}
 */
public interface ReceiptItemFactory {

  ReceiptItem getReceiptItem(ShoppingCartItem shoppingCartItem);
}
