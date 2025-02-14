package com.example.ups.poo.service;

import com.example.ups.poo.UpsApplication;
import com.example.ups.poo.dto.PersonDTO;
import com.example.ups.poo.entity.Person;
import com.example.ups.poo.repository.PersonRepository;
import org.hibernate.sql.Update;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private PersonDTO mapPersonToPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName() + " " + person.getLastname());
        personDTO.setAge(person.getAge());
        personDTO.setId(person.getPersonId());

        return personDTO;
    }

    private Person mapPersonDTOtoPerson(PersonDTO personDTO) {
        if (personDTO == null) {

            return mapPersonDTOtoPerson(personDTO);
        }
        Person person = new Person();


        if (personDTO.getName() == null || personDTO.getName().isBlank()) {
            return mapPersonDTOtoPerson(personDTO);
        }

        String[] nameArray = personDTO.getName().split(" ");
        String name = nameArray[0];
        String lastname = nameArray.length > 1 ? nameArray[1] : "";


        person.setName(name);
        person.setLastname(lastname);
        person.setPersonId(personDTO.getId());
        person.setAge(personDTO.getAge());

        return person;

    }

    public ResponseEntity findByPersonId(String id) {
        Optional<Person> personOptional = personRepository.findByPersonId(id);
        if (personOptional.isPresent()) {
            PersonDTO personDTO = mapPersonToPersonDTO(personOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id: " + id + " not found");
        }
    }


    public ResponseEntity createPerson(PersonDTO personDTO) {
        if (personDTO.getId() == null || personDTO.getId().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please insert an ID.");
        }

        if (personRepository.findByPersonId(personDTO.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID already registered.");
        }

        if (personDTO.getName() == null || personDTO.getName().split(" ").length != 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please insert one name and lastname separated by an space.");
        }

        if (personDTO.getAge() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please insert an age.");
        }

        Person person = mapPersonDTOtoPerson(personDTO);
        personRepository.save(person);

        return ResponseEntity.status(HttpStatus.CREATED).body("Person successfully registered.");
    }

    public ResponseEntity updatePerson(PersonDTO personDTO) {
        if (personDTO == null || personDTO.getId() == null || personDTO.getId().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person ID is required");
        }
        Person person1 = mapPersonDTOtoPerson(personDTO);
        Optional<Person> personOptional = personRepository.findByPersonId(personDTO.getId());
        if (personOptional.isPresent()) {

            Person per = personOptional.get();
            if (personDTO.getName() != null) {
                per.setName(personDTO.getName());
            }
            if (personDTO.getAge() != 0) {
                per.setAge(personDTO.getAge());
            }
        }
        personRepository.save(person1);
        return ResponseEntity.status(HttpStatus.OK).body("Person with Id :" + personDTO.getId() + " was successfully");
    }


    public ResponseEntity deletePersonById(String id) {
        Optional<Person> personOptional = personRepository.findByPersonId(id);
        if (personOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with ID: " + id + " was not found");
        }

        personRepository.delete(personOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully.");


    }

    public ResponseEntity getPersonById(String id) {
        Optional<Person> personOptional = personRepository.findByPersonId(id);
        if (personOptional.isPresent()) {
            PersonDTO personDTO = mapPersonToPersonDTO(personOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);
        } else {
            String message = "Person with id: " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    public ResponseEntity getAllPeople() {
        List<PersonDTO> personDTOList = fetchAllPeopleRecords();
        if (personDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person list is empty");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personDTOList);
    }

    private List<PersonDTO> fetchAllPeopleRecords() {
        Iterable<Person> personIterable = personRepository.findAll();
        List<PersonDTO> personDTOList = new ArrayList<>();

        for (Person per : personIterable) {
            PersonDTO personDTO = mapPersonToPersonDTO(per);
            personDTOList.add(personDTO);
        }
        return personDTOList;
    }
    }



