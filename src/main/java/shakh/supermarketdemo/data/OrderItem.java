package shakh.supermarketdemo.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private long id;

    private boolean isActive = true ;

    private double priceOfBuy;

    private double priceOfSell;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="product_order_id", nullable = true)
    @JsonBackReference
    private ProductOrder productOrder;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn( name = "product_id", nullable = true)
    private Product product;

    private double amount ;

}
