package com.example.mobil.Entity;

import com.example.mobil.Entity.Abstarkat.Abstrakt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Meijoz extends Abstrakt{
    @Column(nullable = false)
    private String fish;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToOne
    private Lavozim lavozim;
    @ManyToOne
    private Manzil manzil;
    @ManyToOne
    private SimKarta simKarta;

}
