package com.training.springbootbuyitem.controller;

import com.training.springbootbuyitem.entity.response.CreateUserResponseDto;
import com.training.springbootbuyitem.entity.response.GetItemResponseDto;
import com.training.springbootbuyitem.service.UserService;
import com.training.springbootbuyitem.utils.annotation.ServiceOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    @ServiceOperation("getUser")
    public ResponseEntity<CreateUserResponseDto> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(mapper.map(userService.getById(id), CreateUserResponseDto.class), HttpStatus.OK);
    }


}
