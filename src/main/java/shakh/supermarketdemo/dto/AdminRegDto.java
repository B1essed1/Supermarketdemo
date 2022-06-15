package shakh.supermarketdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AdminRegDto {
    private String firsName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private String roleName;
    private Boolean isActive;
}
