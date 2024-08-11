package zzz.master.REST.party.MTM.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class SkillEntity {

    @Id
    @GeneratedValue
    @Column(name = "skill_id")
    private long id;

    @Column(name = "skill_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level")
    private LevelEntity level;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LevelEntity getLevel() {
        return level;
    }

    public void setLevel(LevelEntity level) {
        this.level = level;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
