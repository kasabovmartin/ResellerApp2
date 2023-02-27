package com.resellerapp.model.dto;

import com.resellerapp.model.entity.ConditionName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddOfferDTO {

    @Size(min = 2, max = 50, message = "Description must be between 2 and 50 characters")
    private String description;
    @NotNull
    @Min(value = 0)
    private Double price;
    @NotNull
    private ConditionName conditionName;
    private Long sellerId;
}
