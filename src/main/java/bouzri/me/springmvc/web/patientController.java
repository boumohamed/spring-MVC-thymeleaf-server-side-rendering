package bouzri.me.springmvc.web;


import bouzri.me.springmvc.entities.Patient;
import bouzri.me.springmvc.entities.RendezVous;
import bouzri.me.springmvc.repositories.patientRepository;
import bouzri.me.springmvc.repositories.rendezVousRepository;
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
    private rendezVousRepository rvRepo;

    @GetMapping(path = "/user/patients")
    public String Patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "key", defaultValue = "") String key,
                           @RequestParam(name = "malade", defaultValue = "") String malade,
                           @RequestParam(name = "score", defaultValue = "0") int score
                          )
    {
        Page<Patient> pagePatient;
        if (malade.equals("malade"))
            pagePatient = patientRepo.findByNomContainsAndMaladeAndScoreGreaterThan(key, true, score, PageRequest.of(page,size));
        else if (malade.equals("sain"))
            pagePatient = patientRepo.findByNomContainsAndMaladeAndScoreGreaterThan(key, false, score, PageRequest.of(page,size));
        else
            pagePatient = patientRepo.findByNomContainsAndScoreGreaterThan(key, score, PageRequest.of(page,size));

        model.addAttribute("currentPage", page);
        model.addAttribute("key", key);
        model.addAttribute("malade", malade);
        model.addAttribute("score", score);
        model.addAttribute("patients", pagePatient.getContent());
        model.addAttribute("pages", new int[pagePatient.getTotalPages()]);

        return "patients";
        // UUID generate raondom
    }

    @GetMapping("/admin/PatientForm")
    public String PatientForm(Model model)
    {
        model.addAttribute("patient", new Patient());
        return "PatientForm";
    }

    @PostMapping("/admin/save")
    public String Save(Model model, @Valid Patient p, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String key
                       )
    {
        if (bindingResult.hasErrors()) return "PatientForm";
        patientRepo.save(p);
        return "redirect:/user/patients?page="+page+"&key="+key;
    }
    @GetMapping("/admin/update")
    public String Update(Model model, Long id, int page, String key)
    {
        Patient p = patientRepo.findById(id).orElse(null);
        if (p == null) return "400";

        model.addAttribute("patient", p);
        model.addAttribute("page", page);
        model.addAttribute("key", key);


        return "editPatient";
    }

    @GetMapping("/admin/details/patient")
    public String details(Model model, Long id, int page, String key, String malade, int score)
    {
        Patient p = patientRepo.findById(id).orElse(null);
        if (p == null) return "400";

        model.addAttribute("rendezvous", new RendezVous());
        model.addAttribute("patient", p);
        model.addAttribute("page", page);
        model.addAttribute("key", key);
        model.addAttribute("malade", malade);
        model.addAttribute("score", score);


        return "detailsPatient";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id, String key, int page, String malade, int score)
    {
        patientRepo.deleteById(id);
        return "redirect:/user/patients?page="+page+"&key="+key+"&malade="+malade+"&score="+score;
    }

    @GetMapping("/")
    public String home()
    {
        return "home";
    }

    @PostMapping("/admin/rendezvous/save")
    public String SaveRendzeVous(Model model, @Valid RendezVous v, BindingResult bindingResult,
                                @RequestParam() Long id,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam() String malade,
                                @RequestParam(defaultValue = "0") int score,
                                @RequestParam(defaultValue = "") String key

    )
    {
        if (bindingResult.hasErrors()) return "detailsPatient";
        Patient patient = patientRepo.findById(id).orElse(null);
        System.out.println(id);
        System.out.println(patient.getNom());
        v.setPatient(patient);
        v.setMedcin(null);
        v.setAnnuler(false);
        rvRepo.save(v);

        patient.getRendezVous().add(v);


        return "redirect:/user/patients?page="+page+"&key="+key+"&malade="+malade+"&score="+score;
    }
}
