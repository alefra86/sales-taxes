package com.franchini.datamodel;

import java.math.BigDecimal;

/**
 * Represents an item of a receipt
 */
public interface ReceiptItem {

  BigDecimal getPrice();

  BigDecimal getTax();

  BigDecimal getTotalPrice();
}
