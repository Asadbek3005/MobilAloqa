package com.example.mobil.Servis;

import com.example.mobil.Entity.Lavozim;
import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.LavozimDto;
import com.example.mobil.Repostariy.LavozimRepostariy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LavozimServis {
    @Autowired
    LavozimRepostariy lavozimRepostariy;
    public Apirespons add(LavozimDto lavozimDto) {
        Optional<Lavozim> byNomi = lavozimRepostariy.findByNomi(lavozimDto.getNomi());
        if (byNomi.isPresent()){
            return new Apirespons("Ma'lumotni qayatdan kiriting",false);
        }
        Lavozim lavozim=new Lavozim();
        lavozim.setNomi(lavozimDto.getNomi());
        lavozim.setHuquqlarList(lavozimDto.getHuquqlarList());
        lavozimRepostariy.save(lavozim);
        return new Apirespons("Ma'lumotingiz muvaqiyatli joylandi",true);
    }

    public Apirespons edit(LavozimDto lavozimDto, Integer id) {
        Optional<Lavozim> byId = lavozimRepostariy.findById(id);
        if (byId.isPresent()){
            Lavozim lavozim = byId.get();
            lavozim.setNomi(lavozimDto.getNomi());
            lavozim.setHuquqlarList(lavozimDto.getHuquqlarList());
            lavozimRepostariy.save(lavozim);
            return new Apirespons("Ma'lumotingiz taxrilandi:",true);
        }
        return new Apirespons("Ma'lumotingiz taxrilmadi qaytadan kiritng",false);
    }

    public Apirespons view() {
        List<Lavozim> all = lavozimRepostariy.findAll();
        return new Apirespons(all.toString(),true);
    }

    public Apirespons viewId(Integer id) {
        Optional<Lavozim> byId = lavozimRepostariy.findById(id);
        if (byId.isPresent()){
            return new Apirespons(byId.get().toString(),true);
        }
        return new Apirespons("Ma'lumotni ko'ringa xatoli yuz berdi",false);
    }
}
