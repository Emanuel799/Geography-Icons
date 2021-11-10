package com.alkemy.icons.icons.services;

import com.alkemy.icons.icons.dtos.GeographyIconsDTO;
import java.util.List;
import java.util.Set;

public interface GeographyIconsService {

    GeographyIconsDTO save(GeographyIconsDTO serviceGeographyIconsDTO);

    List<GeographyIconsDTO> getAllGeographyIcons();

    void delete(Long id);

    List<GeographyIconsDTO> getByFilters(String name, String date, Set<Long> cities, String order);


}
