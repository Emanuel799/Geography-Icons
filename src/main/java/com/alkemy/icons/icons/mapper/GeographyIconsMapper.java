package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dtos.CityCountryDTO;
import com.alkemy.icons.icons.dtos.GeographyIconsDTO;
import com.alkemy.icons.icons.dtos.basicGeoIconsDTO;
import com.alkemy.icons.icons.entity.GeographyIcons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GeographyIconsMapper {

    @Autowired
    CityCountryMapper cityCountryMapper;


    public GeographyIcons iconsDTO2Entity(GeographyIconsDTO geographyIconsDTO){
        GeographyIcons geographyIcons = new GeographyIcons();
        geographyIcons.setImage(geographyIconsDTO.getImage());
        geographyIcons.setDenomination(geographyIconsDTO.getDenomination());
        geographyIcons.setCreationDate(geographyIconsDTO.getCreationDate());
        geographyIcons.setHeight(geographyIconsDTO.getHeight());
        geographyIcons.setHistory(geographyIconsDTO.getHistory());
        //geographyIcons.setCityAndReplicas(geographyIconsDTO.getCityAndReplicas());
        return geographyIcons;
    }

    public GeographyIconsDTO geographyIcons2DTO(GeographyIcons geographyIcons, boolean loadCityCountries){
        GeographyIconsDTO geographyIconsDTO = new GeographyIconsDTO();
        geographyIconsDTO.setId(geographyIcons.getId());
        geographyIconsDTO.setImage(geographyIcons.getImage());
        geographyIconsDTO.setDenomination(geographyIcons.getDenomination());
        geographyIconsDTO.setCreationDate(geographyIcons.getCreationDate());
        geographyIconsDTO.setHeight(geographyIcons.getHeight());
        geographyIconsDTO.setHistory(geographyIcons.getHistory());
        //geographyIconsDTO.setCityAndReplicas(geographyIcons.getCityAndReplicas());
        if(loadCityCountries){
            List<CityCountryDTO> cityCountryDTOList = this.cityCountryMapper.cityCountryList2DTOList(geographyIcons.getCityAndReplicas(), false);
            geographyIconsDTO.setCityAndReplicas(cityCountryDTOList);
        }
        return geographyIconsDTO;
    }

    public Set<GeographyIconsDTO> iconsSet2DTOSet(Set<GeographyIcons> geographyIconsSet, boolean loadCityCountries){
        Set<GeographyIconsDTO> geographyIconsDTOSet = new HashSet<>();
        for (GeographyIcons geographyIcons:
                geographyIconsSet) {
            geographyIconsDTOSet.add(this.geographyIcons2DTO(geographyIcons,loadCityCountries));
        }
        return geographyIconsDTOSet;
    }

    public List<GeographyIconsDTO> iconsList2DTOList(List<GeographyIcons> geographyIconsList, boolean loadCityCountries){
        List<GeographyIconsDTO> geographyIconsDTOList = new ArrayList<>();
        for (GeographyIcons geographyIcons : geographyIconsList){
            geographyIconsDTOList.add(this.geographyIcons2DTO(geographyIcons,loadCityCountries));
        }
        return geographyIconsDTOList;
    }

    public List<basicGeoIconsDTO> iconsList2BasicDTOList(Collection<GeographyIcons> geographyIcons){
        List<basicGeoIconsDTO> basicGeoIconsDTOList = new ArrayList<>();
        basicGeoIconsDTO basicGeoIconsDTO;
        for(GeographyIcons geographyIcon : geographyIcons){
            basicGeoIconsDTO = new basicGeoIconsDTO();
            basicGeoIconsDTO.setId(geographyIcon.getId());
            basicGeoIconsDTO.setImage(basicGeoIconsDTO.getImage());
            basicGeoIconsDTO.setDenomination(basicGeoIconsDTO.getDenomination());
            basicGeoIconsDTOList.add(basicGeoIconsDTO);
        }
        return basicGeoIconsDTOList;
    }

    public Set<GeographyIcons> iconsDTOList2Entity(Set<GeographyIconsDTO> geographyIconsDTOSet){
        Set<GeographyIcons> geographyIconsSet = new HashSet<>();
        for (GeographyIconsDTO geographyIconsDTO: geographyIconsDTOSet) {
            geographyIconsSet.add(this.iconsDTO2Entity(geographyIconsDTO));
        }
        return geographyIconsSet;
    }
}
