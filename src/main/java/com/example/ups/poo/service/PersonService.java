package com.example.ups.poo.service;

import com.example.ups.poo.dto.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    //public static Object getAllPeople;
    List<PersonDTO> personList = new ArrayList<>();

    public ResponseEntity getAllPeople(){
        if(personList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List is Empty");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

    public ResponseEntity getPersonById(String id){
        for (PersonDTO person: personList){
            if(id.equalsIgnoreCase(person.getId())){
                return ResponseEntity.status(HttpStatus.OK).body(person);
            }
        }
        String message = "Person with id: " +id + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    }


    public ResponseEntity createPerson(PersonDTO person) {
        for (PersonDTO IdPerson : personList) {
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
    public  ResponseEntity updatePerson(PersonDTO person) {
        for (PersonDTO per : personList) {
            if (per.getId().equalsIgnoreCase(person.getId())) {
                if (person.getName() == null) {
                    per.setName(person.getName());
                }
                if (person.getLastname() == null) {
                    per.setLastname(person.getLastname());
                }
                if (person.getAge() < 0) {
                    per.setAge(person.getAge());
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("information");

    }
    public ResponseEntity deletePersonbyid(String id) {
        if (id != null && id.length() < 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person with id:" + id + "doesn't have required lenfth (10 chars min.");
        }
        for (PersonDTO person: personList){
            if(id.equalsIgnoreCase((person.getId()))){
                personList.remove(person);
                return ResponseEntity.status(HttpStatus.OK).body("Person with id: " + id + "was successfully deleted.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with:" + id + "was not found.");

    }
}





