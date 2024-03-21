package project.CrimeRec.service;

import org.springframework.stereotype.Service;
import project.CrimeRec.repository.ComplaintRepository;
import project.CrimeRec.model.Complaint;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyService {

    private final ComplaintRepository complaintRepository;
    private int id;

    public MyService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;


    }

    public void addComplaint(Complaint complaint) {
        complaintRepository.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        complaintRepository.findAll().forEach(complaints::add);
        return complaints;
    }

    public void deleteComplaintById(Long complaintid) {
        complaintRepository.deleteById(complaintid);
    }

    public void deleteComplaintByReceiver(String complaintReceiver) {
        complaintRepository.deleteByComplaintReceiver(complaintReceiver);
    }

    public void deleteByComplaintCreator(String complaintCreator) {
        complaintRepository.deleteByComplaintCreator(complaintCreator);
    }

    public Complaint getComplaintById(Long id) {
        Optional<Complaint> optionalComplaint = complaintRepository.findById(id);
        return optionalComplaint.orElse(null);
    }

    public void updateComplaint(Complaint existingComplaint) {
        complaintRepository.save(existingComplaint);
    }
}
