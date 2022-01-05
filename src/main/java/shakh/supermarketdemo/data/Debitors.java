package shakh.supermarketdemo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Debitors
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id ;

    //@Min(value = 10 , message = " min fullName value is 10 ")
    private String fullName;

    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "debitors" )
    private List<ProductOrder> orderList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "debitors")
    private List<Payment> payment =  new ArrayList<>();

}
