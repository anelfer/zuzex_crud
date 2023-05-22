package me.anelfer.zuzexcrud.service;

import me.anelfer.zuzexcrud.model.Citizen;
import me.anelfer.zuzexcrud.repository.CitizenRepository;
import me.anelfer.zuzexcrud.service.details.ZuzexUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenService implements UserDetailsService {

    private final CitizenRepository citizenRepository;

    @Autowired
    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public List<Citizen> getUsers() {
        return citizenRepository.findAll();
    }

    public Citizen getUserById(Long id) {
        return citizenRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Citizen createUser(Citizen user) {
        return citizenRepository.save(user);
    }

    public void updateUser(Long id, Citizen user) {
        Citizen existingUser = citizenRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        citizenRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        citizenRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String citizenId) throws UsernameNotFoundException {
        System.out.println("citizenId = " + citizenId);
        return new ZuzexUserDetails(citizenRepository
                .findById(Long.valueOf(citizenId))
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
