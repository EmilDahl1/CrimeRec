package project.CrimeRec.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.CrimeRec.model.Offense;

@Repository
public interface OffenseRepository extends CrudRepository<Offense, String> {

    @Modifying  // Required for delete operations
    @Query(value = "DELETE FROM offense WHERE name = ?1", nativeQuery = true)
    void deleteByName(String name);
}
