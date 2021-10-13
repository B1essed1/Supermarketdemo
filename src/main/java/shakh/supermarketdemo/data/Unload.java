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
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @Column(name ="id")
    private Long id;

    private double amount;
    private double priceOfBuy;
    private LocalDateTime createdTime;

    @ManyToOne
    @JsonBackReference
    private Product product;
}
