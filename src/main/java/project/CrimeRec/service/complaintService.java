package project.CrimeRec.service;

import org.springframework.stereotype.Service;
import project.CrimeRec.exception.CustomException;
import project.CrimeRec.repository.ComplaintRepository;
import project.CrimeRec.model.Complaint;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class complaintService {

    private final ComplaintRepository complaintRepository;
    private int id;

    public complaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;


    }

    public void addComplaint(Complaint complaint) throws CustomException {
       if(complaint.getComplaintCreator().isBlank()) {
           throw new CustomException("Invalid complaint data: Complaint creator cannot be empty.");
       }
        if(complaint.getComplaintReceiver().isBlank()) {
            throw new CustomException("Invalid complaint data: Complaint receiver cannot be empty.");
        }
        if(complaint.getComplaintText().isBlank()) {
            throw new CustomException("Invalid complaint data: Complaint description cannot be empty.");
        }


        complaintRepository.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        complaintRepository.findAll().forEach(complaints::add);
        return complaints;
    }

    public void deleteComplaintById(Long complaintId) throws CustomException {
        if (complaintId == null) {
            throw new CustomException("Invalid complaint data: Complaint ID cannot be null.");
        }
        if (complaintId <= 0) {
            throw new CustomException("Invalid complaint data: Invalid complaint ID.");
        }
        complaintRepository.deleteById(complaintId);
    }

    public void deleteComplaintByReceiver(String complaintReceiver) throws CustomException {
        if (complaintReceiver == null || complaintReceiver.isBlank()) {
            throw new CustomException("Invalid complaint data: Complaint receiver cannot be empty");
        }
        if (!complaintRepository.existsByComplaintReceiver(complaintReceiver)) {
            throw new CustomException("Invalid complaint data: Complaints with receiver " + complaintReceiver + " not found");
        }
        complaintRepository.deleteByComplaintReceiver(complaintReceiver);
    }

    public void deleteByComplaintCreator(String complaintCreator) throws CustomException {
        if (complaintCreator == null || complaintCreator.isBlank()) {
            throw new CustomException("Invalid complaint data: Complaint creator cannot be empty");
        }
        if (!complaintRepository.existsByComplaintCreator(complaintCreator)) {
            throw new CustomException("Invalid complaint data: Complaints with creator " + complaintCreator + " not found");
        }
        complaintRepository.deleteByComplaintCreator(complaintCreator);
    }

    public Complaint getComplaintById(Long id) throws CustomException {
        if (id == null || id <= 0) {
            throw new CustomException("Invalid complaint data: Invalid complaint ID");
        }
        Optional<Complaint> optionalComplaint = complaintRepository.findById(id);
        if (!optionalComplaint.isPresent()) {
            throw new CustomException("Invalid complaint data: Complaint not found");
        }
        return optionalComplaint.orElse(null);
    }

    public void updateComplaint(Complaint existingComplaint) throws CustomException {
        if (existingComplaint == null) {
            throw new CustomException("Invalid complaint data: Complaint cannot be null");
        }

        // maybe unecessary
        if (existingComplaint.getComplaintText().isEmpty()) {
            throw new CustomException("Invalid complaint data: Complaint text cannot be empty");
        }
        if (existingComplaint.getComplaintCreator().isEmpty()) {
            throw new CustomException("Invalid complaint data: Complaint creator cannot be empty");
        }
        if (existingComplaint.getComplaintReceiver().isEmpty()) {
            throw new CustomException("Invalid complaint data: Complaint receiver cannot be empty");
        }
        complaintRepository.save(existingComplaint);
    }
}
