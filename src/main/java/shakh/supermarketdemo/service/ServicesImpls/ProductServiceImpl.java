package shakh.supermarketdemo.service.ServicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.exceptions.ProductNotFoundException;
import shakh.supermarketdemo.repository.ProductRepository;
import shakh.supermarketdemo.service.ProductService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product findById(Long id)
    {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
        {
            throw new RuntimeException("ID Not Found");
        }
        else
            return productRepository.findById(id).get();
    }

    @Override
    public Product save(Product product) {
        LocalDateTime currentTime  = LocalDateTime.now();
        product.setCreatedTime(currentTime);
        return productRepository.save(product);
    }


    @Override
    public Set<Product> findBetweenLastWeak() {
        return null;
    }

    @Override
    public Set<Product> findByCount() {
        return null;
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
    public Product saveUpdate(Product product)
    {
        LocalDateTime updateTime  = LocalDateTime.now();
        product.setLastUpdatedTime(updateTime);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id)
    {

        Optional<Product> product = productRepository.findProductById(id);

        if (product.isPresent())
        {
            return product.get();
        } else throw new ProductNotFoundException("######### Get productById is not working");
    }

}
