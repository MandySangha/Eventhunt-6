package ca.sheridancollege.acevedpa.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String userEmail;
    private String password;
}
