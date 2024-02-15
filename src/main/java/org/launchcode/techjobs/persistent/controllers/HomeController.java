package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
    @Autowired//links controller to repo
    private EmployerRepository employerRepository;
    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("jobs", jobRepository.findAll());

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {

        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam(required = false) Integer employerId) {

        if (errors.hasErrors()) {
	        model.addAttribute(new Job());

            return "add";
        }
        if (employerId != null){
            //empId present--> create optEmp, and try to retrieve existing emp from repo based on req param
            Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
            if (optionalEmployer.isPresent()) {
                //if employerId exists in repo, get it and use in newJob
                Employer employer = optionalEmployer.get();
                newJob.setEmployer(employer);
            } else {
                //employerId on form, not found in repo
                model.addAttribute("error", "Invalid employer ID");
                return "add";
            }
        } else {
            //no employerId provided in form
            model.addAttribute("error", "Employer ID required");
            return "add";
        }


        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}
