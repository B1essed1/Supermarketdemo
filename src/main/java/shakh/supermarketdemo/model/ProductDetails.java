package shakh.supermarketdemo.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetails {
    private Long id;
    private String productName;
    private Double priceOfSell;
    private Double amount;
}
