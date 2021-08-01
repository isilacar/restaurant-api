package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.base.repository.BaseRepository;
import com.finartz.restaurantApi.dao.AddressRepository;
import com.finartz.restaurantApi.model.entity.AddressEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
class AddressRepositoryImpl extends BaseRepository<AddressEntity> implements AddressRepository {

    @Transactional
    @Override
    public AddressEntity saveAddress(AddressEntity addressEntity) {
        save(addressEntity);
        return addressEntity;

    }


}
