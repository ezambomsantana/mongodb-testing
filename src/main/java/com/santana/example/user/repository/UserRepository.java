package com.santana.example.user.repository;

import com.santana.example.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
