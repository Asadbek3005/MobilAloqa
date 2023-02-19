package com.example.mobil.Token;

import com.example.mobil.Entity.Lavozim;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class XodimToken {
    public String key = "qwert";
    public String CreateToken(String username, Lavozim lavozimList){
        Long vaqt = Long.valueOf(60*60*100*24);
        Date date = new Date(System.currentTimeMillis()+vaqt);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(date)
                .claim("lavozimlar",lavozimList.getNomi())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        return token;
    }

    public String UsernameOlish(String auth){
        String subject = Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(auth)
                .getBody()
                .getSubject();
        return subject;
    }

    public boolean tokenCheck(String auth) {
        Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(auth);
        return true;
    }

    public String getUsername(String auth) {
        String subject = Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(auth)
                .getBody()
                .getSubject();
        return subject;
    }

    public String Token(String username, Lavozim lavozim) {
        return null;
    }
}
