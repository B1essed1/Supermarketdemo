package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {

    Product findById(Long id);

    Product save(Product product);

    void hardDeleteProductById(Long id);

    List<Product> getProductByFewAndAlert();

    Boolean checkProductByBarcode(int barcode);

    Product findByBarcode(int barcode);

    Set<Product> getAllProduct();

    Product getProductById(Long id);
}


