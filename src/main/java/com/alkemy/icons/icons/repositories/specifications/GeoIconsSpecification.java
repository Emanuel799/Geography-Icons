package com.alkemy.icons.icons.repositories.specifications;

//import antlr.StringUtils;
import com.alkemy.icons.icons.dtos.GeoIconsFiltersDTO;
import com.alkemy.icons.icons.entity.CityCountry;
import com.alkemy.icons.icons.entity.GeographyIcons;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;

@Component
public class GeoIconsSpecification {

    public Specification<GeographyIcons> getByFilters(GeoIconsFiltersDTO geoIconsFiltersDTO){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(geoIconsFiltersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("denomination")),
                                "%" + geoIconsFiltersDTO.getName().toLowerCase() + "%"
                        )
                );
            }
            if(StringUtils.hasLength(geoIconsFiltersDTO.getDate())){
                //TODO: Reuse this in this function
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date  = LocalDate.parse(geoIconsFiltersDTO.getDate(), formatter);

                predicates.add(
                        criteriaBuilder.equal(root.<LocalDate>get("creationDate"), date)
                );
            }
            if(!CollectionUtils.isEmpty(geoIconsFiltersDTO.getCities())){
                Join<CityCountry,GeographyIcons> join = root.join("city_country", JoinType.INNER);
                Expression<String> citiesId = join.get("id");
                predicates.add(citiesId.in(geoIconsFiltersDTO.getCities()));
            }
            //remove duplicates
            query.distinct(true);

            //Order resolver
            String orderByField = "denomination";
            query.orderBy(
                    geoIconsFiltersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
