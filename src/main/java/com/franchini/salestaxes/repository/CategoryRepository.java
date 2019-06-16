package com.franchini.salestaxes.repository;

import com.franchini.salestaxes.datamodel.Category;

/**
 * Repository of {@link Category}
 */
public interface CategoryRepository {

  /**
   * Retrieves the cateogry belonging to a product name passed as argument.
   *
   * @param productName the product name of which retrieve the category
   * @return the Category of the product name; null otherwise.
   */
  Category findByProductName(String productName);
}
