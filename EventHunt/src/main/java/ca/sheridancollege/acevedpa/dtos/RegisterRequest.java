package ca.sheridancollege.acevedpa.dtos;

import ca.sheridancollege.acevedpa.domain.Roles;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String userEmail;
    private String password;
    private Roles role; // Optional if you're defaulting to Roles.USER
}
