package shakh.supermarketdemo.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long id;


    private Boolean isActive = true ;

    private Double totalPaidCost;

    private Date createdTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "debitors_id",nullable = true)
    @JsonBackReference(value = "debitor-payment")
    private Debitors debitors;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_order_id",nullable = true)
    @JsonBackReference(value = "order-payment")
    private ProductOrder productOrder;

}
