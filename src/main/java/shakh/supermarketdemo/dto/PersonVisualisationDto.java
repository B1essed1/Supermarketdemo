package shakh.supermarketdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class PersonVisualisationDto {
    public PersonVisualisationDto(Long id, String firstName, String lastName, String username, String email, String phoneNumber, boolean isActive) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

    public PersonVisualisationDto(Long id ,String firstName, String lastName, String phoneNumber, String additionalDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.additionalDetails = additionalDetails;
        this.id=id;
    }

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private boolean isActive;
    private String additionalDetails;
}
