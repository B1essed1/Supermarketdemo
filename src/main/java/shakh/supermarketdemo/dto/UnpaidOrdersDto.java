package shakh.supermarketdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.model.ProductDetails;
import shakh.supermarketdemo.service.ProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UnpaidOrdersDto {
    private Long id;
    private  Double totalCost;
    private Double paidCost;
    private Date createdTime;

  //  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProductDetails> orderItems = new ArrayList<>();

  //  @JsonIgnore
    private List<ProductOrder> unpaidProductOrders = new ArrayList<>();

    public UnpaidOrdersDto(List<ProductOrder> productOrders){
        this.unpaidProductOrders = productOrders;
    }

    public List<UnpaidOrdersDto> getOrders(){
        List<UnpaidOrdersDto> unpaidOrders = new ArrayList<>();

        for (ProductOrder po: this.unpaidProductOrders) {
            UnpaidOrdersDto uod = new UnpaidOrdersDto();
            uod.setId(po.getId());
            uod.setTotalCost(po.getTotalCost());
            uod.setPaidCost(po.getPaidCost());
            uod.setCreatedTime(po.getCreatedTime());

            List<ProductDetails> productDetails = new ArrayList<>();
            for (OrderItem oi: po.getOrderItems()){
                ProductDetails pod = new ProductDetails();
                pod.setAmount(oi.getAmount());
                pod.setPriceOfSell(oi.getProduct().getPriceOfSell());
                pod.setId(oi.getId());
                pod.setProductName(oi.getProduct().getProductName());
                productDetails.add(pod);
            }
            uod.setOrderItems(productDetails);
            unpaidOrders.add(uod);
        }
        return unpaidOrders;
    }
}
