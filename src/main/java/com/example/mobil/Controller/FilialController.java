package com.example.mobil.Controller;

import com.example.mobil.Payload.Apirespons;
import com.example.mobil.Payload.FilialDto;
import com.example.mobil.Repostariy.FilialRepostariy;
import com.example.mobil.Servis.FilialServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filial")
public class FilialController {
    @Autowired
    FilialServis filialServis;
    @PostMapping("/add")
    public ResponseEntity<?> Add(@RequestBody FilialDto filialDto){
        Apirespons apirespons=filialServis.add(filialDto);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }
    @GetMapping("/korish")
    public ResponseEntity<?> Korish(){
        Apirespons apirespons=filialServis.korish();
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }
    @GetMapping("/idboyicha/{id}")
    public ResponseEntity<?> Idboyicah(@RequestBody FilialDto filialDto,@PathVariable Integer id){
        Apirespons apirespons=filialServis.id(filialDto,id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> Edit(@RequestBody FilialDto filialDto,@PathVariable Integer id){
        Apirespons apirespons=filialServis.edit(filialDto,id);
        return ResponseEntity.status(apirespons.isXolat()?200:208).body(apirespons.getXabar());
    }
}
