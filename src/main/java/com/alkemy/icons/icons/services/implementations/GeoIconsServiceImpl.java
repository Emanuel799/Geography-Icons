package com.alkemy.icons.icons.services.implementations;

import com.alkemy.icons.icons.dtos.GeoIconsFiltersDTO;
import com.alkemy.icons.icons.dtos.GeographyIconsDTO;
import com.alkemy.icons.icons.entity.GeographyIcons;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.GeographyIconsMapper;
import com.alkemy.icons.icons.repositories.GeographyIconsRepository;
import com.alkemy.icons.icons.repositories.specifications.GeoIconsSpecification;
import com.alkemy.icons.icons.services.GeographyIconsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GeoIconsServiceImpl implements GeographyIconsService {

    @Autowired
    private GeographyIconsRepository geographyIconsRepository;

    @Autowired
    private GeoIconsSpecification geoIconsSpecification;

    @Autowired
    private GeographyIconsMapper geographyIconsMapper;

    public GeoIconsServiceImpl() {
    }

    @Override
    public GeographyIconsDTO save(GeographyIconsDTO serviceGeographyIconsDTO) {
        GeographyIcons geographyIcons = this.geographyIconsMapper.iconsDTO2Entity(serviceGeographyIconsDTO);
        GeographyIcons savedGeographyIcons = this.geographyIconsRepository.save(geographyIcons);
        GeographyIconsDTO savedGeographyIcons2iconsDTO = this.geographyIconsMapper.geographyIcons2DTO(savedGeographyIcons, true);
        return savedGeographyIcons2iconsDTO;
    }

    @Override
    public List<GeographyIconsDTO> getAllGeographyIcons() {
        List<GeographyIcons> geographyIconsList = this.geographyIconsRepository.findAll();
        List<GeographyIconsDTO> result = this.geographyIconsMapper.iconsList2DTOList(geographyIconsList, true);
        return result;
    }

    @Override
    public void delete(Long id) {
        this.geographyIconsRepository.deleteById(id);
    }

    @Override
    public List<GeographyIconsDTO> getByFilters(String name, String date, Set<Long> cities, String order) {
        GeoIconsFiltersDTO geoIconsFiltersDTO = new GeoIconsFiltersDTO(name,date,cities,order);
        List<GeographyIcons> geographyIconsList = this.geographyIconsRepository.findAll(this.geoIconsSpecification.getByFilters(geoIconsFiltersDTO));
        List<GeographyIconsDTO> geographyIconsDTOList = this.geographyIconsMapper.iconsList2DTOList(geographyIconsList,true);
        return geographyIconsDTOList;
    }

    public GeographyIconsDTO getDetailsById(Long id){
        Optional<GeographyIcons> geographyIcons = this.geographyIconsRepository.findById(id);
        if(!geographyIcons.isPresent()){
            throw new ParamNotFound("Not valid grographyIcon id");
        }
        GeographyIconsDTO geographyIconsDTO = this.geographyIconsMapper.geographyIcons2DTO(geographyIcons.get(),true);
        return geographyIconsDTO;
    }


}
