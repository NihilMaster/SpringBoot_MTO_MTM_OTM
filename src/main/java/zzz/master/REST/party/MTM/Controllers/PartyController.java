package zzz.master.REST.party.MTM.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zzz.master.REST.party.MTM.Entities.PartyEntity;
import zzz.master.REST.party.MTM.Entities.PersonEntity;
import zzz.master.REST.party.MTM.Repositories.PartyRepository;

import java.util.Collection;

@RestController
@RequestMapping("/api/parties")
public class PartyController {

    @Autowired
    private PartyRepository partyRepository;

    @GetMapping
    public ResponseEntity<Collection<PartyEntity>> getAllParties() {
        return new ResponseEntity<Collection<PartyEntity>>(partyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyEntity> getPartyById(@PathVariable long id) {
        return new ResponseEntity<PartyEntity>(partyRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/people")
    public ResponseEntity<Collection<PersonEntity>> getPartyPersons(@PathVariable long id) {
        PartyEntity party = partyRepository.findById(id);
        return (party==null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(party.getPeople(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<PartyEntity> createParty(@RequestBody PartyEntity party) {
        return new ResponseEntity<PartyEntity>(partyRepository.save(party), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PartyEntity> deleteParty(@PathVariable long id) {
        PartyEntity party = partyRepository.findById(id);
        partyRepository.delete(party);
        return new ResponseEntity<PartyEntity>(party, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartyEntity> updateParty(@PathVariable int id, @RequestBody PartyEntity party) {
        PartyEntity optionalParty = partyRepository.findById(id);
        party.setId(id);
        return optionalParty==null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(partyRepository.save(party), HttpStatus.OK);
    }
}
