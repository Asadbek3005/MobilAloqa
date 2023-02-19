package com.example.mobil.Entity;

import com.example.mobil.Entity.Abstarkat.Abstrakt;
import com.example.mobil.Entity.Enum.Huquqlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Xodim extends Abstrakt implements UserDetails {
    @Column(nullable = false)
    private String fish;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    private Manzil manzil;
    @ManyToOne
    private Filial filial;
     @OneToOne
    private Lavozim lavozim;
    private String emailCod;
    private boolean isAccountNonExpired=true;
    private boolean isAccountNonLocked=true;
    private boolean isCredentialsNonExpired=true;
    private boolean isEnabled=false;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Huquqlar> huquqlarList = this.lavozim.getHuquqlarList();
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Huquqlar huquqlar : huquqlarList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(huquqlar.name()));
        }
        return grantedAuthorities;
    }
    public Xodim(String fish, String username, String password, Lavozim lavozimId, boolean isEnabled){
        this.fish=fish;
        this.username=username;
        this.password=password;
        this.lavozim=lavozimId;
        this.isEnabled=isEnabled;

    }


}
