package com.training.springbootbuyitem.controller;

import com.training.springbootbuyitem.entity.model.User;
import com.training.springbootbuyitem.entity.request.CreateUserRequestDto;
import com.training.springbootbuyitem.entity.response.CreateUserResponseDto;
import com.training.springbootbuyitem.entity.response.GetUserResponseDto;
import com.training.springbootbuyitem.entity.response.UpdateUserResponseDto;
import com.training.springbootbuyitem.utils.annotation.ServiceOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IUserController {

    @GetMapping("/{id}")
    @ServiceOperation("getUser")
    ResponseEntity<GetUserResponseDto> getUser(@PathVariable("id") Long id);

    @GetMapping("/all")
    @ServiceOperation("getUser")
    ResponseEntity<List<GetUserResponseDto>> getUsers();

    @PostMapping
    @ServiceOperation("createUser")
    ResponseEntity<CreateUserResponseDto> createUser(@RequestBody @Valid CreateUserRequestDto request);

    @PatchMapping("/{id}")
    @ServiceOperation("updateUser")
    ResponseEntity<UpdateUserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody User user);
}
