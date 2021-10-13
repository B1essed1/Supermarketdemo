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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private ProductOrder productOrder;

    @ManyToOne
    private Product product;

    private double amount ;

}
