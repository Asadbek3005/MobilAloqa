package com.example.mobil.Repostariy;

import com.example.mobil.Entity.Xodim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface XodimRepostariy extends JpaRepository<Xodim,Integer> {
    Optional<Xodim> findByUsername(String username);
}
