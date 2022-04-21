package bouzri.me.springmvc;

import bouzri.me.springmvc.entities.Patient;
import bouzri.me.springmvc.repositories.patientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringMvcApplication implements CommandLineRunner {

    @Autowired
    private patientRepository pr;

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*for (int i = 0; i < 10; i ++)
        {
            pr.save(
                    new Patient(null, "Amina"  , new Date(), Math.random() > 0.5, (int) (Math.random() * 10)));
        }*/
    }
}
