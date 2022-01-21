package shakh.supermarketdemo.controller;


import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.service.AdminService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admins/")
public class AdminController
{
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("login")
    public ResponseEntity<Admins> addingNewSeller(@RequestBody Admins admin) throws NotFoundException {

        Admins admins= adminService.save(admin);
        if (admins == null) throw  new NotFoundException("Could Not SAve Admin");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(admins.getId()).toUri();

       return ResponseEntity.created(location).build();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Admins> getAdminById(@PathVariable("id") Long id) {
        Admins admin = adminService.getAdminById(id);

        if (admin != null) return ResponseEntity.ok(admin);
        else throw  new RuntimeException("Admin not found");
    }

    @GetMapping("get/all")
    public ResponseEntity<List<Admins>> getAllAdmins(){

        List<Admins> admins = adminService.getAllAdmins();
        if (admins.isEmpty()) throw new  RuntimeException(" adminlar mavjud emas , oldin admin yarating!");

        else return ResponseEntity.ok(admins);
    }


}
