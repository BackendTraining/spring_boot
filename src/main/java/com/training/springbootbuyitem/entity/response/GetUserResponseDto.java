package com.training.springbootbuyitem.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponseDto extends CreateUserResponseDto {

    private String name;


}
