package com.example.mobil.Token;

import com.example.mobil.Servis.XodimServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class Filter extends OncePerRequestFilter {
    @Autowired
    XodimToken token;
    @Autowired
    XodimServis xodimServis;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader("Auth");
        if (auth!=null){
            String a = token.UsernameOlish(auth);
            if(token!=null){
                System.out.println(auth);
                boolean s = token.tokenCheck(auth);
                if (s) {
                    UserDetails userDetails = xodimServis.loadUserByUsername(a);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            else System.out.println("null");
        }
        filterChain.doFilter(request,response);
    }
}
