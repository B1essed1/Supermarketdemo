package shakh.supermarketdemo.dto;

import lombok.Data;

@Data
public class ProductAddDto {
    private Double amount;
    private Integer barcode;
    private String productName;
    private Boolean measureType;
    private String category;
}
