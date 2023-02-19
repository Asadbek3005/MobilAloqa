package com.example.mobil.Controller;

import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.TarifDto;
import com.example.mobil.Servis.TarifServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarif")
public class TarifController {
    @Autowired
    TarifServis tarifServis;


    @PostMapping("/add")
    public ResponseEntity<?> Add(@RequestBody TarifDto tarifDto){
        Apirespons apirespons=tarifServis.add(tarifDto);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/korish")
    public ResponseEntity<?> Korish(){
        Apirespons apirespons=tarifServis.korish();
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/idboyicha/{id}")
    public ResponseEntity<?> Idboyicha(@PathVariable Integer id){
        Apirespons apirespons=tarifServis.id(id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<?> Edit(@RequestBody TarifDto tarifDto,@PathVariable Integer id){
        Apirespons apirespons=tarifServis.edit(tarifDto,id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }
}
