package shakh.supermarketdemo.service.ServicesImpls;

import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.exceptions.ProductNotFoundException;
import shakh.supermarketdemo.repository.ProductRepository;
import shakh.supermarketdemo.service.ProductService;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id)
    {
        Optional<Product> optionalProduct = productRepository.findById(id);
            return optionalProduct.get();
    }

    @Override
    public Product save(Product product) {
        Date currentTime  = new Date();
        product.setCreatedTime(currentTime);
        return productRepository.save(product);
    }

    @Override
    public void hardDeleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductByFewAndAlert() {
        List<Product> products = productRepository.findProductsByAmount();
        return products;
    }

    @Override
    public Boolean checkProductByBarcode(int barcode) {

        if (productRepository.getProductByBarcode(barcode) != null)
            return true;
        else return  false ;
    }

    @Override
    public Product findByBarcode(int barcode)
    {
        Product product= productRepository.getProductByBarcode(barcode);
        if (product== null)
        {
             throw new RuntimeException("not fond");
        } else

        return product;
    }

    @Override
    public Set<Product> getAllProduct()
    {
        Set<Product> allProductsSet = new HashSet<>();
        productRepository.findAll().forEach(allProductsSet::add);
        return allProductsSet;
    }


    @Override
    public Product getProductById(Long id)
    {

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else throw new ProductNotFoundException(" Get productById is not working");
    }

}
