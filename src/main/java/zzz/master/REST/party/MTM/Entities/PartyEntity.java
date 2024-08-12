package zzz.master.REST.party.MTM.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parties")
public class PartyEntity {

    @Id
    @GeneratedValue
    @Column(name = "party_id")
    private long id;

    @Column(name = "party_location")
    private String location;

    @Column(name = "party_date")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date date;

    @ManyToMany
    @JoinTable(name = "parties_people",
            joinColumns = @JoinColumn(name = "party_id", referencedColumnName = "party_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "person_id"))
    private Set<PersonEntity> people = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<PersonEntity> getPeople() {
        return people;
    }

    public void setPeople(Set<PersonEntity> people) {
        this.people = people;
    }
}
