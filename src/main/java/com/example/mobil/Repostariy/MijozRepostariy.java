package com.example.mobil.Repostariy;

import com.example.mobil.Entity.Meijoz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MijozRepostariy extends JpaRepository<Meijoz,Integer> {
    Optional<Meijoz> findByUsername(String username);
}
