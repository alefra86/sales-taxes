package com.franchini.repository;

import com.franchini.datamodel.Category;

/**
 *
 */
public interface CategoryRepository {

  Category findByProductName(String productName);
}
