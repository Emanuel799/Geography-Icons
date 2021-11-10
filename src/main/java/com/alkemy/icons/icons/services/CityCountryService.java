package com.alkemy.icons.icons.services;

import com.alkemy.icons.icons.dtos.CityCountryDTO;
import java.util.List;

public interface CityCountryService {

    CityCountryDTO save(CityCountryDTO serviceCityCountryDTO);

    List<CityCountryDTO> getAllCityCountries();

    void delete(Long id);

    List<CityCountryDTO> getByFilters(String name,String date,String order);
}
