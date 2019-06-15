package com.franchini.salestaxes.service;

import com.franchini.salestaxes.datamodel.Receipt;
import com.franchini.salestaxes.datamodel.ShoppingCart;

/**
 *
 */
public interface ReceiptService {

  Receipt createReceipt(ShoppingCart shoppingCart);
}
