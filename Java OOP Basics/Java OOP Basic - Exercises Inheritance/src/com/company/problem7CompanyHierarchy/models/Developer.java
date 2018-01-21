package com.company.problem7CompanyHierarchy.models;

import java.util.HashSet;
import java.util.Set;

public class Developer extends RegularEmployee {

    private Set<Project> projects;

    public Developer(int id, String firstName, String lastName, double salary, Department department) {
        super(id, firstName, lastName, salary, department);
        this.projects = new HashSet<>();
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void closeProject(Project project) {
        project.closeProject();
    }

    @Override
    public String toString() {
        return "Developer{" +
                "projects=" + projects +
                '}';
    }
}
