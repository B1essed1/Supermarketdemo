package shakh.supermarketdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import shakh.supermarketdemo.data.Payment;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.service.ProductOrderService;

import java.util.List;

@Data
@NoArgsConstructor
public class PaymentDto {
    private Long id;
    private Double amount;

    public List<ProductOrder> payAllDebts(PaymentDto paymentDto, ProductOrderService service){
        List<ProductOrder> productOrders = service.getUnpaidOrdersByDebitors(paymentDto.getId());
        Double paymentAmount = paymentDto.getAmount();

        while (paymentAmount >= 0){
            int i=0;
            if (paymentAmount >= productOrders.get(i).getTotalCost()-productOrders.get(i).getPaidCost()) {
                productOrders.get(i).setPaidCost(productOrders.get(i).getPaidCost() + (productOrders.get(i).getTotalCost() - productOrders.get(i).getPaidCost()));
                paymentAmount -= productOrders.get(i).getTotalCost() - productOrders.get(i).getPaidCost();
                i++;
            } else {
                productOrders.get(i).setPaidCost(productOrders.get(i).getPaidCost()+paymentAmount);
            }
        }


        return productOrders;
    }
}
