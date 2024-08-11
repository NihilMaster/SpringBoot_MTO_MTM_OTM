package zzz.master.REST.party.MTM.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zzz.master.REST.party.MTM.Entities.PartyEntity;

import java.util.Collection;

@Repository
public interface PartyRepository extends CrudRepository<PartyEntity, Long> {

    Collection<PartyEntity> findAll();
    PartyEntity findByName(String name);
}
