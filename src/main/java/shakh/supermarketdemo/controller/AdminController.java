package shakh.supermarketdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.Admins;
import shakh.supermarketdemo.service.AdminService;

import java.net.URI;

@RestController
public class AdminController
{

    @Autowired
    private  AdminService adminService;


    @PostMapping("/login")
    public ResponseEntity<Admins> addingNewSeller(@RequestBody Admins admin)
    {

        Admins admins= adminService.save(admin);
        if (admins == null) throw  new UsernameNotFoundException("Could Not SAve Admin");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(admins.getId()).toUri();


       return ResponseEntity.created(location).build();
    }
}
