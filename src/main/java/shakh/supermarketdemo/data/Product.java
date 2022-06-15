package shakh.supermarketdemo.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer barcode;

    @Size(min = 5, message = "kamida 5 ta belgidan tashkil topgan bo'lishi kerak !")
    private String productName;

    private Double priceOfBuy;

    private Double priceOfSell;

    private Boolean measureType;

    private Boolean isActive = true;

    @PositiveOrZero(message = "qiymat doim noldan katta bo'lishi kerak")
    private Double amount;

    private Date createdTime;

    private Date lastUpdatedTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonManagedReference(value = "product-unload")
    private Set<Unload> unload = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonManagedReference(value = "product-item")
    private List<OrderItem> orderItemSet = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category categories;

}
