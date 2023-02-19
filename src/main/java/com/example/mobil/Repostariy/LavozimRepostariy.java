package com.example.mobil.Repostariy;

import com.example.mobil.Entity.Lavozim;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public interface LavozimRepostariy extends JpaRepository<Lavozim,Integer>{
    Optional<Lavozim> findByNomi(@NotBlank(message = "Lavozim nomida probel ishlatish mumkin emas") String nomi);
    Optional<Lavozim> findById(Integer id);
}
