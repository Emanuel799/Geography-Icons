package com.alkemy.icons.icons.repositories;

import com.alkemy.icons.icons.entity.CityCountry;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityCountryRepository extends JpaRepository<CityCountry,Long> {

    List<CityCountry> findAll(Specification<CityCountry> specification);
}
