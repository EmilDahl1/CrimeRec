package project.CrimeRec.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.CrimeRec.model.Inmate;
import project.CrimeRec.model.Log;

@Repository
public interface inmateRepository extends CrudRepository<Inmate, Long> {


}
