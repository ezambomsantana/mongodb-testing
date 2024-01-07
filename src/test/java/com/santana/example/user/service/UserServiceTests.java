package com.santana.example.user.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.santana.example.user.dto.CityAggregationDTO;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AutoConfigureDataMongo
@SpringBootTest
public class UserServiceTests {


    @Autowired
    private UserService userService;

    @Autowired
    private MongoClient mongoClient;

    @BeforeEach
    void setup() {
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("user");

        collection.deleteMany(new Document());

        List<Document> documents = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            Document document = new Document();
            document.put("name", "Eduardo");
            document.put("city", "SP");
            document.put("age", 20);
            document.put("salary", 1000 * (i + 1));

            documents.add(document);
        }

        collection.insertMany(documents);

    }

    @Test
    void test_aggregateUsersSuccessfully() {
        List<CityAggregationDTO> page = userService
                .getAvg();

        Assertions.assertEquals("SP", page.get(0).getCity());
        Assertions.assertEquals(20, page.get(0).getAvgAge());
        Assertions.assertEquals(3000, page.get(0).getAvgSalary());
    }
}
