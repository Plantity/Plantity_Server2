package com.plantity.server.controller;

import com.plantity.server.domain.users.Users;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersRepository userRepository;
    private final UsersService userService;

    @Autowired
    UsersService us;

    // user 목록 조회
    @GetMapping("/users")
    public List<Users> getUsers(){
        return userRepository.findAll();
    }



}
