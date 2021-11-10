package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
//import java.util.Set;

@Entity
@Table(name = "continent")
@Getter
@Setter
@SQLDelete(sql = "UPDATE continent SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String denomination;
    private boolean deleted = Boolean.FALSE;
}
