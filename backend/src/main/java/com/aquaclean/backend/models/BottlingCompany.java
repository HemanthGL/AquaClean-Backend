package com.aquaclean.backend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BottlingCompany {

    @Id
    private String companyId;
    // make companyUserName as PK ?
    private String companyUserName;

    private String password;

    private List<Superior> superiors;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyUserName() {
        return companyUserName;
    }

    public void setCompanyUserName(String companyUserName) {
        this.companyUserName = companyUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Superior> getSuperiors() {
        return superiors;
    }

    public void setSuperiors(List<Superior> superiors) {
        this.superiors = superiors;
    }
}
