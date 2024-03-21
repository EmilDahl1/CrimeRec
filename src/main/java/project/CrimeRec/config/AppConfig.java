package project.CrimeRec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.CrimeRec.repository.ComplaintRepository;
import project.CrimeRec.service.complaintService;

@Configuration
public class AppConfig {

    private final ComplaintRepository complaintRepository;

    public AppConfig(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Bean
    public complaintService complaintService() {
        return new complaintService(complaintRepository);
    }

}
