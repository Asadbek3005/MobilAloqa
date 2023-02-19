package com.example.mobil.Controller;

import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.LoginDto;
import com.example.mobil.Payload.XodimDto;
import com.example.mobil.Servis.XodimServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xodim")
public class XodimControlller {
    @Autowired
    XodimServis xodimServis;


    @PostMapping("/add")
    public ResponseEntity<?> Add(@RequestBody XodimDto xodimDto){
        Apirespons apirespons=xodimServis.add(xodimDto);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/view")
    public ResponseEntity<?> View(){
        Apirespons apirespons=xodimServis.view();
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/viewId/{id}")
    public ResponseEntity<?> ViewId(@PathVariable Integer id){
        Apirespons apirespons=xodimServis.viewId(id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @PutMapping("/edit")
    public ResponseEntity<?> Edit(@RequestBody XodimDto xodimDto,@PathVariable Integer id){
        Apirespons apirespons=xodimServis.edit(xodimDto,id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }
    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginDto loginDto){
        Apirespons apirespons=xodimServis.login(loginDto);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }
}
