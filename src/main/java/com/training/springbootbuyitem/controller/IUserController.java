package com.training.springbootbuyitem.controller;

import com.training.springbootbuyitem.entity.model.User;
import com.training.springbootbuyitem.entity.request.CreateUserRequestDto;
import com.training.springbootbuyitem.entity.response.CreateUserResponseDto;
import com.training.springbootbuyitem.entity.response.GetUserResponseDto;
import com.training.springbootbuyitem.entity.response.UpdateUserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IUserController {

    @GetMapping("/user/{id}")
    ResponseEntity<GetUserResponseDto> getUser(@PathVariable("id") Long id);

    @GetMapping
    ResponseEntity<List<GetUserResponseDto>> getUsers();

    @PostMapping("/user")
    ResponseEntity<CreateUserResponseDto> createUser(@RequestBody @Valid CreateUserRequestDto request);

    @PatchMapping("/user/{id}")
    ResponseEntity<UpdateUserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody User user);

}
