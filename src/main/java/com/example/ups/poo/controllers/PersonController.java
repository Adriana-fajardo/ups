package com.example.ups.poo.controllers;

import com.example.ups.poo.dto.PersonDTO;
import com.example.ups.poo.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
//
//    @PostMapping("/person")
//    public ResponseEntity createPerson(@RequestBody PersonDTO person){
//        return personService.createPerson(person);
//    }
//    @PutMapping("/update-person")
//    public ResponseEntity updatePerson(@RequestBody PersonDTO Person){
//        return personService.updatePerson(Person);
//    }
//    @GetMapping("/get-person")
//    public ResponseEntity getPersonById (@RequestParam String id){
//        return personService.getPersonById(id);
//    }
//}