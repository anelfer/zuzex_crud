package me.anelfer.zuzexcrud.controller;

import me.anelfer.zuzexcrud.model.Citizen;
import me.anelfer.zuzexcrud.service.CitizenService;
import me.anelfer.zuzexcrud.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CitizenController {

    private final CitizenService citizenService;

    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @GetMapping("/users/{id}")
    public Citizen getUserById(@PathVariable Long id) {
        System.out.println("id = " + id);
        Citizen currentUser = UserUtils.getCurrentUser();
        System.out.println("currentUser = " + currentUser);
        return citizenService.getUserById(id);
    }

    @PostMapping("/user")
    public Citizen createUser(@RequestBody Citizen user) {
        System.out.println("user = " + user);
        return citizenService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody Citizen user) {
        citizenService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        citizenService.deleteUser(id);
    }
}
