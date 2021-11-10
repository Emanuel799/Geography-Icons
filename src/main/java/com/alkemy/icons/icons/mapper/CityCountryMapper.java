package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dtos.CityCountryDTO;
import com.alkemy.icons.icons.dtos.GeographyIconsDTO;
import com.alkemy.icons.icons.entity.CityCountry;
import com.alkemy.icons.icons.entity.GeographyIcons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CityCountryMapper {

    @Autowired
    GeographyIconsMapper geographyIconsMapper;

    public CityCountry cityCountryDTO2Entity(CityCountryDTO cityCountryDTO){
        CityCountry cityCountry = new CityCountry();
        cityCountry.setImage(cityCountryDTO.getImage());
        cityCountry.setDenomination(cityCountryDTO.getDenomination());
        cityCountry.setPopulation(cityCountryDTO.getPopulation());
        cityCountry.setTotalAreaInM2(cityCountryDTO.getTotalAreaInM2());
        cityCountry.setContinentId(cityCountryDTO.getContinentId());
        Set<GeographyIcons> geographyIconsSet = this.geographyIconsMapper.iconsDTOList2Entity(cityCountryDTO.getIcons());
        cityCountry.setIcons(geographyIconsSet);
        return cityCountry;
    }

    public CityCountryDTO cityCountry2DTO(CityCountry cityCountry, boolean loadGeoIcons){
        CityCountryDTO cityCountryDTO = new CityCountryDTO();
        cityCountryDTO.setId(cityCountry.getId());
        cityCountryDTO.setImage(cityCountry.getImage());
        cityCountryDTO.setDenomination(cityCountry.getDenomination());
        cityCountryDTO.setPopulation(cityCountry.getPopulation());
        cityCountryDTO.setTotalAreaInM2(cityCountry.getTotalAreaInM2());
        cityCountryDTO.setContinentId(cityCountry.getContinentId());
        if(loadGeoIcons){
            Set<GeographyIconsDTO> geographyIconsDTOSet = this.geographyIconsMapper.iconsSet2DTOSet(cityCountry.getIcons(),false);
            cityCountryDTO.setIcons(geographyIconsDTOSet);
        }
        return cityCountryDTO;
    }

    public List<CityCountryDTO> cityCountryList2DTOList(List<CityCountry> cityCountryList, boolean loadGeoIcons){
        List<CityCountryDTO> cityCountryDTOList = new ArrayList<>();
        for (CityCountry cityCountry:
                cityCountryList) {
            cityCountryDTOList.add(this.cityCountry2DTO(cityCountry,loadGeoIcons));
        }
        return cityCountryDTOList;
    }
}
