
package shakh.supermarketdemo.service.ServicesImpls;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService , UserDetailsService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admins admin = adminRepository.findByUsername(username);
        if(admin  == null) {
            log.error("Admin not  found in database");
            throw new UsernameNotFoundException("Admin not found in Db");
        } else {
            log.info("user found in database {}" , admin);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        admin.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new User(admin.getUsername(), admin.getPassword(), authorities);
    }

    @Override
    public Admins save(Admins admins)
    {
        admins.setPassword(passwordEncoder.encode(admins.getPassword()));
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
    public Admins getAdminByUsername(String name) {
        return adminRepository.findByUsername(name);
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

