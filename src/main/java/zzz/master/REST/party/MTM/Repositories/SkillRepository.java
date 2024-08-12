package zzz.master.REST.party.MTM.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zzz.master.REST.party.MTM.Entities.SkillEntity;

import java.util.Collection;

@Repository
public interface SkillRepository extends CrudRepository<SkillEntity, Long> {

    Collection<SkillEntity> findAll();

    SkillEntity findById(long id);
}
