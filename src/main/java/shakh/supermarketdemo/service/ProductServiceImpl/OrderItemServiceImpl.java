package shakh.supermarketdemo.service.ProductServiceImpl;

import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.data.ProductOrder;
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
}
