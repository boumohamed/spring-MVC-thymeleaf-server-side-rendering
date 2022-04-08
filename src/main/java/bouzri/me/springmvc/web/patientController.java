package bouzri.me.springmvc.web;


import bouzri.me.springmvc.entities.Patient;
import bouzri.me.springmvc.repositories.patientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class patientController {


    private patientRepository patientRepo;

    @GetMapping(path = "/patients")
    public String Patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "key", defaultValue = "") String key
                          )
    {
        Page<Patient> pagePatient = patientRepo.findByNomContains(key, PageRequest.of(page,size));

        model.addAttribute("patients", pagePatient.getContent());
        model.addAttribute("pages", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("key", key);
        return "patients";
        // UUID generate raondom
    }

    @GetMapping("/delete")
    public String delete(Long id, String key, int page)
    {
        patientRepo.deleteById(id);
        return "redirect:/patients?page="+page+"&key="+key;
    }

    @GetMapping("/")
    public String home()
    {
        return "redirect:/patients";
    }


}
