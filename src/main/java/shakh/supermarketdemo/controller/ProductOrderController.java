package shakh.supermarketdemo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.Debitors;
import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.service.AdminService;
import shakh.supermarketdemo.service.DebitorService;
import shakh.supermarketdemo.service.OrderItemService;
import shakh.supermarketdemo.service.ProductOrderService;

import javax.persistence.EntityManager;
import java.net.URI;
import java.util.List;

@RestController()
@Slf4j
@RequestMapping("/api/orders/")
public class ProductOrderController
{

    private final OrderItemService orderItemService ;
    private final ProductOrderService productOrderService;
    private final AdminService adminService;
    private final DebitorService debitorService;
    private final EntityManager entityManager;
    private  HibernateTemplate hibernateTemplate;


    public ProductOrderController(OrderItemService orderItemService, ProductOrderService productOrderService, AdminService adminService, DebitorService debitorService, EntityManager entityManager) {
        this.orderItemService = orderItemService;
        this.productOrderService = productOrderService;
        this.adminService = adminService;
        this.debitorService = debitorService;
        this.entityManager = entityManager;
    }

    @PostMapping("sell")
    public ResponseEntity<ProductOrder> sellingProcess(@RequestBody ProductOrder order)
    {
        ProductOrder order1  =   productOrderService.save(order);
        List<OrderItem> orderItemList = orderItemService.getByProductOrder(order1);

        for (int i = 0; i < orderItemList.size(); i++)
        {
            //shu qator kelgan idni FK columnga qoshishi kerek lkn ishlamayyapti umuman
            orderItemList.get(i).setProduct(entityManager.getReference(Product.class ,
                                             orderItemList.get(i).getProduct().getId()));

            log.info("productId------------------> " + orderItemList.get(i).getProduct().getId());
            orderItemService.save(orderItemList.get(i));
        }
        //boshqacha yol
        //shu qator kelgan idni FK columnga qoshishi kerek lkn ishlamayyapti umuman
        log.info("****************" + adminService.getAdminById(2l).getId()+adminService.getAdminById(2l).getUsername());
        order1.setAdmins(adminService.getAdminById(2L));

        //shu qator kelgan idni FK columnga qoshishi kerek lkn ishlamayyapti umuman
        order1.setDebitors(entityManager.getReference(Debitors.class, 1L));

        log.info("******************   "+ order1.getAdmins().getUsername());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(order1.getId()).toUri();

        return  ResponseEntity.created(location).build();
    }


    @GetMapping("get/all")
    public ResponseEntity<List<ProductOrder>> getAllOrder(){
        List orders = productOrderService.getAllOrders();

        return ResponseEntity.ok(orders);
    }
}
