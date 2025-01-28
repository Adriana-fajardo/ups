package com.example.ups.poo.service;

import com.example.ups.poo.dto.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

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
}





//public class PersonService {
//        public List<Person>getAllPeople(){
//            List<Person> personList = new ArrayList<>();
//            Person p1 = new Person("adriana","fajardo",20,"0918432657");
//            Person p2 = new Person("cinthia","Herrera",47,"0958236672");
//            personList.add(p1);
//            personList.add(p2);
//           return personList;
//       }
//    }

