package com.nassar.usermanagement.model;

import jakarta.validation.constraints.Pattern;

import java.util.List;

public class UserRequest {

    @Pattern(regexp = "^[A-Za-z]{3,10}$", message = "Name can contain characters only")
    private String name;
    private List<String> skills;

    public UserRequest(String name, List<String> skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
