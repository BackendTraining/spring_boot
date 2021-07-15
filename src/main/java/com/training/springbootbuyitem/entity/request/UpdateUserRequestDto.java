package com.training.springbootbuyitem.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {

    private String firstName;
    private String lastName;

}
