package shakh.supermarketdemo.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.dto.OrderedItemsDto;

import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem , Long>
{
    List<OrderItem> findByProductOrder(ProductOrder order);
  //  @Query("select oi from OrderItem oi where oi.productOrder.id = ?1")
    List<OrderItem> findByProductOrderId(Long id);

    @Query("select new shakh.supermarketdemo.dto.OrderedItemsDto(oi.id,p.id,p.productName,oi.priceOfSell,oi.priceOfBuy) from OrderItem oi , Product p where oi.product.id = p.id and oi.productOrder.id = :id")
    List<OrderedItemsDto> findOrderItemsByProductOrderId(@Param("id") Long id);

}
