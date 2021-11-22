package shakh.supermarketdemo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter

public class Debitors
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id ;

    @Min(value = 10 , message = "ism- familya kamida 10 ta belgidan tashkil topgan bo'lishi kerak ")
    private String fullName;
    private String phonenumber;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user" )
    private List<ProductOrder> orderSet = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Payment> payment =  new ArrayList<>();
}
