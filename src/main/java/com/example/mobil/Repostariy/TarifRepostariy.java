package com.example.mobil.Repostariy;

import com.example.mobil.Entity.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TarifRepostariy extends JpaRepository<Tarif,Integer> {
    Optional<Tarif> findByNomi(String nomi);
    Optional<Tarif> findById(Integer id);
}
