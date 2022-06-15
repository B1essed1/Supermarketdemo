
package shakh.supermarketdemo.service.ServicesImpls;

import lombok.AllArgsConstructor;
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
import shakh.supermarketdemo.data.securitymodel.AdminReserve;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.data.securitymodel.Role;
import shakh.supermarketdemo.dto.AdminRegDto;
import shakh.supermarketdemo.dto.PersonVisualisationDto;
import shakh.supermarketdemo.repository.AdminRepository;
import shakh.supermarketdemo.service.AdminService;
import shakh.supermarketdemo.service.RoleService;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService , UserDetailsService {


    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


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
    public Admins getAdminsByMail(String mail) {
        return adminRepository.findByEmail(mail);
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
    public List<PersonVisualisationDto> getAllAdmins() {
       List<PersonVisualisationDto> admins = new ArrayList<>();
       adminRepository.getAllAdmins().forEach(admins::add);
       if (admins.isEmpty()) throw new RuntimeException("adminlar qoshilmagan , iltimos yangi admin qoshing ");
        else return admins;
    }

    @Override
    public Admins cast(AdminReserve adminReserve) {
        Admins admins = new Admins();
        admins.setPassword(adminReserve.getPassword());
        admins.setEmail(adminReserve.getEmail());
        admins.setFirstName(adminReserve.getFirsName());
        admins.setLastName(adminReserve.getLastName());
        admins.setOneTimePassword(adminReserve.getOneTimePassword());
        admins.setPhoneNumber(adminReserve.getPhoneNumber());
        admins.setCreatedTime(new Date(System.currentTimeMillis()));
        admins.setActive(true);
        admins.setUsername(adminReserve.getUsername());
        admins.setOtpRequestedTime(adminReserve.getOtpRequestedTime());

        if (roleService.getRoleByName(adminReserve.getRoleName()) == null){
            Role role = new Role();
            role.setName(adminReserve.getRoleName());
            roleService.save(role);
            admins.setRoles(Collections.singletonList(role));
        } else {
            Role role = roleService.getRoleByName(adminReserve.getRoleName());
            admins.setRoles(Collections.singletonList(role));
        }
        return admins;
    }
}

