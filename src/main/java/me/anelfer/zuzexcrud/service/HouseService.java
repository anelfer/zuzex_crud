package me.anelfer.zuzexcrud.service;

import me.anelfer.zuzexcrud.model.House;
import me.anelfer.zuzexcrud.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    public List<House> getHouses() {
        return houseRepository.findAll();
    }

    public House getHouseById(Long id) {
        return houseRepository.findById(id).orElseThrow(() -> new RuntimeException("House not found"));
    }

    public void createHouse(House house) {
        houseRepository.save(house);
    }

    public void updateHouse(Long id, House house) {
        House existingHouse = houseRepository.findById(id).orElseThrow(() -> new RuntimeException("House not found"));
        existingHouse.setAddress(house.getAddress());
        existingHouse.setOwner(house.getOwner());
        houseRepository.save(existingHouse);
    }

    public void deleteHouse(Long id) {
        houseRepository.deleteById(id);
    }
}
