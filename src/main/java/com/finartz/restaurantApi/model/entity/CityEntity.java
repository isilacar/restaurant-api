package com.finartz.restaurantApi.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city")
public class CityEntity extends BaseEntity{
    private String name;
    private int licensePlate;

}
