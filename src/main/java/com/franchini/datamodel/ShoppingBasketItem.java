package com.franchini.datamodel;

import java.math.BigDecimal;

public class ShoppingBasketItem {

  private final int quantity;
  private final Item item;
  private final BigDecimal price;

  private ShoppingBasketItem(int quantity, Item item, BigDecimal price) {
    this.quantity = quantity;
    this.item = item;
    this.price = price;
  }

  public static ShoppingBasketItem of(int quantity, Item item, BigDecimal price) {
    return new ShoppingBasketItem(quantity, item, price);
  }

  public int getQuantity() {
    return quantity;
  }

  public Item getItem() {
    return item;
  }

  public BigDecimal getPrice() {
    return price;
  }

}
