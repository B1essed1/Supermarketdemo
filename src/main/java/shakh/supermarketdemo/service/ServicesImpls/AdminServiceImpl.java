
package shakh.supermarketdemo.service.ServicesImpls;
/*
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.repository.AdminRepository;
import shakh.supermarketdemo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService /*, UserDetailsService */{

   private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;

    }
/*
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admins user = adminRepository.findByUsername(s);

        if (user == null) throw new UsernameNotFoundException("User Not Found In Database");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);

    }

   */ @Override
    public Admins save(Admins admins)
    {
        //admins.setPassword(passwordEncoder.encode(admins.getPassword()));
        return adminRepository.save(admins);
    }

    @Override
    public void delete(Long id) {
         adminRepository.deleteById(id);
    }
}

