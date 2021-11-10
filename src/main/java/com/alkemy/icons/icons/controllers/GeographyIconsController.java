package com.alkemy.icons.icons.controllers;

import com.alkemy.icons.icons.dtos.GeographyIconsDTO;
import com.alkemy.icons.icons.services.GeographyIconsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("geographyIcons")
public class GeographyIconsController {

    @Autowired
    private GeographyIconsService geographyIconsService;


    @GetMapping
    public ResponseEntity<List<GeographyIconsDTO>> getAll(){
        List<GeographyIconsDTO> geographyIconsDTOList = this.geographyIconsService.getAllGeographyIcons();
        return ResponseEntity.ok().body(geographyIconsDTOList);
    }

    @PostMapping
    public ResponseEntity<GeographyIconsDTO> save(@RequestBody GeographyIconsDTO geographyIconsDTO){
        GeographyIconsDTO savedIcon = this.geographyIconsService.save(geographyIconsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIcon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.geographyIconsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /*@GetMapping
    public ResponseEntity<List<GeographyIconsDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Set<Long> cities,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<GeographyIconsDTO> geographyIconsDTOList = this.geographyIconsService.getByFilters(name, date, cities, order);
        return ResponseEntity.ok(geographyIconsDTOList);
    }*/
}
