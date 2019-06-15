package com.franchini.salestaxes.datamodel;

import java.util.Objects;

public class Item {

  public final static String IMPORTED_LABEL = "imported";
  private final String desc;
  private final boolean imported;

  private Item(String desc, boolean imported) {
    this.desc = desc;
    this.imported = imported;
  }

  public static Item newItem(String desc) {
    if (desc == null) {
      throw new IllegalArgumentException("Description is mandatory.");
    }
    return new Item(desc, false);
  }

  public static Item newImportedItem(String desc) {
    if (desc == null) {
      throw new IllegalArgumentException("Description is mandatory.");
    }
    return new Item(desc, true);
  }

  public String getDesc() {
    return desc;
  }

  public boolean isImported() {
    return imported;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item)) {
      return false;
    }
    Item item = (Item) o;
    return isImported() == item.isImported() && getDesc().equals(item.getDesc());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDesc(), isImported());
  }

  @Override
  public String toString() {
    return String.format("%s %s", imported ? IMPORTED_LABEL : "", desc).trim();
  }
}
