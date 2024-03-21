package project.CrimeRec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.CrimeRec.repository.ComplaintRepository;
import project.CrimeRec.service.MyService;

@Configuration
public class AppConfig {

    private final ComplaintRepository complaintRepository;

    public AppConfig(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Bean
    public MyService complaintService() {
        return new MyService(complaintRepository);
    }

}
