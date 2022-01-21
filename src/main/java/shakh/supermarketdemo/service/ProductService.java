package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.Product;


import java.util.Set;

public interface ProductService {

    Product findById(Long id);

    Product save(Product product);

    void hardDeleteProductById(Long id);

    void softDeleteProductById(Long id);
    Boolean checkProductByBarcode(int barcode);


    Set<Product> findBetweenLastWeak();

    Set<Product> findByCount();

    Product findByBarcode(int barcode);

    Set<Product> getAllProduct();

    Product saveUpdate(Product product);

    Product getProductById(Long id);
}


