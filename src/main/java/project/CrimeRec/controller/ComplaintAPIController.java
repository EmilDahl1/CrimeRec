package project.CrimeRec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.CrimeRec.exception.CustomException;
import project.CrimeRec.model.Complaint;
import project.CrimeRec.service.complaintService;

import java.util.List;
import java.util.Optional;

@RestController
public class ComplaintAPIController {

    private final complaintService complaintService;

    @Autowired
    public ComplaintAPIController(complaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/complaints/add")
    public ResponseEntity<String> addComplaint(@RequestBody Complaint complaint) throws CustomException {
        complaintService.addComplaint(complaint);
        return ResponseEntity.ok("Complaint added successfully");
    }



    @GetMapping("/complaints/getAll")
    public ResponseEntity<List<Complaint>> getComplaints() {
        List<Complaint> complaints = complaintService.getAllComplaints();
        System.out.println(complaints.size());
        return ResponseEntity.ok(complaints);
    }


    /**
     * lös problem med att delete på namn, kan klasha med att det är samma skapare som receiver på en annan.
     * Verkar också vara problem med att converta från String till long?
     * @param complaintid
     * @param complaintReceiver
     * @param complaintCreator
     * @return
     */

    @DeleteMapping("/complaints/delete/{complaintid}")
    public ResponseEntity<String> deleteComplaint(
            @PathVariable Long complaintid,
            @RequestParam(value = "complaintReceiver", required = false) String complaintReceiver,
            @RequestParam(value = "complaintCreator", required = false) String complaintCreator) throws CustomException {

        if (complaintid!=null){
            complaintService.deleteComplaintById(complaintid);
        } else if (complaintReceiver!=null) {
            complaintService.deleteComplaintByReceiver(complaintReceiver);
        } else if (complaintCreator!=null) {
            complaintService.deleteByComplaintCreator(complaintCreator);
        } else {
            return  ResponseEntity.badRequest().body("Please specify which complaint or complaints to delete.");
        }
        return ResponseEntity.ok("Complaint or complaints deleted successfully");

    }


    @GetMapping("/complaints/getById/{complaintid}")
    public ResponseEntity<Complaint> getComplaint( @PathVariable Long complaintid) throws CustomException {
        Complaint complaint = complaintService.getComplaintById(complaintid);
        return ResponseEntity.ok(complaint);
    }


    @PutMapping("/complaints/updateComplaint/{complaintid}")
    public ResponseEntity<Complaint> updateComplaint(
            @PathVariable Long complaintid,
            @RequestBody Complaint updatedComplaint,
            @RequestParam(name = "complaintReceiver", required = false) Optional<String> optionalComplaintReceiver,
            @RequestParam(name = "complaintCreator", required = false) Optional<String> optionalComplaintCreator,
            @RequestParam(name = "complaintText", required = false) Optional<String> optionalComplaintText
    ) throws CustomException {
        Complaint existingComplaint = complaintService.getComplaintById(complaintid);
        if (existingComplaint == null) {
            return ResponseEntity.notFound().build();
        }

        boolean isModified = false;

        if (optionalComplaintText.isPresent()) {
            existingComplaint.setComplaintText(optionalComplaintText.get());
            isModified = true;
        }
        if (optionalComplaintReceiver.isPresent()) {
            existingComplaint.setComplaintReceiver(optionalComplaintReceiver.get());
            isModified = true;
        }
        if (optionalComplaintCreator.isPresent()) {
            existingComplaint.setComplaintCreator(optionalComplaintCreator.get());
            isModified = true;
        }

        if (!isModified) {
            return createErrorResponse("No update information provided for complaint.");
        }

        complaintService.updateComplaint(existingComplaint);

        return ResponseEntity.ok(existingComplaint);

    }

    private ResponseEntity<Complaint> createErrorResponse(String message) {
        return ResponseEntity.badRequest().body(null); //
    }



}
