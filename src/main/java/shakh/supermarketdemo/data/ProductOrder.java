package shakh.supermarketdemo.data;

import lombok.Getter;
import lombok.Setter;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productOrder")
    List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne()
    private User user;

    private double paidCost;
    private double totalCost;

    private Date createdTime;
}
