package com.training.springbootbuyitem.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponseDto extends CreateUserResponseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
