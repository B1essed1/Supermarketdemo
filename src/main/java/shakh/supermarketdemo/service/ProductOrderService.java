package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.dto.OrderDto;

import java.util.List;
import java.util.Optional;

public interface ProductOrderService
{
    List<ProductOrder> getAllOrders();
    ProductOrder save(ProductOrder order);
    OrderDto getById(Long id);
    List<ProductOrder> getUnpaidOrdersByDebitors(Long id);
}
