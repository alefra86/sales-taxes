package com.franchini.salestaxes.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Utility class for loading resources.
 */
public class TestUtil {

  public static InputStream loadFileAsInputStream(Class<?> testClass, String filename, String folder) throws IOException {
    String path = folder + File.separator + filename;
    InputStream stream = testClass.getClassLoader().getResourceAsStream(path);
    if (stream == null) {
      throw new IOException("File: '" + path + "' cannot be found");
    }
    return stream;
  }

  public static String loadFileAsString(Class<?> testClass, String filename) throws IOException {
    return new BufferedReader(new InputStreamReader(loadFileAsInputStream(testClass, filename, "output"))).lines()
             .collect(Collectors.joining(System.lineSeparator()));
  }

}
