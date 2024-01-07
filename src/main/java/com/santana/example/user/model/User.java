package com.santana.example.user.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("user")
public class User {
    @Id
    private String id;
    private String name;
    private String city;
    private Integer age;
    private Float salary;
}
