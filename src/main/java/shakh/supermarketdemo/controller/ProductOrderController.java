package shakh.supermarketdemo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.*;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.dto.ProductOrderDto;
import shakh.supermarketdemo.service.*;

import javax.persistence.EntityManager;
import java.net.URI;
import java.util.List;

@RestController()
@Slf4j
@RequestMapping("/api/orders/")
public class ProductOrderController {

    private final OrderItemService orderItemService;
    private final ProductService productService;
    private final ProductOrderService productOrderService;
    private final AdminService adminService;
    private final DebitorService debitorService;
    private final EntityManager entityManager;
    HibernateTemplate hibernateTemplate;


    public ProductOrderController(OrderItemService orderItemService, ProductService productService, ProductOrderService productOrderService, AdminService adminService, DebitorService debitorService, EntityManager entityManager) {
        this.orderItemService = orderItemService;
        this.productService = productService;
        this.productOrderService = productOrderService;
        this.adminService = adminService;
        this.debitorService = debitorService;
        this.entityManager = entityManager;
    }

    @PostMapping("sell")
    public ResponseEntity<?> sellingProcess(@RequestBody ProductOrderDto productOrderDto) {
        ProductOrder productOrder = productOrderDto.convertToProductOrder(debitorService, adminService);
        productOrderService.save(productOrder);
        List<OrderItem> orderItemList = productOrderDto.getOrderItems();
        for (OrderItem orderItem : orderItemList) {
            OrderItem orderItem1 = new OrderItem();
            orderItem1.setAmount(orderItem.getAmount());
            orderItem1.setProductOrder(productOrder);
            orderItem1.setPriceOfBuy(orderItem.getPriceOfBuy());
            orderItem1.setPriceOfSell(orderItem.getPriceOfSell());
            Product product = productService.getProductById(orderItem.getProduct().getId());
            orderItem1.setProduct(product);

            orderItemService.save(orderItem1);
        }

        return ResponseEntity.ok("successs");
    }


    @GetMapping("get/all")
    public ResponseEntity<List<ProductOrder>> getAllOrder() {
        List orders = productOrderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<ProductOrderDto> getById(@PathVariable("id") Long id){

        List<OrderItem> items = orderItemService.getByProductOrderId(id);
        ProductOrder order = productOrderService.getById(id).get();
        return ResponseEntity.ok(new ProductOrderDto(order, items));
    }
}
