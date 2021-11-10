package com.alkemy.icons.icons.repositories.specifications;

import com.alkemy.icons.icons.dtos.ContinentFiltersDTO;
import com.alkemy.icons.icons.dtos.GeoIconsFiltersDTO;
import com.alkemy.icons.icons.entity.CityCountry;
import com.alkemy.icons.icons.entity.Continent;
import com.alkemy.icons.icons.entity.GeographyIcons;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContinentSpecification {

    public Specification<Continent> getByFilters(ContinentFiltersDTO continentFiltersDTO){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(continentFiltersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("denomination")),
                                "%" + continentFiltersDTO.getName().toLowerCase() + "%"
                        )
                );
            }
            if(StringUtils.hasLength(continentFiltersDTO.getDate())){
                //TODO: Reuse this in this function
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date  = LocalDate.parse(continentFiltersDTO.getDate(), formatter);

                predicates.add(
                        criteriaBuilder.equal(root.<LocalDate>get("creationDate"), date)
                );
            }
            /*if(!CollectionUtils.isEmpty(continentFiltersDTO.getCities())){
                Join<CityCountry,GeographyIcons> join = root.join("city_country", JoinType.INNER);
                Expression<String> citiesId = join.get("id");
                predicates.add(citiesId.in(continentFiltersDTO.getCities()));
            }*/
            //remove duplicates
           // query.distinct(true);

            //Order resolver
            String orderByField = "denomination";
            query.orderBy(
                    continentFiltersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
