package com.finartz.restaurantApi.jUnitTest;

import com.finartz.restaurantApi.controller.MealController;
import com.finartz.restaurantApi.model.dto.MealDto;
import com.finartz.restaurantApi.model.request.CreateMealRequest;
import com.finartz.restaurantApi.security.JwtAuthenticationEntryPoint;
import com.finartz.restaurantApi.security.TokenManager;
import com.finartz.restaurantApi.service.aggregation.MealService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = MealController.class)
public class MealControllerTest {

    private static final Long MEAL_ID = 2L;
    private static final String URL = "/meal";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealService mealService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private TokenManager tokenManager;

    @MockBean
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Test
    public void saveMealTest() throws Exception {
        /*
       mockMvc.perform(get("/api/account/12345")
           .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
           .andExpect(jsonPath("$.accountId").value(12345))
           .andExpect(jsonPath("$.accountType").value("SAVINGS"))
           .andExpect(jsonPath("$.balance").value(5000.0));
}
         */

        CreateMealRequest mealRequest = new CreateMealRequest();
        mealRequest.setName("testYemek");
        mealRequest.setPrice(10.0);

        MealDto mealDto = getMealDto(mealRequest);

        when(mealService.saveMeal(any(CreateMealRequest.class))).thenReturn(mealDto);
        mockMvc.perform(post(URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("testYemek"))
                .andExpect(jsonPath("$.price").value(10.0))
                .andExpect(status().isOk());

    }

    private MealDto getMealDto(CreateMealRequest mealRequest) {
        MealDto mealDto = new MealDto();
        mealDto.setName(mealRequest.getName());
        mealDto.setPrice(mealRequest.getPrice());

        return mealDto;
    }

    @Test
    public void getByIdTest() throws Exception {
        MealDto mealDto = new MealDto();
        mealDto.setId(MEAL_ID);
        mealDto.setPrice(10.0);

        when(mealService.getMeal(anyLong())).thenReturn(mealDto);

        mockMvc.perform(get(URL + "/{mealId}", MEAL_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(10.0))
                .andExpect(status().isOk());

    }

    @Test
    public void getByMealNameTest() throws Exception {
        List<MealDto> mealDtoList = new ArrayList<>();

        MealDto mealDto = new MealDto();
        mealDto.setName("pizza");
        MealDto mealDto2 = new MealDto();
        mealDto2.setName("pilav");
        MealDto mealDto3 = new MealDto();
        mealDto3.setName("pilaki");
        MealDto mealDto4 = new MealDto();
        mealDto4.setName("makarna");

        mealDtoList.add(mealDto);
        mealDtoList.add(mealDto2);
        mealDtoList.add(mealDto3);
        mealDtoList.add(mealDto4);

        String mealName = "pi";
        List<MealDto> mealList = getMealDtoListByMealName(mealDtoList, mealName);

        when(mealService.getByMealName(anyString())).thenReturn(mealList);

        mockMvc.perform(get(URL + "/name/{mealName}", mealName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", is("pizza")))
                .andExpect(status().isOk());

    }

    private List<MealDto> getMealDtoListByMealName(List<MealDto> mealDtoList, String mealName) {
        List<MealDto> mealList = new ArrayList<>();
        for (MealDto meal : mealDtoList) {
            if (meal.getName().contains(mealName)) {
                mealList.add(meal);
            }
        }
        return mealList;
    }

}
