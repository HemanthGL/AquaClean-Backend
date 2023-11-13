package com.aquaclean.backend.models;

import java.util.List;

public class Superior {

    private String superiorFullName;

    private String password;

    private String employeeId;

    List<Supervisor> supervisors;

    public String getSuperiorFullName() {
        return superiorFullName;
    }

    public void setSuperiorFullName(String superiorFullName) {
        this.superiorFullName = superiorFullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<Supervisor> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(List<Supervisor> supervisors) {
        this.supervisors = supervisors;
    }
}
