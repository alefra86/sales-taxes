package com.franchini.salestaxes.datamodel;

import static org.junit.Assert.assertEquals;

import com.franchini.salestaxes.util.TestUtil;
import java.io.IOException;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptTest {

  public static final String BOOK = "book";
  public static final String MUSIC_CD = "music CD";
  public static final String CHOCOLATE = "chocolate";
  private Receipt sut;

  @Before
  public void setUp() {
    sut = new Receipt();
  }

  @Test(expected = IllegalStateException.class)
  public void emptyReceiptThrowsException() {
    sut.print();
  }

  @Test
  public void testPrintOneItem() throws IOException {
    sut.addReceiptItem(getReceiptItem(MUSIC_CD));
    String receipt = sut.print();
    Assert.assertEquals(TestUtil.loadFileAsString(getClass(), "oneline-output.txt"), receipt);
  }

  @Test
  public void testPrintMultipleItems() throws IOException {
    sut.addReceiptItem(getReceiptItem(MUSIC_CD));
    sut.addReceiptItem(getReceiptItem(BOOK));
    sut.addReceiptItem(getReceiptItem(CHOCOLATE));
    String receipt = sut.print();
    Assert.assertEquals(TestUtil.loadFileAsString(getClass(), "multiplelines-output.txt"), receipt);
  }

  @Test
  public void testSize() {
    sut.addReceiptItem(getReceiptItem(BOOK));
    sut.addReceiptItem(getReceiptItem(MUSIC_CD));
    assertEquals(2, sut.size());
  }

  @Test
  public void testTotalAndTax() {
    sut.addReceiptItem(getReceiptItem(BOOK));
    assertEquals(new BigDecimal("0.95"), sut.getTaxes());
    assertEquals(new BigDecimal("11.00"), sut.getTotal());
  }

  @Test
  public void testTotalAndTaxMultipleItems() {
    sut.addReceiptItem(getReceiptItem(BOOK));
    assertEquals(new BigDecimal("0.95"), sut.getTaxes());
    assertEquals(new BigDecimal("11.00"), sut.getTotal());
  }

  private ReceiptItem getReceiptItem(final String itemDesc) {
    return new StubReceiptItem(itemDesc);
  }

}