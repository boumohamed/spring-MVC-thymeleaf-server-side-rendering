package bouzri.me.springmvc;

import bouzri.me.springmvc.entities.Patient;
import bouzri.me.springmvc.repositories.patientRepository;
import bouzri.me.springmvc.security.entities.AppUser;
import bouzri.me.springmvc.security.repositories.AppUserRepository;
import bouzri.me.springmvc.security.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication

public class SpringMvcApplication implements CommandLineRunner {

    @Autowired
    private patientRepository pr;

    @Autowired
    private SecurityService securityService;

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*
        for (int i = 0; i < 5; i ++)
        {
            pr.save(
                    new Patient(null, "Mohamed"  , new Date(), Math.random() > 0.5, (int) (Math.random() * 10) + 10));
        }
        for (int i = 0; i < 10; i ++)
        {
            pr.save(
                    new Patient(null, "Amina"  , new Date(), Math.random() > 0.5, (int) (Math.random() * 10) + 10));
        }


        securityService.saveNewUser("user1", "bouzri", "bouzri");
        securityService.saveNewUser("mohamed", "bouzri", "bouzri");


        securityService.saveNewRole("USER", "utilisateur simple");
        securityService.saveNewRole("ADMIN", "Administrateur de l'application");


        securityService.addRoleToUser("mohamed", "ADMIN");
        securityService.addRoleToUser("mohamed", "USER");
        securityService.addRoleToUser("user1", "USER");

        securityService.saveNewUser("admin", "bouzri", "bouzri");

        securityService.addRoleToUser("admin", "ADMIN");
        securityService.addRoleToUser("admin", "USER");

         */




    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
