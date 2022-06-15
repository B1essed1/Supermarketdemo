package shakh.supermarketdemo.service.ServicesImpls;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.dto.OrderDto;
import shakh.supermarketdemo.dto.OrderedItemsDto;
import shakh.supermarketdemo.repository.ProductOrderRepository;
import shakh.supermarketdemo.service.OrderItemService;
import shakh.supermarketdemo.service.ProductOrderService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderServiceImpl implements ProductOrderService
{
     private  final ProductOrderRepository productOrderRepository;
     private final OrderItemService orderItemService;

    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository, OrderItemService orderItemService) {
        this.productOrderRepository = productOrderRepository;
        this.orderItemService = orderItemService;
    }

    @Override
    public List<ProductOrder> getAllOrders() {

        List orders = new ArrayList();
        productOrderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    @Transactional
    public ProductOrder save(ProductOrder order) {
        ProductOrder productOrder = productOrderRepository.save(order);
        return productOrder;

    }

    @Override
    public OrderDto getById(Long id) {
       OrderDto order = productOrderRepository.getProductOrderById(id, PageRequest.of(0, 26));
        List<OrderedItemsDto> orderItems = orderItemService.getOrderItemByProductOrderId(id);
        order.setOrderedItems(orderItems);
        return order;
    }

    @Override
    public List<ProductOrder> getUnpaidOrdersByDebitors(Long id) {

        List<ProductOrder> unpaidOrders = productOrderRepository.getProductOrdersByDebitorsId(id);

        return unpaidOrders;
    }
}
