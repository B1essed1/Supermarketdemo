package shakh.supermarketdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.ProductOrder;

@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long>
{

}
