package shakh.supermarketdemo.data;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id ;


    @Column(name = "barcode")
    private int barcode;

    @Size(min = 5, message = "kamida 5 ta belgidan tashkil topgan bo'lishi kerak !")
    @Column(name = "product_name")
    private String productName ;

    @Column(name = "price_of_sell")
    private double priceOfSell;

    @Column(name ="price_of_buy")
    private double priceOfBuy;

    @Column(name = "measure_type")
    private boolean measureType;


    @Column(name = "quantity")
    @PositiveOrZero(message = "qiymat doim noldan katta bo'lishi kerak")
    private double quantity;


    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "last_updated_time")
    private LocalDateTime lastUpdatedTime;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    @JsonManagedReference
    private Set<Unload> unload= new HashSet<>();

    @OneToMany(cascade =CascadeType.ALL ,mappedBy = "product" )
    private List<OrderItem> orderItemSet = new ArrayList<>();


}
