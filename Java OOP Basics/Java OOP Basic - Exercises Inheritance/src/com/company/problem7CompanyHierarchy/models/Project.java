package com.company.problem7CompanyHierarchy.models;

import java.util.Calendar;

public class Project {

    private String projectName;
    private Calendar projectStartDate;
    private String details;
    private State state;

    public Project(String projectName, Calendar projectStartDate, String details, State state) {
        this.setProjectName(projectName);
        this.setProjectStartDate(projectStartDate);
        this.setDetails(details);
        this.setState(state);
    }

    public String getProjectName() {
        return projectName;
    }

    private void setProjectName(String projectName) {
        if (projectName == null || projectName.length() < 3) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.projectName = projectName;
    }

    public Calendar getProjectStartDate() {
        return projectStartDate;
    }

    private void setProjectStartDate(Calendar projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getDetails() {
        return details;
    }

    private void setDetails(String details) {
        if (details == null || details.length() < 3) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.details = details;
    }

    public State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public void closeProject() {
        if (state.getState().equals("open")) {
            state.setState("close");
        }
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", projectStartDate=" + projectStartDate +
                ", details='" + details + '\'' +
                ", state=" + state +
                '}';
    }
}
