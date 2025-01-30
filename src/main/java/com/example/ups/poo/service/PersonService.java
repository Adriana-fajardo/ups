package com.example.ups.poo.service;

import com.example.ups.poo.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    //public static Object getAllPeople;
    List<Person> personList = new ArrayList<>();

    public ResponseEntity getAllPeople(){
        if(personList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List is Empty");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personList);
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
    //public ResponseEntity createPerson(Person person) {
    //    personList.add(person);
    //    return ResponseEntity.status(HttpStatus.OK).body("Person Succesfully Registered");


    public ResponseEntity createPerson(Person person) {
        for (Person IdPerson : personList) {
            if (IdPerson.getId().equalsIgnoreCase(person.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person with this ID already exists.");
            }
        }
        if (person.getName() == null || person.getLastname() == null || person.getAge() <= 0 || person.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields for person.");
        }
        personList.add(person);
        return ResponseEntity.status(HttpStatus.OK).body("Person added to the list.");
    }
}



