package com.franchini.salestaxes.service;

import com.franchini.salestaxes.datamodel.Receipt;
import com.franchini.salestaxes.datamodel.ShoppingCart;

/**
 * Service for managing {@link Receipt} object.
 */
public interface ReceiptService {

  /**
   * Creates a {@link Receipt} from a {@link ShoppingCart}
   *
   * @param shoppingCart the shopping cart
   * @return the receipt for the shopping cart
   * @throws IllegalArgumentException if shoppingCart is empty
   */
  Receipt createReceipt(ShoppingCart shoppingCart);
}
