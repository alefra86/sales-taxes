package com.franchini.salestaxes.parser;

import com.franchini.salestaxes.datamodel.ShoppingCart;
import java.io.InputStream;

/**
 * A parser for getting a {@link ShoppingCart} from an {@link InputStream}
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
