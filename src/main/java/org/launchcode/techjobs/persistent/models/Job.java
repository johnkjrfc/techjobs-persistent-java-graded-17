package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class Job extends AbstractEntity {



    @ManyToOne
    @NotNull(message = "Job must have employer")
    private Employer employer;

    @ManyToMany
    private List<Skill> skills;


    public Job() {
    }

    // Initialize the id and value fields.
    public Job(Employer employer, List<Skill> skills) {
        super();
        this.employer = employer;
        this.skills = skills;
    }

    // Getters and setters.
    


    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
