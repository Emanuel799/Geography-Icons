package com.alkemy.icons.icons.services;

import com.alkemy.icons.icons.dtos.ContinentDTO;
import com.alkemy.icons.icons.dtos.GeographyIconsDTO;

import java.util.List;
import java.util.Set;

public interface ContinentService {

    ContinentDTO save(ContinentDTO serviceContinentDTO);

    List<ContinentDTO> getAllContinents();

    void delete(Long id);

    List<ContinentDTO> getByFilters(String name, String date, String order);
}
