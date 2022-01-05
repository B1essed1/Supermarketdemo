package shakh.supermarketdemo.controller;


import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.exceptions.ProductNotFoundException;
import shakh.supermarketdemo.service.OrderItemService;
import shakh.supermarketdemo.service.ProductOrderService;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/api/selling/")
public class SellingProcessController
{

    private final OrderItemService orderItemService ;
    private final ProductOrderService productOrderService;

    public SellingProcessController(OrderItemService orderItemService, ProductOrderService productOrderService) {
        this.orderItemService = orderItemService;
        this.productOrderService = productOrderService;
    }


    @PostMapping("sell")
    public ResponseEntity<ProductOrder> sellingProcess(@RequestBody ProductOrder order)
    {

       /* List<OrderItem> products =  orderItemService.getByProductOrder(order);
        for (int i = 0; i <products.size(); i++) {
            System.out.println(products.get(i)
            );
        }*/
      ProductOrder order1 =  productOrderService.save(order);

      if (order1 == null) throw new ProductNotFoundException("harbalo bolgur");


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order1.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


}
