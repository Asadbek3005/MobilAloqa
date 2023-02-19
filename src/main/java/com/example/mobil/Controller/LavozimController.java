package com.example.mobil.Controller;

import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.LavozimDto;
import com.example.mobil.Servis.LavozimServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lavozim")
public class LavozimController {
    @Autowired
    LavozimServis lavozimServis;


    @PostMapping("/add")
    public ResponseEntity<?> Add(@RequestBody LavozimDto lavozimDto){
        Apirespons apirespons=lavozimServis.add(lavozimDto);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<?> Edit(@RequestBody LavozimDto lavozimDto,@PathVariable Integer id){
        Apirespons apirespons=lavozimServis.edit(lavozimDto,id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/view")
    public ResponseEntity<?> View(){
        Apirespons apirespons=lavozimServis.view();
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/view/{id}")
    public ResponseEntity<?> ViewId(@PathVariable Integer id){
        Apirespons apirespons=lavozimServis.viewId(id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }
}
