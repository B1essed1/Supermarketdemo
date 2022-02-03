package shakh.supermarketdemo.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double paidCost;

    private Double totalCost;

    private Date createdTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productOrder")
    @JsonManagedReference
    List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST,targetEntity = Debitors.class)
    @JoinColumn( name = "debitors_id",nullable = true)
    private Debitors debitors;

    private Boolean isActive = true ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productOrder")
    private List<Payment> payment=  new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST, targetEntity = Admins.class)
    @JoinColumn(name = "admins_id",nullable = true)
    private Admins admins;
}