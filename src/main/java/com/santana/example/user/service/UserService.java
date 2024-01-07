package com.santana.example.user.service;

import com.mongodb.client.MongoClient;
import com.santana.example.user.dto.CityAggregationDTO;
import com.santana.example.user.dto.UserDTO;
import com.santana.example.user.model.User;
import com.santana.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final MongoClient mongoClient;

    public void saveUser(UserDTO userDTO) {
        var user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userDTO.getName());
        user.setCity(userDTO.getCity());
        user.setAge(userDTO.getAge());
        user.setSalary(userDTO.getSalary());
        userRepository.save(user);
    }

    public List<UserDTO> listUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(model -> UserDTO.convert(model))
                .toList();
    }

    public List<CityAggregationDTO> getAvg() {
        var database = mongoClient.getDatabase("test");
        var collection = database.getCollection("user");
        var result = collection.aggregate(Arrays.asList(new Document("$group",
                new Document("_id", "$city")
                        .append("avgAge",
                                new Document("$avg", "$age"))
                        .append("avgSalary",
                                new Document("$avg", "$salary")))));

        List<CityAggregationDTO> response = new ArrayList<>();
        for (Document doc : result) {
            var cityAggregationDTO = new CityAggregationDTO();
            cityAggregationDTO.setCity(doc.getString("_id"));
            cityAggregationDTO.setAvgSalary(doc.getDouble("avgSalary"));
            cityAggregationDTO.setAvgAge(doc.getDouble("avgAge"));
            response.add(cityAggregationDTO);
        }
        return response;
    }

}
