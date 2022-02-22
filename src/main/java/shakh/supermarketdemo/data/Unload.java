package shakh.supermarketdemo.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
public class Unload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private Double priceOfBuy;

    private Double priceOfSell;

    private Boolean isActive = true;

    private Date createdTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "product-unload")
    private Product product;
}
