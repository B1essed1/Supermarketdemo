package shakh.supermarketdemo.data;

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
    @JoinColumn(referencedColumnName = "product_order_id",name="product_order_id",insertable = false,updatable = false)
    private ProductOrder productOrder;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "product_id", name = "product_id",insertable = false,updatable = false)
    private Product product;

    private double amount ;

}
