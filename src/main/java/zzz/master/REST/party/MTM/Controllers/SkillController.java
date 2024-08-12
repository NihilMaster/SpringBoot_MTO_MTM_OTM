package zzz.master.REST.party.MTM.Controllers;

import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zzz.master.REST.party.MTM.Entities.PersonEntity;
import zzz.master.REST.party.MTM.Entities.SkillEntity;
import zzz.master.REST.party.MTM.Repositories.SkillRepository;

import java.util.Collection;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public ResponseEntity<Collection<SkillEntity>> getAllSkills() {
        Collection<SkillEntity> skills = skillRepository.findAll();
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillEntity> getSkillById(@PathVariable long id) {
        SkillEntity skill = skillRepository.findById(id);
        return skill == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(skill);
    }

    @GetMapping("/{id}/person")
    public ResponseEntity<PersonEntity> getSkillPerson(@PathVariable long id) {
        SkillEntity skill = skillRepository.findById(id);
        return skill == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(skill.getPerson());
    }

    @PostMapping
    public ResponseEntity<SkillEntity> createSkill(@RequestBody SkillEntity skill) {
        return new ResponseEntity<>(skillRepository.save(skill), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable long id) {
        SkillEntity skill = skillRepository.findById(id);
        if (skill != null) {
            skillRepository.delete(skill);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillEntity> updateSkill(@PathVariable long id, @RequestBody SkillEntity skill) {
        SkillEntity optionalSkill = skillRepository.findById(id);
        skill.setId(id);
        return optionalSkill == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(skillRepository.save(skill), HttpStatus.OK);
    }
}
