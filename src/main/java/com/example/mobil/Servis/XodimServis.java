package com.example.mobil.Servis;

import com.example.mobil.Entity.Filial;
import com.example.mobil.Entity.Lavozim;
import com.example.mobil.Entity.Manzil;
import com.example.mobil.Entity.Xodim;
import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.LoginDto;
import com.example.mobil.Payload.XodimDto;
import com.example.mobil.Repostariy.FilialRepostariy;
import com.example.mobil.Repostariy.LavozimRepostariy;
import com.example.mobil.Repostariy.ManzilRepostariy;
import com.example.mobil.Repostariy.XodimRepostariy;
import com.example.mobil.Token.XodimToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class XodimServis implements UserDetailsService {
    @Autowired
    XodimRepostariy xodimRepostariy;
    @Autowired
    ManzilRepostariy manzilRepostariy;
    @Autowired
    LavozimRepostariy lavozimRepostariy;
    @Autowired
    FilialRepostariy filialRepostariy;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    XodimToken xodimToken;
    @Autowired
    JavaMailSender javaMailSender;
    public Apirespons add(XodimDto xodimDto) {
        Optional<Xodim> byUsername = xodimRepostariy.findByUsername(xodimDto.getUsername());
        if (byUsername.isPresent()){
            return new Apirespons("Ma'lumotni qaytadan kirting xatolik yuz berdi",false);
        }
        Xodim xodim=new Xodim();
        xodim.setFish(xodimDto.getFish());
        xodim.setUsername(xodimDto.getUsername());
        xodim.setPassword(passwordEncoder.encode(xodimDto.getPassword()));
        xodim.setEmailCod(UUID.randomUUID().toString().substring(0,6));
        Optional<Manzil> byId = manzilRepostariy.findById(xodimDto.getManzil());
        if (byId.isPresent()){
            Manzil manzil = byId.get();
            xodim.setManzil(manzil);
        }
        Optional<Filial> byId1 = filialRepostariy.findById(xodimDto.getFilial());
        if (byId1.isPresent()){
            Filial filial = byId1.get();
            xodim.setFilial(filial);
        }
        Optional<Lavozim> byId2 = lavozimRepostariy.findById(xodimDto.getLavozim());
        if (byId2.isPresent()){
            Lavozim lavozim = byId2.get();
            xodim.setLavozim(lavozim);
        }
        boolean b1 = XabarYuborish(xodim.getUsername(), xodim.getEmailCod());
        if (b1){
            xodimRepostariy.save(xodim);
            return new Apirespons("Falotirish kodi eliktiron pochtangizga yuborildi",true);
        }
        return new Apirespons("Malumotlarni tekshirib boshqatdan kirying",false);
    }

    public boolean XabarYuborish(String email,String emailCode) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("asadbekrustamov575@gmail.com");
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("email tastihlash");
            simpleMailMessage.setText("<a href='http://localhost:8080/Auth/emailtasdiqlash?useremail=" + email + "&emailCode=" + emailCode + "'>hisobni tasdiqlash</a>");
            javaMailSender.send(simpleMailMessage);
            return true;
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return false;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Xodim> byPasport = xodimRepostariy.findByUsername(username);
        if (byPasport.isPresent()){
            return byPasport.get();
        }
        return (UserDetails) new UsernameNotFoundException("paspurt seriya  topilmadi");
    }

    public Apirespons login(LoginDto loginDto) {
        try{
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            Xodim percila = (Xodim) authenticate.getPrincipal();
            String token = xodimToken.CreateToken(percila.getUsername(), percila.getLavozim());
            return new Apirespons("Profilga hush kelibsiz:"+token,true);
        }
        catch (Exception e){
            return new Apirespons("Xabarga login yoki parol xato",false);
        }
    }

    public Apirespons view() {
        List<Xodim> all = xodimRepostariy.findAll();
        return new Apirespons(all.toString(),true);
    }

    public Apirespons viewId(Integer id) {
        Optional<Xodim> byId = xodimRepostariy.findById(id);
        if (byId.isPresent()){
            return new Apirespons(byId.get().toString(),true);
        }
        return new Apirespons("Ma'lumotini qyatdan kirting xatolik yuz berdi",false);
    }

    public Apirespons edit(XodimDto xodimDto, Integer id) {
        Optional<Xodim> byId = xodimRepostariy.findById(id);
        if (byId.isPresent()){
            Xodim xodim = byId.get();
            xodim.setFish(xodimDto.getFish());
            xodim.setUsername(xodimDto.getUsername());
            xodim.setPassword(passwordEncoder.encode(xodimDto.getPassword()));
            Optional<Manzil> byId1 = manzilRepostariy.findById(xodimDto.getManzil());
            if (byId1.isPresent()){
                Manzil manzil = byId1.get();
                xodim.setManzil(manzil);
            }
            Optional<Filial> byId2 = filialRepostariy.findById(xodimDto.getFilial());
            if (byId2.isPresent()){
                Filial filial = byId2.get();
                xodim.setFilial(filial);
            }
            Optional<Lavozim> byId3 = lavozimRepostariy.findById(xodimDto.getLavozim());
            if (byId3.isPresent()){
                Lavozim lavozim = byId3.get();
                xodim.setLavozim(lavozim);
            }
            return new Apirespons("Ma'lumotingiz taxrilandi:",true);
        }
        return new Apirespons("Ma'lumotini qayatdan kirting xatolik yuz berdi",false);
    }
}
