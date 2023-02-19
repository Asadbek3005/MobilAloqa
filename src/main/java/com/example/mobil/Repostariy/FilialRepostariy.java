package com.example.mobil.Repostariy;

import com.example.mobil.Entity.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FilialRepostariy extends JpaRepository<Filial,Integer> {
    Optional<Filial> findByNomi(String nomi);
    Optional<Filial> findById(Integer id);

}
