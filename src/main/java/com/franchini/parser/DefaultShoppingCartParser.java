package com.franchini.parser;

import com.franchini.datamodel.ShoppingCart;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default implementation that read each line of the input stream, extract the items and checks if they are imported and belong to
 * any of recognized categories.
 */
public class DefaultShoppingCartParser implements ShoppingCartParser {

  @Override
  public ShoppingCart parse(InputStream inputStream) {
    List<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.toList());
    if (lines.isEmpty()) {
      throw new IllegalArgumentException("Empty shopping cart. Please provide a correct one.");
    }
    return new ShoppingCart();
  }
}
