package com.finartz.restaurantApi.model.converter.dto.impl;

import com.finartz.restaurantApi.model.dto.MealMenuDto;
import com.finartz.restaurantApi.model.entity.MenuEntity;
import com.finartz.restaurantApi.model.request.MealMenuRequest;
import org.springframework.stereotype.Component;

@Component
public class MealMenuDtoConverter{

    public MealMenuDto convert(MealMenuRequest mealMenuRequest){
        if(mealMenuRequest==null){
            return null;
        }
        MealMenuDto mealMenuDto=new MealMenuDto();
        mealMenuDto.setMealId(mealMenuRequest.getMealId());
        mealMenuDto.setMenuId(mealMenuRequest.getMenuId());

        return mealMenuDto;
    }


    public MealMenuDto convertToDto(MenuEntity entity) {
        if(entity==null){
            return null;
        }
        MealMenuDto mealMenuDto=new MealMenuDto();
        mealMenuDto.setMenuId(entity.getId());

        return mealMenuDto;
    }
}
