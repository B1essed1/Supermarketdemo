package shakh.supermarketdemo.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shakh.supermarketdemo.data.securitymodel.AdminReserve;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.data.securitymodel.Role;
import shakh.supermarketdemo.dto.AdminRegDto;
import shakh.supermarketdemo.dto.PersonVisualisationDto;
import shakh.supermarketdemo.dto.RegConfirmationDto;
import shakh.supermarketdemo.service.AdminReserveService;
import shakh.supermarketdemo.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/admins/")
@Slf4j
public class AdminController {

    private final AdminService adminService;
    private final JavaMailSender javaMailSender;
    private final AdminReserveService adminReserveService;

    public AdminController(AdminService adminService, JavaMailSender javaMailSender, AdminReserveService adminReserveService) {
        this.adminService = adminService;
        this.javaMailSender = javaMailSender;
        this.adminReserveService = adminReserveService;
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Admins> getAdminById(@PathVariable("id") Long id) {

        Admins admin = adminService.getAdminById(id);
        if (admin != null) return ResponseEntity.ok(admin);
        else throw new RuntimeException("Admin not found");

    }

    @GetMapping("get/all")
    public ResponseEntity<List<PersonVisualisationDto>> getAllAdmins() {

        List<PersonVisualisationDto> admins = adminService.getAllAdmins();
        if (admins.isEmpty()) throw new RuntimeException(" adminlar mavjud emas , oldin admin yarating!");

        else return ResponseEntity.ok(admins);
    }

    @GetMapping("refresh/token")
    public void getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                        .withIssuer(String.valueOf((admin.getFirstName() + admin.getLastName()).hashCode()))
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

    @PostMapping("registration")
    ResponseEntity<?> registerAdmin(@RequestBody AdminRegDto adminRegDto) {

        AdminReserve adminReserve = new AdminReserve();

        Admins adminReserveExists = adminService.getAdminsByMail(adminRegDto.getEmail());
        if (adminReserveExists != null) {
            return new ResponseEntity<>("Bu email bilan allaqachon ro'yxatdan o'tilgan iltimos boshqa email kiriting", HttpStatus.BAD_REQUEST);
        }


        adminReserve.setFirsName(adminRegDto.getFirsName());
        adminReserve.setLastName(adminRegDto.getLastName());
        adminReserve.setEmail(adminRegDto.getEmail());
        adminReserve.setPassword(adminRegDto.getPassword());
        adminReserve.setPhoneNumber(adminRegDto.getPhoneNumber());
        adminReserve.setRoleName(adminRegDto.getRoleName());
        adminReserve.setUsername(adminRegDto.getUserName());

        Random random = new Random();
        Integer otp = random.nextInt(8999) + 1000;
        adminReserve.setOneTimePassword(otp.toString());
        adminReserve.setOtpRequestedTime(new Date(System.currentTimeMillis()));
        adminReserveService.save(adminReserve);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Tasdiqlash kodi");
        mailMessage.setText(otp.toString());
        mailMessage.setTo(adminRegDto.getEmail());
        javaMailSender.send(mailMessage);

        RegConfirmationDto response = new RegConfirmationDto();

        response.setOtp(otp.toString());
        response.setEmail(adminRegDto.getEmail());

        return ResponseEntity.ok(response);
    }

    @PostMapping("confirmation")
    public ResponseEntity<?> confirmRegistration(@RequestBody RegConfirmationDto confirmationDto){
        AdminReserve adminReserve = adminReserveService.findAdminReserveByMail(confirmationDto.getEmail());
        String reservedOtp = adminReserve.getOneTimePassword();
        String confirmationOtp =  confirmationDto.getOtp();
        if (Objects.equals(reservedOtp, confirmationOtp)){
            Long dateDiff = new Date(System.currentTimeMillis()).getTime()  - adminReserve.getOtpRequestedTime().getTime();
            if (dateDiff  >120000 ){
                return new ResponseEntity<>("belgilangan muddatdan otib ketti, iltimos qaytadan jo'nating", FORBIDDEN);
            } else {
                Admins admins = adminService.save(adminService.cast(adminReserve));

                Algorithm algorithm = Algorithm.HMAC256("itisnotsecretkeythatyousearch,hehe".getBytes());

                String access_token = JWT.create()
                        .withSubject(admins.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 5))
                        .withIssuer(String.valueOf(admins.getUsername().hashCode()))
                        .withClaim("roles",  admins.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                String refresh_token = JWT.create()
                        .withSubject(admins.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 60 * 24))
                        .withIssuer(String.valueOf(admins.getUsername().hashCode()))
                        .sign(algorithm);


                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                adminReserveService.deleteByMail(adminReserve.getEmail());

                return ResponseEntity.ok(tokens);
            }
        } else {
            return new   ResponseEntity<>("kod xato kiritilgan", FORBIDDEN);
        }
    }
    @PostMapping("/resend/otp")
    public ResponseEntity<?> resendOtp(@RequestBody RegConfirmationDto dto){
        RegConfirmationDto confirmationDto = new RegConfirmationDto();
        AdminReserve adminReserve = adminReserveService.findAdminReserveByMail(dto.getEmail());
        Random random = new Random();
        Integer otp = random.nextInt(8999) + 1000;
        adminReserve.setOneTimePassword(otp.toString());
        adminReserveService.save(adminReserve);
        confirmationDto.setEmail(dto.getEmail());
        confirmationDto.setOtp(otp.toString());
        return ResponseEntity.ok(confirmationDto);
    }
}
