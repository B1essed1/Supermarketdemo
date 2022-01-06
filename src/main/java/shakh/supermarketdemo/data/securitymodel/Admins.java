package shakh.supermarketdemo.data.securitymodel;

import lombok.Getter;
import lombok.Setter;
import shakh.supermarketdemo.data.ProductOrder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Admins
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy ="admins" ,orphanRemoval = true,cascade = CascadeType.ALL)
    List<ProductOrder> orders = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    } ,fetch = FetchType.EAGER)
    @JoinTable(
            name = "Admins_Role",
            joinColumns = { @JoinColumn(name = "admin_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    Collection<Role> roles = new ArrayList<>();

}
