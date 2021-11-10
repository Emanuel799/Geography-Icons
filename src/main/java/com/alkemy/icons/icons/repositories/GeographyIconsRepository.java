package com.alkemy.icons.icons.repositories;

import com.alkemy.icons.icons.entity.GeographyIcons;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GeographyIconsRepository extends JpaRepository<GeographyIcons,Long> {

    List<GeographyIcons> findAll(Specification<GeographyIcons> specification);
}
