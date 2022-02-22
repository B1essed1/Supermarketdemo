package shakh.supermarketdemo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.ProductOrder;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long id;

    private Boolean isActive = true ;

    private Double priceOfBuy;

    private Double priceOfSell;

    private ProductOrder productOrder;

    private Product product;

    private Double amount ;

}
