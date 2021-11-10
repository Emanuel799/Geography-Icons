package com.alkemy.icons.icons.dtos;

import com.alkemy.icons.icons.entity.CityCountry;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GeographyIconsDTO {

    private Long id;
    private String image;
    private String denomination;
    private LocalDate creationDate;
    private double height;
    private String history;
    private List<CityCountryDTO> cityAndReplicas;
}
