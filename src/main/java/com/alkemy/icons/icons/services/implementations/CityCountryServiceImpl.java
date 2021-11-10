package com.alkemy.icons.icons.services.implementations;

import com.alkemy.icons.icons.dtos.*;
import com.alkemy.icons.icons.entity.CityCountry;
import com.alkemy.icons.icons.entity.Continent;
import com.alkemy.icons.icons.entity.GeographyIcons;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.CityCountryMapper;
import com.alkemy.icons.icons.repositories.CityCountryRepository;
import com.alkemy.icons.icons.repositories.specifications.CityCountrySpecification;
import com.alkemy.icons.icons.services.CityCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityCountryServiceImpl implements CityCountryService {

    @Autowired
    private CityCountryRepository cityCountryRepository;

    @Autowired
    private CityCountryMapper cityCountryMapper;

    @Autowired
    private CityCountrySpecification cityCountrySpecification;

    @Override
    public CityCountryDTO save(CityCountryDTO serviceCityCountryDTO) {
        CityCountry cityCountry = this.cityCountryMapper.cityCountryDTO2Entity(serviceCityCountryDTO);
        CityCountry savedCityCountry = this.cityCountryRepository.save(cityCountry);
        CityCountryDTO savedCityCountry2CityCountryDTO = this.cityCountryMapper.cityCountry2DTO(savedCityCountry, true);
        return savedCityCountry2CityCountryDTO;
    }

    @Override
    public List<CityCountryDTO> getAllCityCountries() {
        List<CityCountry> cityCountryList = this.cityCountryRepository.findAll();
        List<CityCountryDTO> result = this.cityCountryMapper.cityCountryList2DTOList(cityCountryList, true);
        return result;
    }

    @Override
    public void delete(Long id) {
        this.cityCountryRepository.deleteById(id);
    }

    @Override
    public List<CityCountryDTO> getByFilters(String name, String date, String order){
        CityCountryFiltersDTO cityCountryFiltersDTO = new CityCountryFiltersDTO(name,date,order);
        List<CityCountry> cityCountryList = this.cityCountryRepository.findAll(this.cityCountrySpecification.getByFilters(cityCountryFiltersDTO));
        List<CityCountryDTO> cityCountryDTOList = this.cityCountryMapper.cityCountryList2DTOList(cityCountryList,true);
        return cityCountryDTOList;
    }

    public CityCountryDTO getDetailsById(Long id){
        Optional<CityCountry> cityCountry = this.cityCountryRepository.findById(id);
        if(!cityCountry.isPresent()){
            throw new ParamNotFound("Not valid cityCountry id");
        }
        CityCountryDTO cityCountryDTO = this.cityCountryMapper.cityCountry2DTO(cityCountry.get(),true);
        return cityCountryDTO;
    }
}
