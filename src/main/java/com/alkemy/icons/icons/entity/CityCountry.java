package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city_country")
@Getter
@Setter
public class CityCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String denomination;
    private int population;

    @Column(name = "total_area")
    private double totalAreaInM2;

    private String associatedGeographyIcons;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "continent_id", insertable = false, updatable = false)
    private Continent continent;

    @Column(name = "continent_id", nullable = false)
    private Long continentId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "icon_city_country",
            joinColumns = @JoinColumn(name = "city_country_id"),
            inverseJoinColumns = @JoinColumn(name = "icon_id"))
    private Set<GeographyIcons> icons = new HashSet<>();

    //private CityCountry(){}
}
