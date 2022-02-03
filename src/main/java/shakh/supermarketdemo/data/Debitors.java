package shakh.supermarketdemo.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Proxy(lazy = false  )
public class Debitors
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    //@Min(value = 10, message = " min fullName value is 10 ")
    private String fullName;

    private boolean isActive = true ;

    private String phoneNumber;

    @OneToMany(mappedBy = "debitors",cascade = CascadeType.ALL,orphanRemoval = true )
    private List<ProductOrder> orderList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "debitors")
    private List<Payment> payment =  new ArrayList<>();

}
