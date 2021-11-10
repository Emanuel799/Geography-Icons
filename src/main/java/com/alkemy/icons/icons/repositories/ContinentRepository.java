package com.alkemy.icons.icons.repositories;

import com.alkemy.icons.icons.entity.Continent;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContinentRepository extends JpaRepository<Continent,Long> {

    List<Continent> findAll(Specification<Continent> specification);
}
