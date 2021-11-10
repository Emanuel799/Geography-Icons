package com.alkemy.icons.icons.services.implementations;

import com.alkemy.icons.icons.dtos.ContinentDTO;
import com.alkemy.icons.icons.dtos.ContinentFiltersDTO;
import com.alkemy.icons.icons.dtos.GeographyIconsDTO;
import com.alkemy.icons.icons.entity.Continent;
import com.alkemy.icons.icons.entity.GeographyIcons;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.ContinentMapper;
import com.alkemy.icons.icons.repositories.ContinentRepository;
import com.alkemy.icons.icons.repositories.specifications.ContinentSpecification;
import com.alkemy.icons.icons.services.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    private ContinentMapper continentMapper;

    @Autowired
    private ContinentRepository continentRepository;

    @Autowired
    private ContinentSpecification continentSpecification;

    public ContinentDTO save(ContinentDTO serviceContinentDTO){
        Continent continent = this.continentMapper.continentDTO2Entity(serviceContinentDTO);
        Continent savedContinent = this.continentRepository.save(continent);
        ContinentDTO savedContinent2continentDTO = this.continentMapper.continent2DTO(savedContinent);
        return savedContinent2continentDTO;
    }

    @Override
    public List<ContinentDTO> getAllContinents() {
        List<Continent> continentList = this.continentRepository.findAll();
        List<ContinentDTO> result = this.continentMapper.continentList2DTOList(continentList);
        return result;
    }

    @Override
    public void delete(Long id) {
        this.continentRepository.deleteById(id);
    }

    @Override
    public List<ContinentDTO> getByFilters(String name, String date,String order){
        ContinentFiltersDTO continentFiltersDTO = new ContinentFiltersDTO(name,date,order);
        List<Continent> continentList = this.continentRepository.findAll(this.continentSpecification.getByFilters(continentFiltersDTO));
        List<ContinentDTO> continentDTOList = this.continentMapper.continentList2DTOList(continentList);
        return continentDTOList;
    }

    public ContinentDTO getDetailsById(Long id){
        Optional<Continent> continent = this.continentRepository.findById(id);
        if(!continent.isPresent()){
            throw new ParamNotFound("Not valid continent id");
        }
        ContinentDTO continentDTO = this.continentMapper.continent2DTO(continent.get());
        return continentDTO;
    }
}
