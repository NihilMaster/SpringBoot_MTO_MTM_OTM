package zzz.master.REST.party.MTM.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zzz.master.REST.party.MTM.Entities.PartyEntity;
import zzz.master.REST.party.MTM.Entities.PersonEntity;
import zzz.master.REST.party.MTM.Repositories.PersonRepository;

import java.util.Collection;

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
    public ResponseEntity<PersonEntity> getPersonById(long id) {
        PersonEntity person = personRepository.findById(id).orElseThrow();
        if (person != null) {
            return new ResponseEntity<>(personRepository.findById(id).orElseThrow(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/parties")
    public ResponseEntity<Collection<PartyEntity>> getPersonParties(long id) {
        PersonEntity person = personRepository.findById(id).orElseThrow();
        if (person != null) {
            return new ResponseEntity<>(person.getParties(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity person) {
        return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonEntity> deletePerson(long id) {
        PersonEntity person = personRepository.findById(id).orElseThrow();
        if (person != null) {
            personRepository.delete(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@RequestBody PersonEntity person) {
        return new ResponseEntity<>(personRepository.save(person), HttpStatus.OK);
    }
}
