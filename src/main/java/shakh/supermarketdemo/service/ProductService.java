package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.dto.ProductVisualisationDto;

import java.util.List;
import java.util.Set;

public interface ProductService {

    Product findById(Long id);

    Product save(Product product);

    void hardDeleteProductById(Long id);

    List<ProductVisualisationDto> getProductByFewAndAlert();

    Boolean checkProductByBarcode(int barcode);

    ProductVisualisationDto findByBarcode(int barcode);

    List<ProductVisualisationDto> getAllProduct();

    Product getProductById(Long id);
}


