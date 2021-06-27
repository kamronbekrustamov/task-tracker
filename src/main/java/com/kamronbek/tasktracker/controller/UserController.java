package com.kamronbek.tasktracker.controller;
import com.kamronbek.tasktracker.dto.AuthAndRegistrationResponse;
import com.kamronbek.tasktracker.dto.RegistrationRequest;
import com.kamronbek.tasktracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/authenticate")
    public AuthAndRegistrationResponse authenticate() {
        return userService.authenticate();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        return userService.register(request);
    }
}