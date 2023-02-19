package com.example.mobil.Entity;

import com.example.mobil.Entity.Abstarkat.Abstrakt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tarif extends Abstrakt {
    @Column(nullable = false)
    private String nomi;
    @Column(nullable = false)
    private String narxi;
    @Column(nullable = false)
    private String srok;
}
