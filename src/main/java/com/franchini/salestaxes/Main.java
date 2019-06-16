package com.franchini.salestaxes;

import com.franchini.salestaxes.datamodel.DefaultReceiptItemFactory;
import com.franchini.salestaxes.parser.DefaultShoppingCartParser;
import com.franchini.salestaxes.repository.StubCategoryRepository;
import com.franchini.salestaxes.service.DefaultReceiptService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("Please provide a path to a file input. You can find some example in data folder.");
      System.exit(0);
    }
    StubCategoryRepository categoryRepository = new StubCategoryRepository();
    SalesTaxesController salesTaxesController = new SalesTaxesController(new DefaultShoppingCartParser(categoryRepository),
      new DefaultReceiptService(new DefaultReceiptItemFactory(categoryRepository)));
    try {
      System.out.println(salesTaxesController.printReceipt(loadFile(args[0])));
    } catch (FileNotFoundException e) {
      System.err.println("File not found");
      System.exit(0);
    }
  }

  private static InputStream loadFile(String filename) throws FileNotFoundException {
    Path path = Paths.get(filename);
    return new FileInputStream(path.toFile());
  }

}
