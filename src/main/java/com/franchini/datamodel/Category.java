package com.franchini.datamodel;

public class Category {

  private final String name;
  private final boolean exempt;

  private Category(String name, boolean exempt) {
    this.name = name;
    this.exempt = exempt;
  }

  public static Category of(String name, boolean exempt) {
    return new Category(name, exempt);
  }

  public String getName() {
    return name;
  }

  public boolean isExempt() {
    return exempt;
  }
}
