package com.alkemy.icons.icons.controllers;

import com.alkemy.icons.icons.dtos.CityCountryDTO;
import com.alkemy.icons.icons.services.CityCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("cityCountries")
public class CityCountryController {

    @Autowired
    private CityCountryService cityCountryService;

    @GetMapping
    public ResponseEntity<List<CityCountryDTO>> getAll(){
        List<CityCountryDTO> cityCountryDTOList = this.cityCountryService.getAllCityCountries();
        return ResponseEntity.ok().body(cityCountryDTOList);
    }

    @PostMapping
    public ResponseEntity<CityCountryDTO> save(@RequestBody CityCountryDTO cityCountryDTO){
        CityCountryDTO savedCityCountry = this.cityCountryService.save(cityCountryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCityCountry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.cityCountryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
