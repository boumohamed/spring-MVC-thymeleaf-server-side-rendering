package bouzri.me.springmvc.web;


import bouzri.me.springmvc.entities.Patient;
import bouzri.me.springmvc.repositories.patientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

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
    }

    @GetMapping("/PatientForm")
    public String PatientForm(Model model)
    {
        model.addAttribute("patient", new Patient());
        return "PatientForm";
    }

    @PostMapping("/save")
    public String Save(Model model, @Valid Patient p, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String key)
    {
        if (bindingResult.hasErrors()) return "PatientForm";
        patientRepo.save(p);
        return "redirect:/patients?page="+page+"&key="+key;
    }
    @GetMapping("/update")
    public String Update(Model model, Long id, int page, String key)
    {
        Patient p = patientRepo.findById(id).orElse(null);
        if (p == null) throw new RuntimeException("No such Patient");

        model.addAttribute("patient", p);
        model.addAttribute("page", page);
        model.addAttribute("key", key);

        return "editPatient";
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
