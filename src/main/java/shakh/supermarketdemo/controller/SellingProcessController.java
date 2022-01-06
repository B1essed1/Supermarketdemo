package shakh.supermarketdemo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.service.AdminService;
import shakh.supermarketdemo.service.OrderItemService;
import shakh.supermarketdemo.service.ProductOrderService;

import java.util.List;

@RestController()
@RequestMapping("/api/selling/")
public class SellingProcessController
{

    private final OrderItemService orderItemService ;
    private final ProductOrderService productOrderService;
    private final AdminService adminService;


    public SellingProcessController(OrderItemService orderItemService, ProductOrderService productOrderService, AdminService adminService) {
        this.orderItemService = orderItemService;
        this.productOrderService = productOrderService;
        this.adminService = adminService;
    }

    @PostMapping("sell")

    public ResponseEntity<ProductOrder> sellingProcess(@RequestBody ProductOrder order)
    {
        List<OrderItem> orderItemList = orderItemService.getByProductOrder(order);
        for (int i = 0; i < orderItemList.size(); i++)
        {
            orderItemService.save(orderItemList.get(i));
        }
        adminService.save(order.getAdmins());
        ProductOrder order1  =   productOrderService.save(order);
        return ResponseEntity.ok(order1);
    }
}
