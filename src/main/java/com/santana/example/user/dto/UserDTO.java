package com.santana.example.user.dto;

import com.santana.example.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String id;
    private String name;
    private String city;
    private Integer age;
    private Float salary;

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setCity(user.getCity());
        userDTO.setAge(user.getAge());
        userDTO.setSalary(user.getSalary());
        return userDTO;
    }
}
