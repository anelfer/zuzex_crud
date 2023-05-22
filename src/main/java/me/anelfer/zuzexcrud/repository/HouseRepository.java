package me.anelfer.zuzexcrud.repository;

import me.anelfer.zuzexcrud.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {



}
