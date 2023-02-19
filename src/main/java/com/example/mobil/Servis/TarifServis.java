package com.example.mobil.Servis;

import com.example.mobil.Entity.Tarif;
import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.TarifDto;
import com.example.mobil.Repostariy.TarifRepostariy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifServis {
    @Autowired
    TarifRepostariy tarifRepostariy;

    public Apirespons add(TarifDto tarifDto) {
        Optional<Tarif> byNomi = tarifRepostariy.findByNomi(tarifDto.getNomi());
        if (byNomi.isPresent()){
            return new Apirespons("Ma'lumotingizni qaytadan kirting",false);
        }
        Tarif tarif=new Tarif();
        tarif.setNomi(tarifDto.getNomi());
        tarif.setNarxi(tarifDto.getNarxi());
        tarif.setSrok(tarifDto.getSrok());
        tarifRepostariy.save(tarif);
        return new Apirespons("Ma'lumotingiz muvaqiyatli joylandi:",true);
    }

    public Apirespons korish() {
        List<Tarif> all = tarifRepostariy.findAll();
        return new Apirespons(all.toString(),true);
    }

    public Apirespons id(Integer id) {
        Optional<Tarif> byId = tarifRepostariy.findById(id);
        if (byId.isPresent()){
            return new Apirespons(byId.get().toString(),true);
        }
        return new Apirespons("Qyatadan koring xatolik yuz bedi",false);
    }

    public Apirespons edit(TarifDto tarifDto, Integer id) {
        Optional<Tarif> byId = tarifRepostariy.findById(id);
        if (byId.isPresent()){
            Tarif tarif = byId.get();
            tarif.setNomi(tarifDto.getNomi());
            tarif.setNarxi(tarifDto.getNarxi());
            tarif.setSrok(tarifDto.getSrok());
            return new Apirespons("Ma'lumotingiz taxrilandi:",true);
        }
        return new Apirespons("Qayatdan koring xatolik yuz berdi",false);
    }
}
