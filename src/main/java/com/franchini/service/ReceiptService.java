package com.franchini.service;

import com.franchini.datamodel.Receipt;
import com.franchini.datamodel.ShoppingCart;

/**
 *
 */
public interface ReceiptService {

  Receipt createReceipt(ShoppingCart shoppingCart);
}
