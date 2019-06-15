package com.franchini.repository;

import com.franchini.datamodel.Category;
import java.util.HashMap;
import java.util.Map;

/**
 * Stub implementation of Category Repository.
 * <br>
 * In real scenario could be for example a DB or a file.
 */
public class StubCategoryRepository implements CategoryRepository {

  private final Map<String, Category> itemsCategory = new HashMap<>();

  {
    {
      itemsCategory.put("book", Category.of("books", true));
      itemsCategory.put("music CD", Category.of("music", false));
      itemsCategory.put("chocolate bar", Category.of("food", true));
      itemsCategory.put("box of chocolates", Category.of("food", true));
      itemsCategory.put("bottle of perfume", Category.of("cosmetics", false));
      itemsCategory.put("packet of headache pills", Category.of("medical products", true));
    }
  }

  @Override
  public Category findByProductName(String productName) {
    return itemsCategory.get(productName);
  }

}
