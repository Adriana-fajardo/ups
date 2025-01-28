package com.example.ups.poo.service;

import com.example.ups.poo.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private static Object getAllPeople;
    List<Person> personList = new ArrayList<>();


    public List<Person> getAllPeople() {
        Person p1 = new Person();
        p1.setName("Adriana");
        p1.setLastname("Fajardo");
        p1.setAge(20);
        p1.setId("p001");
        Person p2 = new Person("cinthia","Herrera",47,"0958236672");
        personList.add(p1);
        personList.add(p2);
        return personList;
    }
    public ResponseEntity getPersonById(String id){
        for (Person person: personList){
            if(id.equalsIgnoreCase(person.getId())){
                return ResponseEntity.status(HttpStatus.OK).body(person);
            }
        }
        String message = "Person with id: " +id + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    }
}

