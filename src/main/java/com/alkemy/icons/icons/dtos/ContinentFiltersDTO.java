package com.alkemy.icons.icons.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContinentFiltersDTO {

    private String name;
    private String date;
    private String order;

    public ContinentFiltersDTO(String name, String date, String order) {
        this.name = name;
        this.date = date;
        this.order = order;
    }


    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
