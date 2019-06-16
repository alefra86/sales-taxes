package com.franchini.salestaxes;

import static com.franchini.salestaxes.util.TestUtil.loadFileAsInputStream;
import static com.franchini.salestaxes.util.TestUtil.loadFileAsString;
import static org.junit.Assert.assertEquals;

import com.franchini.salestaxes.datamodel.DefaultReceiptItemFactory;
import com.franchini.salestaxes.parser.DefaultShoppingCartParser;
import com.franchini.salestaxes.repository.StubCategoryRepository;
import com.franchini.salestaxes.service.DefaultReceiptService;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class SalesTaxesTest {

  private SalesTaxes sut;

  @Before
  public void setUp() {
    StubCategoryRepository categoryRepository = new StubCategoryRepository();
    sut = new SalesTaxes(new DefaultShoppingCartParser(categoryRepository),
      new DefaultReceiptService(new DefaultReceiptItemFactory(categoryRepository)));
  }

  @Test
  public void testInput1() throws IOException {
    String receipt = sut.printReceipt(loadFileAsInputStream(getClass(), "input1.txt", "input"));
    assertEquals(loadFileAsString(getClass(), "output1.txt"), receipt);
  }

  @Test
  public void testInput2() throws IOException {
    String receipt = sut.printReceipt(loadFileAsInputStream(getClass(), "input2.txt", "input"));
    assertEquals(loadFileAsString(getClass(), "output2.txt"), receipt);
  }

  @Test
  public void testInput3() throws IOException {
    String receipt = sut.printReceipt(loadFileAsInputStream(getClass(), "input3.txt", "input"));
    assertEquals(loadFileAsString(getClass(), "output3.txt"), receipt);
  }

}