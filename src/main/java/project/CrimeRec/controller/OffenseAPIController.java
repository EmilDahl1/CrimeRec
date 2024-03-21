package project.CrimeRec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.CrimeRec.model.Offense;
import project.CrimeRec.service.complaintService;

@RestController
public class OffenseAPIController {

    private final project.CrimeRec.service.offenseService offenseService;


    @Autowired
    public OffenseAPIController(project.CrimeRec.service.offenseService offenseService) {
        this.offenseService = offenseService;
    }

    @PostMapping("/offenses/add")
    public ResponseEntity<String> addOffense(@RequestBody Offense offense) {
        offenseService.addOffense(offense);
        return ResponseEntity.ok("Offense added successfully");
    }
}
