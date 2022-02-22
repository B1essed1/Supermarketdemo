package shakh.supermarketdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.dto.PaymentDto;
import shakh.supermarketdemo.service.ProductOrderService;

import java.util.List;


@RestController
@RequestMapping("/api/payment/debitors")
public class PaymentController {

    private final ProductOrderService productOrderService;

    public PaymentController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @PutMapping("/debt")
    public ResponseEntity<List<ProductOrder>> addPayment(@RequestBody PaymentDto paymentDto){

        List<ProductOrder> payingOrders = paymentDto.payAllDebts(paymentDto,productOrderService);

    return ResponseEntity.ok(payingOrders);
    }
}
