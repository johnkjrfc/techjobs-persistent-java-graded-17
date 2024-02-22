package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired//links controller to repo
    private EmployerRepository employerRepository;
    @GetMapping("/")
    public String index (Model model) {
        //add all employers in empRepo
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }
    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        //add new Emp() to model so we can add data from the view
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            //if errors, we save the UI on the form, but redirect back to add
            model.addAttribute(new Employer());
            return "employers/add";
        }
        //Employer valid, save to empRepo
        employerRepository.save(newEmployer);
        return "redirect:";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {
        //get a single employer from repo, as long as it is in the repo
        Optional <Employer> optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }

    }
}
