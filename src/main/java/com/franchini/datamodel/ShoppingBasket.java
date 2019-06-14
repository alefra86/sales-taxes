package com.franchini.datamodel;

import java.util.Collections;
import java.util.List;

/**
 *
 */
public class ShoppingBasket {

  private List<ShoppingBasketItem> shoppingBasketItems;

  public void addPurchase(ShoppingBasketItem shoppingBasketItem) {
    shoppingBasketItems.add(shoppingBasketItem);
  }

  public List<ShoppingBasketItem> getShoppingBasketItems() {
    return Collections.unmodifiableList(shoppingBasketItems);
  }
}
