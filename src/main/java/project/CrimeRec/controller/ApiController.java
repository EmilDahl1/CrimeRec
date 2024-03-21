package project.CrimeRec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.CrimeRec.model.Complaint;
import project.CrimeRec.service.MyService;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {

    private final MyService myService;

    @Autowired
    public ApiController(MyService myService) {
        this.myService = myService;
    }

    @PostMapping("/complaints/add")
    public ResponseEntity<String> addComplaint(@RequestBody Complaint complaint) {
        myService.addComplaint(complaint);
        return ResponseEntity.ok("Complaint added successfully");
    }



    @GetMapping("/complaints/getAll")
    public ResponseEntity<List<Complaint>> getComplaints() {
        List<Complaint> complaints = myService.getAllComplaints();
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
            @RequestParam(value = "complaintCreator", required = false) String complaintCreator) {

        if (complaintid!=null){
            myService.deleteComplaintById(complaintid);
        } else if (complaintReceiver!=null) {
            myService.deleteComplaintByReceiver(complaintReceiver);
        } else if (complaintCreator!=null) {
            myService.deleteByComplaintCreator(complaintCreator);
        } else {
            return  ResponseEntity.badRequest().body("Please specify which complaint or complaints to delete.");
        }
        return ResponseEntity.ok("Complaint or complaints deleted successfully");

    }


    @GetMapping("/complaints/getById/{complaintid}")
    public ResponseEntity<Complaint> getComplaint( @PathVariable Long complaintid) {
        Complaint complaint = myService.getComplaintById(complaintid);
        return ResponseEntity.ok(complaint);
    }


    @PutMapping("/complaints/updateComplaint/{complaintid}")
    public ResponseEntity<Complaint> updateComplaint(
            @PathVariable Long complaintid,
            @RequestBody Complaint updatedComplaint,
            @RequestParam(name = "complaintReceiver", required = false) Optional<String> optionalComplaintReceiver,
            @RequestParam(name = "complaintCreator", required = false) Optional<String> optionalComplaintCreator,
            @RequestParam(name = "complaintText", required = false) Optional<String> optionalComplaintText
    ) {
        Complaint existingComplaint = myService.getComplaintById(complaintid);
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

        myService.updateComplaint(existingComplaint);

        return ResponseEntity.ok(existingComplaint);

    }

    private ResponseEntity<Complaint> createErrorResponse(String message) {
        return ResponseEntity.badRequest().body(null); //
    }



}
