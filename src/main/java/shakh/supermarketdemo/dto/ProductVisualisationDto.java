package shakh.supermarketdemo.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ProductVisualisationDto {
     private Long id;
     private String productName;
     private Double priceOfSell;
     private Double priceOfBuy;
     private Double amount;
     private String category;

     public ProductVisualisationDto(Long id, String productName, Double priceOfSell, Double amount, String category) {
          this.id = id;
          this.productName = productName;
          this.priceOfSell = priceOfSell;
          this.amount = amount;
          this.category = category;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          ProductVisualisationDto that = (ProductVisualisationDto) o;
          return productName.equals(that.productName) && priceOfSell.equals(that.priceOfSell) && amount.equals(that.amount) && category.equals(that.category);
     }

     @Override
     public int hashCode() {
          return Objects.hash(productName, priceOfSell, amount, category);
     }
}
