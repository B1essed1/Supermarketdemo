package shakh.supermarketdemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.Product;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository  extends CrudRepository<Product,Long>
{
    Product getProductByBarcode(int barcode);
    //@Query("select Product ")
    @Query("select p from Product p where p.amount < 10 and p.amount >0")
    List<Product> findProductsByAmount();

    Optional<Product> findProductById(@Param("id") Long id);

}

