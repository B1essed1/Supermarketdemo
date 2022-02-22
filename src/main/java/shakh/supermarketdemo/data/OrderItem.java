package shakh.supermarketdemo.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
public class OrderItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isActive = true ;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double priceOfBuy;

    private Double priceOfSell;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="product_order_id", nullable = true)
    @JsonBackReference(value = "order-item")
    private ProductOrder productOrder;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn( name = "product_id")
    @JsonBackReference(value = "product-item")
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Product product;

    private Double amount ;

}
