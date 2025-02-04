package com.example.ups.poo.controllers;

import com.example.ups.poo.dto.Person;
import com.example.ups.poo.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;


    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get-all-people")
    public ResponseEntity getAllPeople() {
        return personService.getAllPeople();
    }

    @PostMapping("/person")
    public ResponseEntity createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }
    @PutMapping("/update-person")
    public ResponseEntity updatePerson(@RequestBody Person Person){
        return personService.updatePerson(Person);
    }
    @GetMapping("/get-person")
    public ResponseEntity getPersonById (@RequestParam String id){
        return personService.getPersonById(id);
    }
}