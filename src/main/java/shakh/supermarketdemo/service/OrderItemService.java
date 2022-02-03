package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.data.ProductOrder;

import java.util.List;

public interface OrderItemService
{
    List<OrderItem> getByProductOrder(ProductOrder order);
    OrderItem save(OrderItem orderItem);
    List<OrderItem> getByProductOrderId(Long id);
}
