package com.alkemy.icons.icons.dtos;

//import com.alkemy.icons.icons.entity.GeographyIcons;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class CityCountryDTO {

    private Long id;
    private String image;
    private String denomination;
    private int population;
    private double totalAreaInM2;
    private Long continentId;
    private Set<GeographyIconsDTO> icons;
}
