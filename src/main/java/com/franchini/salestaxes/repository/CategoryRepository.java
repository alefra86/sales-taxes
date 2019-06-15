package com.franchini.salestaxes.repository;

import com.franchini.salestaxes.datamodel.Category;

/**
 *
 */
public interface CategoryRepository {

  Category findByProductName(String productName);
}
