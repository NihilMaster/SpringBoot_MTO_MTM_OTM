package zzz.master.REST.party.MTM.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zzz.master.REST.party.MTM.Entities.PersonEntity;

import java.util.Collection;

@Repository
public interface PersonRepository  extends CrudRepository<PersonEntity, Long> {

    Collection<PersonEntity> findAll();

    PersonEntity findById(long id);

}
