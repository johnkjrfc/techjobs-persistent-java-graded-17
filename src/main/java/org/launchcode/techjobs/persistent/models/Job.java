package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Job extends AbstractEntity {



    @ManyToOne
    @NotNull
    private Employer employer;

    private String skills;


    public Job() {
    }

    // Initialize the id and value fields.
    public Job(Employer employer, String skills) {
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

    public String getSkills() {
        return skills;
    }


    public void setSkills(String skills) {
        this.skills = skills;
    }

}
