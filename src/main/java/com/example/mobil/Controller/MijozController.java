package com.example.mobil.Controller;

import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.MijozDto;
import com.example.mobil.Servis.MijozServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mijoz")
public class MijozController {
    @Autowired
    MijozServis mijozServis;


    @PostMapping("/add")
    public ResponseEntity<?> Add(@RequestBody MijozDto mijozDto){
        Apirespons apirespons=mijozServis.add(mijozDto);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/view")
    public ResponseEntity<?> View(){
        Apirespons apirespons=mijozServis.view();
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @GetMapping("/view/{id}")
    public ResponseEntity<?> View(@PathVariable Integer id){
        Apirespons apirespons=mijozServis.viewId(id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }


    @PutMapping("/edit")
    public ResponseEntity<?> Edit(@RequestBody MijozDto mijozDto,@PathVariable Integer id){
        Apirespons apirespons=mijozServis.edit(mijozDto,id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }
}
