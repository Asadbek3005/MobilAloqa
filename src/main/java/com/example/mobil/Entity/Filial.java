package com.example.mobil.Entity;

import com.example.mobil.Entity.Abstarkat.Abstrakt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Filial extends Abstrakt {
    @Column(nullable = false)
    private String nomi;
    @ManyToOne
    private Manzil manzil;
}
