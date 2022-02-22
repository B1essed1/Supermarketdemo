package shakh.supermarketdemo.service.ServicesImpls;

import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.repository.ProductOrderRepository;
import shakh.supermarketdemo.service.ProductOrderService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderServiceImpl implements ProductOrderService
{
     private  final ProductOrderRepository productOrderRepository;

    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
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
    public Optional<ProductOrder> getById(Long id) {

        return productOrderRepository.findById(id);
    }

    @Override
    public List<ProductOrder> getUnpaidOrdersByDebitors(Long id) {

        List<ProductOrder> unpaidOrders = productOrderRepository.getProductOrdersByDebitorsId(id);

        return unpaidOrders;
    }
}
