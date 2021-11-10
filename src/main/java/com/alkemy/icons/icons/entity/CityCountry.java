package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city_country")
@Getter
@Setter
@SQLDelete(sql = "UPDATE city_country SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CityCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String denomination;
    private int population;
    private boolean deleted = Boolean.FALSE;

    @Column(name = "total_area")
    private double totalAreaInM2;

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
            name = "icon_city_country",//crea una tabla intermedia
            joinColumns = @JoinColumn(name = "city_country_id"),
            inverseJoinColumns = @JoinColumn(name = "icon_id"))
    private Set<GeographyIcons> icons = new HashSet<>();
}
