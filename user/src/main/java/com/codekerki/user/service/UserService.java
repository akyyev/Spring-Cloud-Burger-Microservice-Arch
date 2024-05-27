package com.codekerki.user.service;

import com.codekerki.user.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    private final List<UserDto> users = new ArrayList<>();

    public UserService() {
        users.add(new UserDto(123L, "Peter", true));
        users.add(new UserDto(456L, "John", true));
        users.add(new UserDto(101L, "Jake", false));
        users.add(new UserDto(111L, "Gary", true));
    }

    public List<UserDto> getUsers() {
        System.out.println("DataBase URL: " + databaseUrl);
        return users;
    }

    public UserDto getUser(Long id) {
        var user = users.stream().filter(userDto -> userDto.getId().equals(id)).findAny();
        return user.orElse(null);
    }

    public boolean addUser(UserDto userDto) {
        userDto.setId(users.size() + 100L);
        return users.add(userDto);
    }

    public UserDto updateUser(UserDto userDto) {
        var user = users.stream().filter(e -> e.getId().equals(userDto.getId())).findAny();
        if(user.isPresent()) {
            user.get().setName(userDto.getName());
            user.get().setActive(userDto.isActive());
            return user.get();
        }
        return null;
    }

    public boolean removeUser(Long id) {
        var user = users.stream().filter(e -> e.getId().equals(id)).findAny();
        user.ifPresent(users::remove);
        return user.isPresent();
    }

    public boolean validateUser(Long id) {
        var user = users.stream().filter(e -> e.getId().equals(id)).findAny();
        return user.isPresent();
    }

}
