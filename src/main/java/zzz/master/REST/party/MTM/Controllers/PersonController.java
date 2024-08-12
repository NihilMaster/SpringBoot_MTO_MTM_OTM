package zzz.master.REST.party.MTM.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zzz.master.REST.party.MTM.Entities.PartyEntity;
import zzz.master.REST.party.MTM.Entities.PersonEntity;
import zzz.master.REST.party.MTM.Repositories.PersonRepository;


import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping
    public ResponseEntity<Collection<PersonEntity>> getAllPeople() {
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonEntity> getPersonById(@PathVariable long id) {
        PersonEntity person = personRepository.findById(id);
        if (person != null) {
            return new ResponseEntity<>(personRepository.findById(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/parties")
    public ResponseEntity<Collection<PartyEntity>> getPersonParties(@PathVariable long id) {
        PersonEntity person = personRepository.findById(id);
        return person == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(person.getParties(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity person) {
        return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable long id) {
        PersonEntity person = personRepository.findById(id);
        if (person != null) {
            personRepository.delete(person);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable int id, @RequestBody PersonEntity person) {
        PersonEntity optionalPerson = personRepository.findById(id);
        person.setId(id);
        return optionalPerson==null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(personRepository.save(person), HttpStatus.OK);
    }
}