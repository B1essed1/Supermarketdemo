package shakh.supermarketdemo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private boolean isActive = true ;

    private double amount;

    private Date createdTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "debitors_id", referencedColumnName = "debitors_id",insertable = false,updatable = false)
    private Debitors debitors;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_order_id",referencedColumnName = "product_order_id",insertable = false,updatable = false)
    private ProductOrder productOrder;

}
