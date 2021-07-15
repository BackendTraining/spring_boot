package com.training.springbootbuyitem.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItemRequestDto {

    private String name;
    private String state;
    private String description;
    private String market;
    @PositiveOrZero
    private Integer stock;
    @PositiveOrZero
    private Double priceTag;

}
