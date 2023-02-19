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
public class Manzil extends Abstrakt {
    @Column(nullable = false)
    private String viloyat;
    @Column(nullable = false)
    private String tuman;
    @Column(nullable = false)
    private String kocha;
    @Column(nullable = false)
    private String uy;
}
