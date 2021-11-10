package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "geo_icons")
@Getter
@Setter
@SQLDelete(sql = "UPDATE geo_icons SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class GeographyIcons {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String denomination;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;

    private double height;
    private String history;
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "icons", cascade = CascadeType.ALL)
    private List<CityCountry> cityAndReplicas = new ArrayList<>();

    //public GeographyIcons(){}

    public void addCityCountry(CityCountry cityCountry){
        this.cityAndReplicas.add(cityCountry);
    }

    public void removeCityCountry(CityCountry cityCountry){
        this.cityAndReplicas.add(cityCountry);
    }
}
