package shakh.supermarketdemo.model;

import lombok.Data;

@Data
public class ProductCreating {
    private Integer barcode ;
    private String name ;
    private Boolean measureType;
}
