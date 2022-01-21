package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.ProductOrder;

import java.util.List;

public interface ProductOrderService
{
    List<ProductOrder> getAllOrders();
    ProductOrder save(ProductOrder order);
}
