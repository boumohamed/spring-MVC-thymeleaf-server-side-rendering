package bouzri.me.springmvc.web;


import bouzri.me.springmvc.entities.Medcin;
import bouzri.me.springmvc.entities.Patient;
import bouzri.me.springmvc.repositories.medcinRepository;
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
import java.util.List;

@Controller
@AllArgsConstructor
public class medcinController {


    private medcinRepository medcinRepo;


    @GetMapping(path = "/user/medcins")
    public String Medcins(Model model)
    {
        List<Medcin> medcins = medcinRepo.findAll();
        model.addAttribute("medcins", medcins);
        return "medcins";
        // UUID generate raondom
    }
/*
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

    @GetMapping("/admin/delete")
    public String delete(Long id, String key, int page)
    {
        patientRepo.deleteById(id);
        return "redirect:/user/patients?page="+page+"&key="+key;
    }

    @GetMapping("/")
    public String home()
    {
        return "home";
    }

 */


}
