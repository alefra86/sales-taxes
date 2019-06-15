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
      itemsCategory.put("chocolade bar", Category.of("food", true));
      itemsCategory.put("box of chocolates", Category.of("food", true));
      itemsCategory.put("bottle of perfume", Category.of("cosmetics", false));
      itemsCategory.put("packet of headache pills", Category.of("medical products", true));
    }
  }

  @Override
  public Category findByProductName(String productName) {
    Category category = itemsCategory.get(productName);
    if (category == null) {
      throw new IllegalArgumentException(String.format("No Category found for %s", productName));
    }
    return category;
  }

}
