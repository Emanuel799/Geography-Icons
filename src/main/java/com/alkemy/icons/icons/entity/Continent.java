package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.Set;

@Entity
@Table(name = "continent")
@Getter
@Setter
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String denomination;
    private Set<String> associatedGeoIconsCities;
}
