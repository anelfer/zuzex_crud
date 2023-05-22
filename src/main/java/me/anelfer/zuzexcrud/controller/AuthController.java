package me.anelfer.zuzexcrud.controller;

import me.anelfer.zuzexcrud.jwt.JWTComponent;
import me.anelfer.zuzexcrud.model.Citizen;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final JWTComponent jwtComponent;

    public AuthController(JWTComponent jwtComponent) {
        this.jwtComponent = jwtComponent;
    }

    @PostMapping("/auth")
    public String auth(@RequestBody Citizen citizen) {
        return jwtComponent.generateToken(citizen.getId().toString());
    }

}
