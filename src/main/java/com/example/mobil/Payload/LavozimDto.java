package com.example.mobil.Payload;

import com.example.mobil.Entity.Enum.Huquqlar;
import lombok.Data;

import java.util.List;

@Data
public class LavozimDto {
    private String nomi;
    private List<Huquqlar> huquqlarList;
}
