package com.example.mobil.Servis;

import com.example.mobil.Entity.Manzil;
import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.ManzilDto;
import com.example.mobil.Repostariy.ManzilRepostariy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManzilServis {
    @Autowired
    ManzilRepostariy manzilRepostariy;
    public Apirespons add(ManzilDto manzilDto) {
        Optional<Manzil> byViloyat = manzilRepostariy.findByViloyat(manzilDto.getViloyat());
        if (byViloyat.isPresent()){
            return new Apirespons("Ma'lumotingizni qaytadan kirting",false);
        }
        Manzil manzil=new Manzil();
        manzil.setViloyat(manzilDto.getViloyat());
        manzil.setTuman(manzilDto.getTuman());
        manzil.setKocha(manzilDto.getKocha());
        manzil.setUy(manzilDto.getUy());
        manzilRepostariy.save(manzil);
        return new Apirespons("Ma'lumotingiz muvaqiyatli joylandi",true);
    }



    public Apirespons view() {
        List<Manzil> all = manzilRepostariy.findAll();
        return new Apirespons(all.toString(),true);
    }

    public Apirespons korish(Integer id) {
        Optional<Manzil> byId = manzilRepostariy.findById(id);
        if (byId.isPresent()){
            return new Apirespons(byId.get().toString(),true);
        }
        return new Apirespons("Ma'lumotini qaytadan kirting xatolik yuz berdi",false);
    }

    public Apirespons edit(ManzilDto manzilDto, Integer id) {
        Optional<Manzil> byId = manzilRepostariy.findById(id);
        if (byId.isPresent()){
            Manzil manzil = byId.get();
            manzil.setViloyat(manzilDto.getViloyat());
            manzil.setTuman(manzilDto.getTuman());
            manzil.setKocha(manzilDto.getKocha());
            manzil.setUy(manzilDto.getUy());
            manzilRepostariy.save(manzil);
            return new Apirespons("Ma'lumotingiz taxrilandi:",true);
        }
        return new Apirespons("Ma'lumotingiz qayatdan kirting xatolik yuz berdi",false);
    }
}
