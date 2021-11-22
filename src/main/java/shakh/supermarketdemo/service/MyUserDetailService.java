package shakh.supermarketdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.Admins;
import shakh.supermarketdemo.repository.AdminRepository;
import shakh.supermarketdemo.security.MyUserDetail;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        Optional<Admins> admins = adminRepository.findAdminsByUsername(s);

        admins.orElseThrow( ()->new  UsernameNotFoundException(s+ " Not Found"));
        return admins.map(MyUserDetail::new).get();
    }
}
