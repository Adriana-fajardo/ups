package com.example.ups.poo.config;

import com.example.ups.poo.entity.Person;
import com.example.ups.poo.repository.PersonRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData  implements CommandLineRunner {

    private final PersonRepository personRepository;

    public BootStrapData(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        Person p1 = new Person();
        p1.setName("Adriana");
        p1.setLastname("Fajardo");
        p1.setAge(20);
        p1.setPersonId("0928550247");

        Person p2 = new Person();
        p2.setName("Luis");
        p2.setLastname("Alvarado");
        p2.setAge(21);
        p2.setPersonId("0928548570");

        Person p3 = new Person();
        p3.setName("Ana");
        p3.setLastname("Lopez");
        p3.setAge(22);
        p3.setPersonId("0920586775");

        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);


    }
}
