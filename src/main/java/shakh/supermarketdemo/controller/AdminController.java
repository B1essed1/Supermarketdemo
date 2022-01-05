/*
package shakh.supermarketdemo.controller;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.service.AdminService;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class AdminController
{

    @Autowired
    private  AdminService adminService;


    @PostMapping("/login")
    public ResponseEntity<Admins> addingNewSeller(@RequestBody Admins admin) throws NotFoundException {

        Admins admins= adminService.save(admin);
        if (admins == null) throw  new NotFoundException("Could Not SAve Admin");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(admins.getId()).toUri();

       return ResponseEntity.created(location).build();
    }
}
*/
