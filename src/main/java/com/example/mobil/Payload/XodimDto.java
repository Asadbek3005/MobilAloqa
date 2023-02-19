package com.example.mobil.Payload;

import com.example.mobil.Entity.Filial;
import com.example.mobil.Entity.Lavozim;
import com.example.mobil.Entity.Manzil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
public class XodimDto {
    private String fish;
    private String username;
    private String password;
    private Integer manzil;
    private Integer filial;
    private Integer lavozim;
}
