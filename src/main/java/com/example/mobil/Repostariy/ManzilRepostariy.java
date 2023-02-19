package com.example.mobil.Repostariy;

import com.example.mobil.Entity.Manzil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManzilRepostariy extends JpaRepository<Manzil,Integer> {
    Optional<Manzil> findByViloyat(String viloyat);
    Optional<Manzil> findById(Integer id);
}
