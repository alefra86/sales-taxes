package com.franchini.datamodel;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Category)) {
      return false;
    }
    Category category = (Category) o;
    return getName().equals(category.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
