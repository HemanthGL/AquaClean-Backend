package com.aquaclean.backend.models;

public class Supervisor {

    private String supervisorFullName;

    private String password;

    private String employeeId;

    private String plantName;

    private String plantId;

    private String emailId;

    // plant metrics
    // private List<PlantMetrics> metrics/visitData  ==> Updated to move to separate Collection

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSupervisorFullName() {
        return supervisorFullName;
    }

    public void setSupervisorFullName(String supervisorFullName) {
        this.supervisorFullName = supervisorFullName;
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

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

}
