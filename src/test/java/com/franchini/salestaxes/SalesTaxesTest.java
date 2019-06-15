package com.franchini.salestaxes;

import static org.junit.Assert.assertEquals;

import com.franchini.salestaxes.SalesTaxes;
import com.franchini.salestaxes.datamodel.DefaultReceiptItemFactory;
import com.franchini.salestaxes.parser.DefaultShoppingCartParser;
import com.franchini.salestaxes.repository.StubCategoryRepository;
import com.franchini.salestaxes.service.DefaultReceiptService;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

public class SalesTaxesTest {

  private SalesTaxes sut;

  @Before
  public void setUp() throws Exception {
    StubCategoryRepository categoryRepository = new StubCategoryRepository();
    sut = new SalesTaxes(new DefaultShoppingCartParser(categoryRepository),
      new DefaultReceiptService(new DefaultReceiptItemFactory(categoryRepository)));
  }

  @Test
  public void testInput1() throws IOException {
    String receipt = sut.printReceipt(loadFileAsInputStream("input1.txt", "input"));
    assertEquals(loadFileAsString("output1.txt"), receipt);
  }

  @Test
  public void testInput2() throws IOException {
    String receipt = sut.printReceipt(loadFileAsInputStream("input2.txt", "input"));
    assertEquals(loadFileAsString("output2.txt"), receipt);
  }

  @Test
  public void testInput3() throws IOException {
    String receipt = sut.printReceipt(loadFileAsInputStream("input3.txt", "input"));
    assertEquals(loadFileAsString("output3.txt"), receipt);
  }

  private InputStream loadFileAsInputStream(String filename, String folder) throws IOException {
    String path = folder + File.separator + filename;
    InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
    if (stream == null) {
      throw new IOException("File: '" + path + "' cannot be found");
    }
    return stream;
  }

  private String loadFileAsString(String filename) throws IOException {
    return new BufferedReader(new InputStreamReader(loadFileAsInputStream(filename, "output"))).lines()
             .collect(Collectors.joining(System.lineSeparator()));
  }
}