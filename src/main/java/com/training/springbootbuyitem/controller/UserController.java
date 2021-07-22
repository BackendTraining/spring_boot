package com.training.springbootbuyitem.controller;

import com.training.springbootbuyitem.entity.model.User;
import com.training.springbootbuyitem.entity.request.CreateUserRequestDto;
import com.training.springbootbuyitem.entity.response.CreateUserResponseDto;
import com.training.springbootbuyitem.entity.response.GetUserResponseDto;
import com.training.springbootbuyitem.entity.response.UpdateUserResponseDto;
import com.training.springbootbuyitem.service.UserService;
import com.training.springbootbuyitem.utils.annotation.ServiceOperation;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RefreshScope
@RestController
@RequestMapping("/users")
public class UserController implements IUserController {

    private final UserService userService;
    private final ModelMapper mapper;

    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ServiceOperation("getUsers")
    public ResponseEntity<List<GetUserResponseDto>> getUsers() {
        List<GetUserResponseDto> responseDTO = userService.list()
            .stream()
            .map(i -> mapper.map(i, GetUserResponseDto.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    @ServiceOperation("getUser")
    public ResponseEntity<GetUserResponseDto> getUser(@PathVariable("id") Long id) {
        User savedUser = userService.get(id);
        GetUserResponseDto responseDTO = mapper.map(savedUser, GetUserResponseDto.class);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Override
    @PostMapping
    @ServiceOperation("createUser")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody @Valid CreateUserRequestDto request) {
        User userFromRequest = mapper.map(request, User.class);
        User savedUser = userService.save(userFromRequest);
        CreateUserResponseDto responseDTO = mapper.map(savedUser, CreateUserResponseDto.class);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Override
    @PatchMapping("/{id}")
    @ServiceOperation("updateUser")
    public ResponseEntity<UpdateUserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        user.setUserUid(id);
        User savedUser = userService.update(user);
        UpdateUserResponseDto responseDTO = mapper.map(savedUser, UpdateUserResponseDto.class);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
