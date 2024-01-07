package com.santana.example.user.controller;

import com.santana.example.user.dto.CityAggregationDTO;
import com.santana.example.user.dto.UserDTO;
import com.santana.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> listUsers() {
        return userService.listUsers();
    }

    @PostMapping
    public void saveUser(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
    }

    @GetMapping("/avg")
    public List<CityAggregationDTO> getAvg() {
        return userService.getAvg();
    }
}
