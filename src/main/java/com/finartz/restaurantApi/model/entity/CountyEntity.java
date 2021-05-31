package com.finartz.restaurantApi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "county")
@NoArgsConstructor
@AllArgsConstructor
public class CountyEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "cityId")
    private CityEntity cityEntity;

    private String name;

}
