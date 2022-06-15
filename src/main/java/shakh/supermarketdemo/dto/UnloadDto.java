package shakh.supermarketdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.Unload;
import shakh.supermarketdemo.service.ProductService;

import java.util.Date;

@NoArgsConstructor
@Data
public class UnloadDto {

    private Long id;

    private Double amount;

    private Double priceOfBuy;

    private Double priceOfSell;

    private Boolean isActive = true;

    private Long productId;
 }



