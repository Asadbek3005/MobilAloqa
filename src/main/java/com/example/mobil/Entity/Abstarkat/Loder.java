package com.example.mobil.Entity.Abstarkat;

import com.example.mobil.Entity.Enum.Huquqlar;
import com.example.mobil.Entity.Lavozim;
import com.example.mobil.Entity.Xodim;
import com.example.mobil.Repostariy.LavozimRepostariy;
import com.example.mobil.Repostariy.XodimRepostariy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.example.mobil.Entity.Enum.Huquqlar.*;

@Component
public class Loder implements CommandLineRunner {
    @Autowired
    XodimRepostariy xodimRepostariy;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LavozimRepostariy lavozimRepostariy;
    @Value(value = "${spring.sql.init.mode}")
    private String holat;
    @Override
    public void run(String... args) throws Exception {
        if (holat.equals("always")){
            Huquqlar[] huquqlars=Huquqlar.values();
            Lavozim admin=lavozimRepostariy.save(new Lavozim(
                    LavozimConstata.ADMIN,
                    Arrays.asList(huquqlars)
            ));
            Lavozim user=lavozimRepostariy.save(new Lavozim(
                    LavozimConstata.USER,
                    Arrays.asList(ADD_USER,
                            UPDATE_USER,
                            DELETE_USER,
                            READ_USER)
            ));
            xodimRepostariy.save(new Xodim(
                    "Admin","asadbek05@gmail.com", passwordEncoder.encode("123"),admin,true
            ));
            xodimRepostariy.save(new Xodim(
                   "User","asadbek06@gmail.com", passwordEncoder.encode("124"),user,true
            ));

        }
    }
}
