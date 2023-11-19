package com.aquaclean.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plantVisits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantVisits {

    // plant quality check metrics
    // timestamp for plant visit

    @Id
    private String plantVisitId;

    private String plantName;

    private String plantId;

    private String supervisorId;

    private String turbidity;

    private String temp;

    private String color;

    private String tds;

    private String electCond;

    private String pH;

    private String chloride;

    private String fluoride;

    private String hardness;

    private String dissolvedo2;


    public String getPlantVisitId() {
        return plantVisitId;
    }

    public void setPlantVisitId(String plantVisitId) {
        this.plantVisitId = plantVisitId;
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

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(String turbidity) {
        this.turbidity = turbidity;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTds() {
        return tds;
    }

    public void setTds(String tds) {
        this.tds = tds;
    }

    public String getElectCond() {
        return electCond;
    }

    public void setElectCond(String electCond) {
        this.electCond = electCond;
    }

    public String getpH() {
        return pH;
    }

    public void setpH(String pH) {
        this.pH = pH;
    }

    public String getChloride() {
        return chloride;
    }

    public void setChloride(String chloride) {
        this.chloride = chloride;
    }

    public String getFluoride() {
        return fluoride;
    }

    public void setFluoride(String fluoride) {
        this.fluoride = fluoride;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public String getDissolvedo2() {
        return dissolvedo2;
    }

    public void setDissolvedo2(String dissolvedo2) {
        this.dissolvedo2 = dissolvedo2;
    }
}
