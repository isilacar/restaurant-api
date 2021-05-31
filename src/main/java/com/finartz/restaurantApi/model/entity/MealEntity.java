package com.finartz.restaurantApi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "meal")
public class MealEntity extends BaseEntity {

    private String name;

    private Double price;

    @ManyToMany(mappedBy = "meals",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MenuEntity> menu;



}

