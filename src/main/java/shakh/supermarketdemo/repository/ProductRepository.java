package shakh.supermarketdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.Unload;

import java.util.Optional;


@Repository
public interface ProductRepository  extends CrudRepository<Product,Long>
{
    Product getProductByBarcode(int barcode);
    Optional<Product> findProductById(@Param("id") Long id);
    Unload findProductByUnloadId(@Param("id") long id);

}

