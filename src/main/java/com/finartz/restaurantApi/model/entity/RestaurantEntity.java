package com.finartz.restaurantApi.model.entity;

import com.finartz.restaurantApi.model.enumeration.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
public class RestaurantEntity extends BaseEntity {

    private String name;

    @Enumerated(value = EnumType.STRING)
    private StatusEnum status;

    @OneToOne(mappedBy = "restaurant",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private MenuEntity menu;

    private Double latitude;
    private Double longitude;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "restaurantEntity",fetch = FetchType.LAZY)
    private List<AddressEntity> addressEntities=new ArrayList<>();
}
