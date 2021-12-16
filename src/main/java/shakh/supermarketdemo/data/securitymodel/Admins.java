package shakh.supermarketdemo.data.securitymodel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Admins
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String password;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    } ,fetch = FetchType.EAGER)
    @JoinTable(
            name = "Admins_Role",
            joinColumns = { @JoinColumn(name = "admin_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    @JsonManagedReference
    Collection<Role> roles = new ArrayList<>();


}
