package project.CrimeRec.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.CrimeRec.model.Complaint;

import java.util.List;

@Repository
public interface ComplaintRepository extends CrudRepository<Complaint, Long> {

    // Delete by complaintReceiver (query by method name)
    List<Complaint> deleteByComplaintReceiver(String complaintReceiver);

    // Delete by complaintCreator (query by method name)
    List<Complaint> deleteByComplaintCreator(String complaintCreator);

    boolean existsByComplaintReceiver(String complaintReceiver);

    boolean existsByComplaintCreator(String complaintCreator);
}
