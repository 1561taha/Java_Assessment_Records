package com.Record.CRUD.Controller;

import com.Record.CRUD.Model.LoginRequest;
import com.Record.CRUD.Model.RegisterRequest;
import com.Record.CRUD.Model.User;
import com.Record.CRUD.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authManager;
    @Value("${jwt.secret}")
    private String secret;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userService.findByEmail(req.getEmail()).isPresent())
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists"));
        User user = new User(null, req.getName(), req.getEmail(), req.getPassword(), req.getGender());
        userService.save(user);
        return ResponseEntity.ok(Map.of("success", true));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
            User user = userService.findByEmail(req.getEmail()).orElseThrow();
            String token = Jwts.builder()
                    .setSubject(user.getEmail())
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
            return ResponseEntity.ok(Map.of("token", token, "name", user.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
        }
    }
}
