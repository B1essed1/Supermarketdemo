package shakh.supermarketdemo.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.data.ProductOrder;

import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem , Long>
{
    List<OrderItem> findByProductOrder(ProductOrder order);
  //  @Query("select oi from OrderItem oi where oi.productOrder.id = ?1")
    List<OrderItem> findByProductOrderId(Long id);
}
