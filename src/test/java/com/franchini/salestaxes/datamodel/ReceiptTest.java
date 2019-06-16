package com.franchini.salestaxes.datamodel;

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
    sut.addReceiptItem(getReceiptItem("ITEM"));
    String receipt = sut.print();
    Assert.assertEquals(TestUtil.loadFileAsString(getClass(), "oneline-output.txt"), receipt);
  }

  @Test
  public void testPrintMultipleItems() throws IOException {
    sut.addReceiptItem(getReceiptItem("ITEM"));
    sut.addReceiptItem(getReceiptItem("ITEM2"));
    sut.addReceiptItem(getReceiptItem("ITEM3"));
    String receipt = sut.print();
    Assert.assertEquals(TestUtil.loadFileAsString(getClass(), "multiplelines-output.txt"), receipt);
  }

  private ReceiptItem getReceiptItem(final String itemDesc) {
    return new ReceiptItem() {
      @Override
      public int getQuantity() {
        return 1;
      }

      @Override
      public Item getItem() {
        return Item.newItem(itemDesc);
      }

      @Override
      public BigDecimal getPrice() {
        return new BigDecimal("10.05");
      }

      @Override
      public BigDecimal getTax() {
        return new BigDecimal("0.95");
      }

      @Override
      public BigDecimal getTotalPrice() {
        return new BigDecimal("11.00");
      }
    };
  }

}