package com.training.springbootbuyitem.entity.request;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.PositiveOrZero;

@Slf4j
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
