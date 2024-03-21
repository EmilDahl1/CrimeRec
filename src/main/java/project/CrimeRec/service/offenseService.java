package project.CrimeRec.service;

import org.springframework.stereotype.Service;
import project.CrimeRec.model.Inmate;
import project.CrimeRec.model.Offense;
import project.CrimeRec.repository.OffenseRepository;

@Service
public class offenseService {

    private final OffenseRepository offenseRepository;

    public offenseService(OffenseRepository offenseRepository) {
        this.offenseRepository = offenseRepository;
    }

    public void addOffense(Offense offense) {
        offenseRepository.save(offense);
    }


    public void deleteOffenseByName(String name) {offenseRepository.deleteByName(name);
    }
}
