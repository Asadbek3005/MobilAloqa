package com.example.mobil.Servis;

import com.example.mobil.Entity.Filial;
import com.example.mobil.Entity.Manzil;
import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.FilialDto;
import com.example.mobil.Repostariy.FilialRepostariy;
import com.example.mobil.Repostariy.ManzilRepostariy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialServis {
    @Autowired
    FilialRepostariy filialRepostariy;
    @Autowired
    ManzilRepostariy manzilRepostariy;
    public Apirespons add(FilialDto filialDto) {
        Optional<Filial> byNomi = filialRepostariy.findByNomi(filialDto.getNomi());
        if (byNomi.isPresent()){
            return new Apirespons("Ma'lumotingizni qaytadan kirting",false);
        }
        Filial filial=new Filial();
        filial.setNomi(filialDto.getNomi());
        Optional<Manzil> byId = manzilRepostariy.findById(filialDto.getManzil());
        if (byId.isPresent()){
           Manzil manzil = byId.get();
            filial.setManzil(manzil);
       }
      filialRepostariy.save(filial);
       return new Apirespons("Ma'lumotingiz muvaqiyatli joylandi!",true);

    }
    public Apirespons korish() {
        List<Filial> all = filialRepostariy.findAll();
        return new Apirespons(all.toString(),true);
    }
    public Apirespons edit(FilialDto filialDto, Integer id) {
        Optional<Filial> byId = filialRepostariy.findById(id);
        if (byId.isPresent()){
            Filial filial = byId.get();
             filial.setNomi(filialDto.getNomi());
            filialRepostariy.save(filial);
            return new Apirespons("Ma'lumotni taxrilandi:",true);
        }
        return new Apirespons("Ma'lumoti taxrilmadi qaytadan kirting",false);
    }

    public Apirespons id(FilialDto filialDto, Integer id) {
        Optional<Filial> byId = filialRepostariy.findById(id);
          if (byId.isPresent()){
              return new Apirespons(byId.get().toString(),true);
          }
        return new Apirespons("Malumotni qaytadan kirting",false);
    }
}
