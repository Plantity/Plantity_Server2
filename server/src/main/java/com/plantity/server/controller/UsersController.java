package com.plantity.server.controller;

import com.plantity.server.domain.users.Users;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersRepository userRepository;
    private final UsersService userService;

    // user 목록 조회
    @GetMapping("/users")
    public List<Users> getUsers(){
        return userRepository.findAll();
    }
}
