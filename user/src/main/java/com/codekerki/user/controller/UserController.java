package com.codekerki.user.controller;

import com.codekerki.user.model.UserDto;
import com.codekerki.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired UserService handler;

    @GetMapping("/all")
    public List<UserDto> getUsers() {
        return handler.getUsers();
    }

    @GetMapping
    public UserDto getUser(@RequestParam(name = "id") Long id) {
        return handler.getUser(id);
    }

    @PostMapping
    public boolean addUser(@Validated @RequestBody UserDto userDto) {
        return handler.addUser(userDto);
    }

    @PutMapping
    public UserDto updateUser(@Validated @RequestBody UserDto userDto) {
        return handler.updateUser(userDto);
    }

    @DeleteMapping
    public boolean removeUser(@RequestParam(name = "id") Long id) {
        return handler.removeUser(id);
    }

    @GetMapping("/validate")
    public boolean validateUser(@RequestParam(name = "id") Long id) {
        return handler.validateUser(id);
    }
}
