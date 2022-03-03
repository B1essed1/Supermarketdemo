
package shakh.supermarketdemo.service.ServicesImpls;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.repository.AdminRepository;
import shakh.supermarketdemo.service.AdminService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService /*, UserDetailsService*/ {

   private final AdminRepository adminRepository;
   /*private final PasswordEncoder passwordEncoder;*/

    public AdminServiceImpl(AdminRepository adminRepository/*, PasswordEncoder passwordEncoder*/) {
        this.adminRepository = adminRepository;

/*        this.passwordEncoder = passwordEncoder;*/
    }
   /* @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admins user = adminRepository.findByUsername(s);

        if (user == null) throw new UsernameNotFoundException("User Not Found In Database");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);

    }*/

    @Override
    public Admins save(Admins admins)
    {
       // admins.setPassword(passwordEncoder.encode(admins.getPassword()));
        return adminRepository.save(admins);
    }

    @Override
    public void delete(Long id) {
         adminRepository.deleteById(id);
    }

    @Override
    public Admins getAdminsByOrder(ProductOrder order)
    {
        Admins admins =adminRepository.findAdminsByOrders(order);
        if (admins == null) throw new RuntimeException("Admin Not Found");
           else
               return admins;
    }

    @Override
    public Admins getAdminById(Long id)  {
        Optional<Admins> admins = adminRepository.findById(id);

        if (admins.isEmpty()) throw new RuntimeException("bu Id boyicha Admin mavjud emas ");
        else return admins.get();
    }

    @Override
    public List<Admins> getAllAdmins() {
       List<Admins> admins = new ArrayList<>();
       adminRepository.findAll().forEach(admins::add);
       if (admins.isEmpty()) throw new RuntimeException("adminlar qoshilmagan , iltimos yangi admin qoshing ");
        else return admins;
    }
}

