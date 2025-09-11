package ca.sheridancollege.acevedpa.controllers;

import ca.sheridancollege.acevedpa.domain.User;
import ca.sheridancollege.acevedpa.security.JwtService;
import ca.sheridancollege.acevedpa.services.UserService;
import ca.sheridancollege.acevedpa.dtos.LoginRequest;
import ca.sheridancollege.acevedpa.dtos.RegisterRequest;
import ca.sheridancollege.acevedpa.dtos.AuthResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
    
    
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        System.out.println(" /test endpoint hit");
        return ResponseEntity.ok("Test works!");
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        System.out.println("REGISTER endpoint hit with: " + request);

        if (!userService.findByUserEmail(request.getUserEmail()).isEmpty()) {
            return ResponseEntity.status(400).body("Email already in use.");
        }

        User newUser = User.builder()
                .username(request.getUsername())
                .userEmail(request.getUserEmail())
                .userPassword(passwordEncoder.encode(request.getPassword()))
                .registrationDate(new java.util.Date())
                .role(request.getRole())
                .build();

        userService.save(newUser);
        return ResponseEntity.status(201).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println(" Attempting login for: " + request.getUserEmail());

        User user = userService.findByUserEmail(request.getUserEmail())
                .stream()
                .findFirst()
                .orElse(null);

        if (user == null) {
            System.out.println(" User not found");
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        System.out.println("Found user, checking password...");
        System.out.println("Raw input password: " + request.getPassword());
        System.out.println("Stored hash: " + user.getUserPassword());

        if (!passwordEncoder.matches(request.getPassword(), user.getUserPassword())) {
            System.out.println(" Password does not match");
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        System.out.println(" Password matches. Generating token...");

        String token = jwtService.generateToken(user.getUserEmail());
        return ResponseEntity.ok(new AuthResponse("Bearer " + token));
    }

}
