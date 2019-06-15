package com.franchini;

import com.franchini.datamodel.BasicTaxReceiptItem;
import com.franchini.datamodel.Category;
import com.franchini.datamodel.ImportedReceiptItem;
import com.franchini.datamodel.Receipt;
import com.franchini.datamodel.ReceiptItem;
import com.franchini.datamodel.ReceiptItemImpl;
import com.franchini.datamodel.ShoppingCart;
import com.franchini.datamodel.ShoppingCartItem;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for building a receipt
 */
public class SalesTaxes {

  private final Map<String, Category> itemsCategory = new HashMap<>();

  {
    {
      itemsCategory.put("book", Category.of("books", true));
      itemsCategory.put("music CD", Category.of("music", false));
      itemsCategory.put("chocolade bar", Category.of("food", true));
      itemsCategory.put("box of chocolates", Category.of("food", true));
      itemsCategory.put("bootle of perfume", Category.of("cosmetics", false));
      itemsCategory.put("packet of headache pills", Category.of("medical products", true));
    }
  }

  public Receipt createReceipt(ShoppingCart shoppingCart) {
    if (shoppingCart == null || shoppingCart.isEmpty()) {
      throw new IllegalArgumentException("Receipt not available. No items in your shopping cart.");
    }
    Receipt receipt = new Receipt();
    shoppingCart.getItems().forEach(shoppingCartItem -> receipt.addReceiptItem(getReceiptItem(shoppingCartItem)));
    return receipt;
  }

  public ReceiptItem getReceiptItem(ShoppingCartItem shoppingCartItem) {
    ReceiptItem receiptItem = new ReceiptItemImpl(shoppingCartItem);
    if (shoppingCartItem.getItem().isImported()) {
      receiptItem = new ImportedReceiptItem(receiptItem);
    }
    if (!itemsCategory.get(shoppingCartItem.getItem().getDesc()).isExempt()) {
      receiptItem = new BasicTaxReceiptItem(receiptItem);
    }
    return receiptItem;

  }

}
