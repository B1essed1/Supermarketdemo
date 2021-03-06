package shakh.supermarketdemo.service.ServicesImpls;

import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.dto.OrderedItemsDto;
import shakh.supermarketdemo.repository.OrderItemRepository;
import shakh.supermarketdemo.service.OrderItemService;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public List<OrderItem> getByProductOrder(ProductOrder order)
    {
        List<OrderItem> orderItems = orderItemRepository.findByProductOrder(order);
        return orderItems;
    }

    @Override
    public OrderItem save(OrderItem orderItem)
    {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> getByProductOrderId(Long id) {
        return orderItemRepository.findByProductOrderId(id);
    }

    @Override
    public List<OrderedItemsDto> getOrderItemByProductOrderId(Long id) {
        List<OrderedItemsDto> orderedItems = orderItemRepository.findOrderItemsByProductOrderId(id);
        return orderedItems;
    }


}
