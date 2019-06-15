package com.franchini.salestaxes.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represent the shopping basket
 */
public class ShoppingCart {

  private List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

  public void addItem(ShoppingCartItem shoppingCartItem) {
    shoppingCartItems.add(shoppingCartItem);
  }

  public List<ShoppingCartItem> getItems() {
    return Collections.unmodifiableList(shoppingCartItems);
  }

  public boolean isEmpty() {
    return shoppingCartItems.isEmpty();
  }

  public int size() {
    return shoppingCartItems.size();
  }
}
