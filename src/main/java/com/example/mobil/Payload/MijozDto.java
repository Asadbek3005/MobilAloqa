package com.example.mobil.Payload;

import com.example.mobil.Entity.Lavozim;
import com.example.mobil.Entity.Manzil;
import com.example.mobil.Entity.SimKarta;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
public class MijozDto {
    private String fish;
    private String username;
    private String password;
    private Integer lavozim;
    private Integer manzil;
    private Integer simKarta;
}
