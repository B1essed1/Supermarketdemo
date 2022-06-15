package shakh.supermarketdemo.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.dto.OrderDto;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    @Query("SELECT po from ProductOrder po where po.debitors.id =:id and po.paidCost < po.totalCost")
    List<ProductOrder> getProductOrdersByDebitorsId(@Param("id") Long id);

    @Query("select new shakh.supermarketdemo.dto.OrderDto(po.id, a.id, d.id,concat(a.firstName,' ',a.lastName ) " +
            ",concat(d.firstName,' ',d.lastName) ,po.paidCost,po.totalCost) from ProductOrder po , Admins a , " +
            "Debitors d where po.admins.id = a.id and po.debitors.id = d.id and po.id = :id")
    OrderDto getProductOrderById(@Param("id") Long id, Pageable pageable);


    List<OrderDto> getUnpaidOrders();


}
