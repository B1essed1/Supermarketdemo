package shakh.supermarketdemo.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Proxy(lazy = false)
public class Debitors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Min(value = 10, message = " min fullName value is 10 ")
    private String firstName;

    private String lastName;

    private String additionalDetails;

    private Boolean isActive = true;

    private String phoneNumber;

    private Date createdDate;

    @OneToMany(mappedBy = "debitors", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "debitor-order")
    private List<ProductOrder> orderList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "debitors")
    @JsonManagedReference(value = "debitor-payment")
    private List<Payment> payment = new ArrayList<>();

}
