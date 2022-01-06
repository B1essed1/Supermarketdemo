package shakh.supermarketdemo.service.ServicesImpls;

import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.repository.ProductOrderRepository;
import shakh.supermarketdemo.service.ProductOrderService;

import javax.transaction.Transactional;

@Service
public class ProductOrderServiceImpl implements ProductOrderService
{
     private  final ProductOrderRepository productOrderRepository;

    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    @Transactional
    public ProductOrder save(ProductOrder order) {
        ProductOrder productOrder = productOrderRepository.save(order);
        return productOrder;

    }
}
