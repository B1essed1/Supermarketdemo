package shakh.supermarketdemo.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import shakh.supermarketdemo.data.securitymodel.Admins;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class ProductOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private double paidCost;

    private double totalCost;

    private Date createdTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productOrder")
    List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Debitors debitors;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productOrder")
    private List<Payment> payment=  new ArrayList<>();


    @JsonBackReference
    @ManyToOne()
    private Admins admins;


}
