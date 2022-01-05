package shakh.supermarketdemo.service.ProductServiceImpl;

import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.repository.ProductOrderRepository;
import shakh.supermarketdemo.service.ProductOrderService;

@Service
public class ProductOrderServiceImpl implements ProductOrderService
{
     private  final ProductOrderRepository productOrderRepository;

    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public ProductOrder save(ProductOrder order) {
        return productOrderRepository.save(order);
    }
}
