package com.franchini.datamodel;

import java.math.BigDecimal;

public class Purchase {

  private final int quantity;
  private final Item item;
  private final BigDecimal price;
  private BigDecimal tax;

  private Purchase(int quantity, Item item, BigDecimal price) {
    this.quantity = quantity;
    this.item = item;
    this.price = price;
  }

  public static Purchase of(int quantity, Item item, BigDecimal price) {
    return new Purchase(quantity, item, price);
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

  public BigDecimal getTax() {
    return tax;
  }

  public void applyTaxPercentage(BigDecimal taxPercentage) {

  }
}
