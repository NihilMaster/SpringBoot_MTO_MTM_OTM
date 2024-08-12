package zzz.master.REST.party.MTM.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "people")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "person_id")
    private long id;

    @Column(name = "person_name")
    private String name;

    @Column(name = "person_age")
    private int age;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<SkillEntity> skills = new HashSet<>();

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "parties_people",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "party_id", referencedColumnName = "party_id"))
    private Set<PartyEntity> parties = new HashSet<>();

    public PersonEntity() {
        super();
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
    }

    public Set<PartyEntity> getParties() {
        return parties;
    }

    public void setParties(Set<PartyEntity> parties) {
        this.parties = parties;
    }
}
