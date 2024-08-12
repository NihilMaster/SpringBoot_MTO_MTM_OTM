package zzz.master.REST.party.MTM.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zzz.master.REST.party.MTM.Entities.PartyEntity;
import zzz.master.REST.party.MTM.Entities.PersonEntity;
import zzz.master.REST.party.MTM.Repositories.PartyRepository;
import zzz.master.REST.party.MTM.Repositories.PersonRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class JoinController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PartyRepository partyRepository;

    @GetMapping("/joins")
    public ResponseEntity<Collection<Map<String, Long>>> getAllJoins() {
        Collection<PersonEntity> people = personRepository.findAll();
        List<Map<String, Long>> joins = new ArrayList<>();

        for (PersonEntity person : people) {
            for (PartyEntity party : person.getParties()) {
                joins.add(Map.of("personId", person.getId(), "partyId", party.getId()));
            }
        }

        return new ResponseEntity<>(joins, HttpStatus.OK);
    }

    @PostMapping("/{personId}/join/{partyId}")
    public ResponseEntity<Void> addPersonToParty(@PathVariable long personId, @PathVariable long partyId) {
        PersonEntity person = personRepository.findById(personId);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PartyEntity party = partyRepository.findById(partyId);
        if (party == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        person.getParties().add(party);
        personRepository.save(person);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{personId}/join/{partyId}")
    public ResponseEntity<Void> removePersonFromParty(@PathVariable long personId, @PathVariable long partyId) {
        PersonEntity person = personRepository.findById(personId);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PartyEntity party = partyRepository.findById(partyId);
        if (party == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        person.getParties().remove(party);
        personRepository.save(person);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
