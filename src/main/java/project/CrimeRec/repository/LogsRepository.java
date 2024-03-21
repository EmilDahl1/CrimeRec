package project.CrimeRec.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.CrimeRec.model.Complaint;
import project.CrimeRec.model.Log;

@Repository
public interface LogsRepository extends CrudRepository<Log, Long> {
}
