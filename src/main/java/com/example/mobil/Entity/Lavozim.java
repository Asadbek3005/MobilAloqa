package com.example.mobil.Entity;

import com.example.mobil.Entity.Abstarkat.Abstrakt;
import com.example.mobil.Entity.Enum.Huquqlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Lavozim extends Abstrakt {

    @NotBlank(message = "Lavozim nomida probel ishlatish mumkin emas")
    @Column(nullable = false)
    private String nomi;
    @NotEmpty(message = "huquqlar listi bo'sh bo'lmasligi kerak !")
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Huquqlar> huquqlarList;


}
