package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController( PersonService personService ) {

        this.personService = personService;

    }
//    @RequestMapping("/addperson")
@PostMapping
    public void addPersone( @RequestBody Person person){
        System.out.println(person.getName());
        personService.addPerson(person);
    }


    @GetMapping
    public List<Person> getPeolple(){
        return personService.getPeople();
    }


    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.selectPersonById(id).orElse(null);
    }


}
