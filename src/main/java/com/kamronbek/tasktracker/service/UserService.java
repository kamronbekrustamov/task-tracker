package com.kamronbek.tasktracker.service;
import com.kamronbek.tasktracker.dto.AuthAndRegistrationResponse;
import com.kamronbek.tasktracker.dto.RegistrationRequest;
import com.kamronbek.tasktracker.model.User;
import com.kamronbek.tasktracker.repository.UserRepository;
import com.kamronbek.tasktracker.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email " + email + " not found"));
    }


    public AuthAndRegistrationResponse authenticate() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = JwtUtils.generateToken(user);
        return new AuthAndRegistrationResponse(
                token,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }


    public ResponseEntity<?> register(RegistrationRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        User user = new User(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);
        String token = JwtUtils.generateToken(user);

        return ResponseEntity.ok().body(
                new AuthAndRegistrationResponse(
                        token,
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail()
                )
        );
    }
}