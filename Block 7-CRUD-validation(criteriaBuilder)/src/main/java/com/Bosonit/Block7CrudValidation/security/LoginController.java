package com.Bosonit.Block7CrudValidation.security;

import com.Bosonit.Block7CrudValidation.domain.Persona;
import com.Bosonit.Block7CrudValidation.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    JwtToken jwtToken;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
        String username = requestMap.get("username");
        String password = requestMap.get("password");

        Persona persona = personaRepository.findByUsuario(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Persona not found with username: " + username
                ));

        if (!persona.getPassword().equals(password)) {
            throw new EntityNotFoundException("Passwords do not match");
        }

        String role = Boolean.TRUE.equals(persona.isAdmin())
                ? "ROLE_ADMIN"
                : "ROLE_USER";

        return new ResponseEntity<>(jwtToken.generateToken(username, role), HttpStatus.OK);
    }
}
