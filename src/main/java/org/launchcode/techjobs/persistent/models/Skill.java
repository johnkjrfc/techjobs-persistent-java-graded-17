package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    @ManyToMany(mappedBy = "skills")
    private final List<Job> jobs = new ArrayList<>();

    @NotNull(message = "Description is required")
    @Size(message= "must be shorter than 150 characters", min = 1, max=150)
    private String description;



    public Skill(String description) {
        this.description = description;
    }
    public Skill () {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

}
