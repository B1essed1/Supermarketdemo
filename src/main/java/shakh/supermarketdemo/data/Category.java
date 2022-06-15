package shakh.supermarketdemo.data;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;

    @OneToMany(mappedBy = "categories")
    private Collection<Product> productsC= new ArrayList<>();

}
