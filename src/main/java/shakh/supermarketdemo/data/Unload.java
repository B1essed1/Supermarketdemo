package shakh.supermarketdemo.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@Getter
@Setter
public class Unload
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private double priceOfBuy;
    private double priceOfSell;

    private boolean isActive = true ;


    private LocalDateTime createdTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    @JoinColumn(name = "product_id" , referencedColumnName = "product_id",insertable = false,updatable = false)
    private Product product;
}
