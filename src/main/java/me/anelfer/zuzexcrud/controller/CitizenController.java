package me.anelfer.zuzexcrud.controller;

import me.anelfer.zuzexcrud.model.Citizen;
import me.anelfer.zuzexcrud.service.CitizenService;
import me.anelfer.zuzexcrud.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class CitizenController {

    private final CitizenService citizenService;

    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @GetMapping("/{id}")
    public Citizen getUserById(@PathVariable Long id) {
        return citizenService.getUserById(id);
    }

    @PostMapping("/")
    public Citizen createUser(@RequestBody Citizen user) {
        return citizenService.createUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody Citizen user) {
        citizenService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        citizenService.deleteUser(id);
    }
}
