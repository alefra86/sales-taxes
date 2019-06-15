package com.franchini.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;

public class DefaultShoppingCartParserTest {

  private DefaultShoppingCartParser sut = new DefaultShoppingCartParser();

  @Test(expected = IllegalArgumentException.class)
  public void emptyStreamReturnsException() throws IOException {
    sut.parse(loadFileAsInputStream("empty.txt"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongContentReturnsException() throws IOException {
    sut.parse(loadFileAsInputStream("wrong.txt"));
  }

  private InputStream loadFileAsInputStream(String name) throws IOException {
    String path = "input" + File.separator + name;
    InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
    if (stream == null) {
      throw new IOException("File: '" + path + "' cannot be found");
    }
    return stream;
  }
}