package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dtos.ContinentDTO;
import com.alkemy.icons.icons.entity.Continent;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContinentMapper {

    public Continent continentDTO2Entity(ContinentDTO continentDTO){
        Continent continent = new Continent();
        continent.setImage(continentDTO.getImage());
        continent.setDenomination(continentDTO.getDenomination());
        return continent;
    }

    public ContinentDTO continent2DTO(Continent continent){
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setId(continent.getId());
        continentDTO.setImage(continent.getImage());
        continentDTO.setDenomination(continent.getDenomination());
        return continentDTO;
    }

    public List<ContinentDTO> continentList2DTOList(List<Continent> continentList){
        List<ContinentDTO> continentDTOList = new ArrayList<>();
        for (Continent continent:
             continentList) {
            continentDTOList.add(this.continent2DTO(continent));
        }
        return continentDTOList;
    }

}
