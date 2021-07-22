package com.training.springbootbuyitem.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO implements Serializable {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}