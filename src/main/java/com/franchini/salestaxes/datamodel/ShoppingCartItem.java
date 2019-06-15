package com.franchini.salestaxes.datamodel;

import java.math.BigDecimal;
import java.util.Objects;

public class ShoppingCartItem {

  private final int quantity;
  private final Item item;
  private final BigDecimal price;

  private ShoppingCartItem(int quantity, Item item, BigDecimal price) {
    this.quantity = quantity;
    this.item = item;
    this.price = price;
  }

  public static ShoppingCartItem of(int quantity, Item item, BigDecimal price) {
    return new ShoppingCartItem(quantity, item, price);
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ShoppingCartItem)) {
      return false;
    }
    ShoppingCartItem that = (ShoppingCartItem) o;
    return getItem().equals(that.getItem());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getItem());
  }
}
