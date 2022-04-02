package shakh.supermarketdemo.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.data.securitymodel.Role;
import shakh.supermarketdemo.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/admins/")
@Slf4j
public class AdminController
{
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping(value = "login")
    public ResponseEntity<Admins> addingNewSeller(@RequestBody Admins admin)  {

        Admins admins= adminService.save(admin);
        if (admins == null) throw  new RuntimeException("Could Not SAve Admin");

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

    @GetMapping("refresh/token")
    public  void getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("itisnotsecretkeythatyousearch,hehe".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();

                Admins admin = adminService.getAdminByUsername(username);
                String access_token = JWT.create()
                        .withSubject(admin.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 5))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", admin.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);


            } catch (Exception e) {
                log.error("error logging in - -> {}", e.getMessage());
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value(), "error");
                /*response.sendError(FORBIDDEN.value());*/
                Map<String, String> error_message = new HashMap<>();
                error_message.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), error_message);
            }
        } else {
            throw new RuntimeException("Refresh token missing for some kind of exception");
        }
    }


}
