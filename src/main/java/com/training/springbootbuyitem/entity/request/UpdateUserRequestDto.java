package com.training.springbootbuyitem.entity.request;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Slf4j
public class UpdateUserRequestDto {

    private String firstName;
    private String lastName;

}
