package com.example.mobil.Servis;

import com.example.mobil.Entity.Lavozim;
import com.example.mobil.Entity.Manzil;
import com.example.mobil.Entity.Meijoz;
import com.example.mobil.Entity.SimKarta;
import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.MijozDto;
import com.example.mobil.Repostariy.LavozimRepostariy;
import com.example.mobil.Repostariy.ManzilRepostariy;
import com.example.mobil.Repostariy.MijozRepostariy;
import com.example.mobil.Repostariy.SimKartaRepostariy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MijozServis {
    @Autowired
    MijozRepostariy mijozRepostariy;
    @Autowired
    ManzilRepostariy manzilRepostariy;
    @Autowired
    LavozimRepostariy lavozimRepostariy;
    @Autowired
    SimKartaRepostariy simKartaRepostariy;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Apirespons add(MijozDto mijozDto) {
        Optional<Meijoz> byUsername = mijozRepostariy.findByUsername(mijozDto.getUsername());
        if (byUsername.isPresent()){
            return new Apirespons("Ma'lumotni qaytadan kirting xatolik yuz berdi",false);
        }
        Meijoz meijoz=new Meijoz();
        meijoz.setFish(mijozDto.getFish());
        meijoz.setUsername(mijozDto.getUsername());
        meijoz.setPassword(passwordEncoder.encode(mijozDto.getPassword()));
        Optional<Manzil> byId1 = manzilRepostariy.findById(mijozDto.getManzil());
        if (byId1.isPresent()){
            Manzil manzil = byId1.get();
            meijoz.setManzil(manzil);
        }
        Optional<Lavozim> byId = lavozimRepostariy.findById(mijozDto.getLavozim());
        if (byId.isPresent()){
            Lavozim lavozim = byId.get();
            meijoz.setLavozim(lavozim);
        }
        Optional<SimKarta> byId2 = simKartaRepostariy.findById(mijozDto.getSimKarta());
        if (byId2.isPresent()){
            SimKarta simKarta = byId2.get();
            meijoz.setSimKarta(simKarta);
        }
        mijozRepostariy.save(meijoz);
        return new Apirespons("Ma'lumotingiz muvaqiyatli joylandi:",true);
    }

    public Apirespons view() {
        List<Meijoz> all = mijozRepostariy.findAll();
        return new Apirespons(all.toString(),true);
    }

    public Apirespons viewId(Integer id) {
        Optional<Manzil> byId = manzilRepostariy.findById(id);
        if (byId.isPresent()){
            return new Apirespons(byId.get().toString(),true);
        }
        return new Apirespons("Ma'lumotingizni qyatdan kiting xatolik yuz berdi",false);
    }

    public Apirespons edit(MijozDto mijozDto, Integer id) {
        Optional<Meijoz> byId = mijozRepostariy.findById(id);
        if (byId.isPresent()){
            Meijoz meijoz = byId.get();
            meijoz.setFish(mijozDto.getFish());
            meijoz.setUsername(mijozDto.getUsername());
            meijoz.setPassword(passwordEncoder.encode(mijozDto.getPassword()));
            Optional<Lavozim> byId1 = lavozimRepostariy.findById(mijozDto.getLavozim());
            if (byId.isPresent()){
                Lavozim lavozim = byId1.get();
                meijoz.setLavozim(lavozim);
            }
            Optional<Manzil> byId2 = manzilRepostariy.findById(mijozDto.getManzil());
            if (byId2.isPresent()){
                Manzil manzil = byId2.get();
                meijoz.setManzil(manzil);
            }
            Optional<SimKarta> byId3 = simKartaRepostariy.findById(mijozDto.getSimKarta());
            if (byId3.isPresent()){
                SimKarta simKarta = byId3.get();
                meijoz.setSimKarta(simKarta);
            }
            mijozRepostariy.save(meijoz);
            return new Apirespons("Ma'lumotingiz taxrilandi:",true);
        }
        return new Apirespons("Ma'lumotingizni qayatdan kirting xatolik yuz berdi",false);
    }
}
