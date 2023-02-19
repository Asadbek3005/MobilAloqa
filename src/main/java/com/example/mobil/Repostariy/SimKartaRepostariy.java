package com.example.mobil.Repostariy;

import com.example.mobil.Entity.SimKarta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimKartaRepostariy extends JpaRepository<SimKarta,Integer> {
    Optional<SimKarta> findByRaqam(String raqam);
    Optional<SimKarta> findById(Integer id);
}
