package com.finartz.restaurantApi.model.entity;

import com.finartz.restaurantApi.model.enumeration.UserRoleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class UserEntity extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    private UserRoleStatus role;

    private String userName;
    private String password;
    private Double latitude;
    private Double longitude;
    private String eMail;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "userEntity",fetch = FetchType.LAZY)
    private List<AddressEntity> addresses=new ArrayList<>();

}
