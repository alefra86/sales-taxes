package com.franchini.salestaxes.datamodel;

import java.math.BigDecimal;

/**
 * Represents an item of a receipt
 */
public interface ReceiptItem {

  int getQuantity();

  Item getItem();

  BigDecimal getPrice();

  BigDecimal getTax();

  BigDecimal getTotalPrice();
}
