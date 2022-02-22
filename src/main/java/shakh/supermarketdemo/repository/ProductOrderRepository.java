package shakh.supermarketdemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.ProductOrder;

import java.util.List;

@Repository
public interface ProductOrderRepository extends PagingAndSortingRepository<ProductOrder, Long>
{
    @Query("SELECT po from ProductOrder po where po.debitors.id =:id and po.paidCost < po.totalCost")
    List<ProductOrder> getProductOrdersByDebitorsId(@Param("id") Long id);
}
