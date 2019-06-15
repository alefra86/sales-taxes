package com.franchini.parser;

import com.franchini.datamodel.ShoppingCart;
import java.io.InputStream;

/**
 *
 */
public interface ShoppingCartParser {

  /**
   * Parses an inputstream into a {@link ShoppingCart}
   *
   * @param inputStream the inpuntstream containing the input data
   * @return the ShoppingCart instance
   */
  ShoppingCart parse(InputStream inputStream);

}
