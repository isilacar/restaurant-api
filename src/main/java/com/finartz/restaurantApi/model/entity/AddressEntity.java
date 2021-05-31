package com.finartz.restaurantApi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class AddressEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    private CityEntity cityEntity;

    @OneToOne
    @JoinColumn(name = "countyId")
    private CountyEntity countyEntity;

    private Long districtId;

    private int zipCode;

    private String detail;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userEntity;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private RestaurantEntity restaurantEntity;


}
