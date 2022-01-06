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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name= "debitor_id")
    private Debitors debitors;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private ProductOrder productOrder;

    private Date createdTime;
}
