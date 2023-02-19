package com.example.mobil.Controller;

import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.SimkartaDto;
import com.example.mobil.Servis.SimKartaServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sim")
public class SimKartaController {
    @Autowired
    SimKartaServis simKartaServis;

    @PostMapping("/add")
    public ResponseEntity<?> Add(@RequestBody SimkartaDto simkartaDto){
        Apirespons apirespons=simKartaServis.add(simkartaDto);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/view")
    public ResponseEntity<?> View(){
        Apirespons apirespons=simKartaServis.view();
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/view/{id}")
    public ResponseEntity<?> ViewId(@PathVariable Integer id){
        Apirespons apirespons=simKartaServis.viewId(id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @PutMapping("/edit")
    public ResponseEntity<?> Edit(@RequestBody SimkartaDto simkartaDto,@PathVariable Integer id){
        Apirespons apirespons=simKartaServis.edit(simkartaDto,id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }
}
