package com.example.mobil.Servis;

import com.example.mobil.Entity.SimKarta;
import com.example.mobil.Entity.Tarif;
import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.SimkartaDto;
import com.example.mobil.Repostariy.SimKartaRepostariy;
import com.example.mobil.Repostariy.TarifRepostariy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimKartaServis {
    @Autowired
    SimKartaRepostariy simKartaRepostariy;
    @Autowired
    TarifRepostariy tarifRepostariy;

    public Apirespons add(SimkartaDto simkartaDto) {
        Optional<SimKarta> byRaqam = simKartaRepostariy.findByRaqam(simkartaDto.getRaqami());
        if (byRaqam.isPresent()){
            return new Apirespons("Ma'lumotni qayatdan kirting",false);
        }
        SimKarta simKarta=new SimKarta();
        simKarta.setRaqam(simkartaDto.getRaqami());
        simKarta.setNarxi(simkartaDto.getNarxi());
        Optional<Tarif> byId = tarifRepostariy.findById(simkartaDto.getTarif());
        if (byId.isPresent()){
            Tarif tarif = byId.get();
            simKarta.setTarif(tarif);
        }
        simKartaRepostariy.save(simKarta);
        return new Apirespons("Ma'lumotingiz muvaqiyatli joylandi",true);
    }

    public Apirespons view() {
        List<SimKarta> all = simKartaRepostariy.findAll();
        return new Apirespons(all.toString(),true);
    }

    public Apirespons viewId(Integer id) {
        Optional<SimKarta> byId = simKartaRepostariy.findById(id);
        if (byId.isPresent()){
            return new Apirespons(byId.get().toString(),true);
        }
        return new Apirespons("Ma'lumotnigizni qyatdan kirting xatolik yuz berdi",false);
    }

    public Apirespons edit(SimkartaDto simkartaDto, Integer id) {
        Optional<SimKarta> byId = simKartaRepostariy.findById(id);
        if (byId.isPresent()){
            SimKarta simKarta = byId.get();
            simKarta.setRaqam(simkartaDto.getRaqami());
            simKarta.setNarxi(simkartaDto.getNarxi());
            Optional<Tarif> byId1 = tarifRepostariy.findById(simkartaDto.getTarif());
            if (byId1.isPresent()){
                Tarif tarif = byId1.get();
                simKarta.setTarif(tarif);
            }
            return new Apirespons("Ma'lumotingiz taxrilandi:",true);
        }
        return new Apirespons("Ma'lumotingiz qayatdan kirting xayolik yuz berdi",false);
    }
}
