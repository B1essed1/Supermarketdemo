package shakh.supermarketdemo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.data.Payment;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.dto.OrderDto;
import shakh.supermarketdemo.dto.OrderedItemsDto;
import shakh.supermarketdemo.dto.ProductOrderDto;
import shakh.supermarketdemo.dto.UnpaidOrdersDto;
import shakh.supermarketdemo.service.*;

import java.util.Date;
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
    private final PaymentService paymentService;


    public ProductOrderController(OrderItemService orderItemService, ProductService productService,
                                  ProductOrderService productOrderService, AdminService adminService,
                                  DebitorService debitorService, PaymentService paymentService) {

        this.orderItemService = orderItemService;
        this.productService = productService;
        this.productOrderService = productOrderService;
        this.adminService = adminService;
        this.debitorService = debitorService;
        this.paymentService = paymentService;
    }

    @PostMapping("sell")
    public ResponseEntity<?> sellingProcess(@RequestBody ProductOrderDto productOrderDto) {
        ProductOrder productOrder = productOrderDto.convertToProductOrder(debitorService, adminService);
        productOrderService.save(productOrder);
        Payment payment = new Payment();
        Date now = new Date();

        payment.setTotalPaidCost(productOrderDto.getTotalCost());
        payment.setCreatedTime(now);
        payment.setDebitors(debitorService.getDebitorById(productOrderDto.getDebitorId()));
        payment.setProductOrder(productOrder);
        paymentService.save(payment);

        List<OrderItem> orderItemList = productOrderDto.getOrderItems();
        for (OrderItem orderItem : orderItemList) {
            OrderItem orderItem1 = new OrderItem();
            orderItem1.setAmount(orderItem.getAmount());
            orderItem1.setProductOrder(productOrder);
            orderItem1.setPriceOfBuy(orderItem.getPriceOfBuy());
            orderItem1.setPriceOfSell(orderItem.getPriceOfSell());
            Product product = productService.getProductById(orderItem.getProduct().getId());
            product.setAmount(product.getAmount() - orderItem.getAmount());
            orderItem1.setProduct(product);
            orderItemService.save(orderItem1);
        }

        return ResponseEntity.ok("successs");
    }


    @GetMapping("all")
    public ResponseEntity<List<ProductOrder>> getAllOrder() {
        List orders = productOrderService.getAllOrders();
        System.out.println("cron is working");
        return ResponseEntity.ok(orders);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable("id") Long id) {
        OrderDto orderDto =  productOrderService.getById(id);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("unpaid")
    public ResponseEntity<List<UnpaidOrdersDto>> getUnpaidOrders() {

        //List<ProductOrder> unpaidOrders = productOrderService.getUnpaidOrders();

        return ResponseEntity.ok((new UnpaidOrdersDto(unpaidOrders)).getOrders());
    }

}
