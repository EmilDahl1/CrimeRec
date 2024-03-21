package project.CrimeRec.service;

import org.springframework.stereotype.Service;
import project.CrimeRec.model.Complaint;
import project.CrimeRec.model.Inmate;
import project.CrimeRec.repository.ComplaintRepository;
import project.CrimeRec.repository.inmateRepository;

@Service
public class inmateService {

    private final inmateRepository inmateRepository;

    public inmateService(inmateRepository inmateRepository) {
        this.inmateRepository = inmateRepository;


    }

    public void addInmate(Inmate inmate) {
        inmateRepository.save(inmate);
    }


    public void deleteInmateById(Long indent) {
        inmateRepository.deleteById(indent);
    }

}
