package shakh.supermarketdemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.dto.ProductVisualisationDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("select new shakh.supermarketdemo.dto.ProductVisualisationDto(p.id, p.productName,p.priceOfSell, p.amount, c.category) from Product p   join Category c on p.categories.id = c.id and p.isActive = true ")
    List<ProductVisualisationDto> findAllProducts();

    @Query("select new shakh.supermarketdemo.dto.ProductVisualisationDto(p.id, p.productName,p.priceOfSell,p.amount , c.category) from Product  p , Category c where p.categories.id = c.id and  p.barcode = :barcode")
    ProductVisualisationDto getProductByBarcode(@Param("barcode") int barcode);


    @Query("select new shakh.supermarketdemo.dto.ProductVisualisationDto(p.id, p.productName,p.priceOfSell, p.amount, c.category) from Product p   join Category c on p.categories.id = c.id where p.amount < 10 and  p.amount >0 ")
    List<ProductVisualisationDto> findProductsByAmount();

    Optional<Product> findProductById(@Param("id") Long id);

}

