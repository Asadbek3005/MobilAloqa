package com.example.mobil.Controller;

import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.ManzilDto;
import com.example.mobil.Servis.ManzilServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manzil")
public class ManzilController {
    @Autowired
    ManzilServis manzilServis;

    @PostMapping("/add")
    public ResponseEntity<?> Add(@RequestBody ManzilDto manzilDto){
        Apirespons apirespons=manzilServis.add(manzilDto);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/view/{id}")
    public ResponseEntity<?> KorishId(@PathVariable Integer id){
        Apirespons apirespons=manzilServis.korish(id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/view")
    public ResponseEntity<?> View(){
        Apirespons apirespons=manzilServis.view();
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @PutMapping("/edit")
    public ResponseEntity<?> Etid(@RequestBody ManzilDto manzilDto,@PathVariable Integer id){
        Apirespons apirespons=manzilServis.edit(manzilDto,id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }

}
