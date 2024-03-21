package project.CrimeRec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.CrimeRec.model.Inmate;


@RestController
public class InmateAPIController {

    private final project.CrimeRec.service.inmateService inmateService;

    @Autowired
    public InmateAPIController(project.CrimeRec.service.inmateService inmateService) {
        this.inmateService = inmateService;
    }

    @PostMapping("/inmates/add")
    public ResponseEntity<String> addInmate(@RequestBody Inmate inmate) {
        inmateService.addInmate(inmate);
        return ResponseEntity.ok("Inmate added successfully");
    }

    @DeleteMapping("/inmates/delete/{indent}")
    public ResponseEntity<String> deleteComplaint(
            @PathVariable Long indent) {
        if (indent!=null){
            inmateService.deleteInmateById(indent);
        } else {
            return  ResponseEntity.badRequest().body("Please specify which inmate to delete.");
        }
        return ResponseEntity.ok("Complaint or complaints deleted successfully");

    }
}
