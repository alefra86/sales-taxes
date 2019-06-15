package com.franchini.salestaxes.datamodel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.franchini.salestaxes.datamodel.Category;
import com.franchini.salestaxes.datamodel.DefaultReceiptItemFactory;
import com.franchini.salestaxes.datamodel.Item;
import com.franchini.salestaxes.datamodel.ReceiptItem;
import com.franchini.salestaxes.datamodel.ShoppingCartItem;
import com.franchini.salestaxes.repository.CategoryRepository;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultReceiptItemFactoryTest {

  public static final String BASICTAX_FREE_ITEM = "book";
  public static final String TAXED_ITEM = "music CD";
  public static final Category EXEMPT_CATEGORY = Category.of("", false);
  public static final Category NOT_EXEMPT_CATEGORY = Category.of("", true);

  @InjectMocks
  private DefaultReceiptItemFactory sut;
  @Mock
  private CategoryRepository categoryRepository;

  @Test
  public void testTaxFreeItem() {
    when(categoryRepository.findByProductName(anyString())).thenReturn(NOT_EXEMPT_CATEGORY);
    ReceiptItem receiptItem = sut.getReceiptItem(ShoppingCartItem.of(1, Item.newItem(BASICTAX_FREE_ITEM), BigDecimal.ONE));
    assertEquals(BigDecimal.ZERO, receiptItem.getTax());
  }

  @Test
  public void testImportedItem() {
    when(categoryRepository.findByProductName(anyString())).thenReturn(NOT_EXEMPT_CATEGORY);
    ReceiptItem receiptItem = sut.getReceiptItem(ShoppingCartItem.of(1, Item.newImportedItem(BASICTAX_FREE_ITEM), BigDecimal.ONE));
    assertEquals(new BigDecimal("0.05"), receiptItem.getTax());
  }

  @Test
  public void testBasicTaxFreeItem() {
    when(categoryRepository.findByProductName(anyString())).thenReturn(EXEMPT_CATEGORY);
    ReceiptItem receiptItem = sut.getReceiptItem(ShoppingCartItem.of(1, Item.newItem(TAXED_ITEM), BigDecimal.ONE));
    assertEquals(new BigDecimal("0.10"), receiptItem.getTax());
  }

  @Test
  public void testImportedBasicTaxFreeItem() {
    when(categoryRepository.findByProductName(anyString())).thenReturn(EXEMPT_CATEGORY);
    ReceiptItem receiptItem = sut.getReceiptItem(ShoppingCartItem.of(1, Item.newImportedItem(TAXED_ITEM), BigDecimal.ONE));
    assertEquals(new BigDecimal("0.15"), receiptItem.getTax());
  }
}