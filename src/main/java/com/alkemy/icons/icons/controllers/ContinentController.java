package com.alkemy.icons.icons.controllers;

import com.alkemy.icons.icons.dtos.ContinentDTO;
import com.alkemy.icons.icons.services.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("continents")
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    @GetMapping
    public ResponseEntity<List<ContinentDTO>> getAll(){
        List<ContinentDTO> continentDTOList = this.continentService.getAllContinents();
        return ResponseEntity.ok().body(continentDTOList);
    }

    @PostMapping
    public ResponseEntity<ContinentDTO> save(@RequestBody ContinentDTO continentDTO){
         ContinentDTO savedContinent = this.continentService.save(continentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContinent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.continentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
