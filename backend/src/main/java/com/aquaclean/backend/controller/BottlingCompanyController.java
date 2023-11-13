package com.aquaclean.backend.controller;

import com.aquaclean.backend.models.BottlingCompany;
import com.aquaclean.backend.models.Superior;
import com.aquaclean.backend.models.Supervisor;
import com.aquaclean.backend.services.BottlingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
//http://localhost:4200/logIn
@RequestMapping("/")
public class BottlingCompanyController {

    @Autowired
    private BottlingCompanyService service;

    @GetMapping("/v1/company/")
    public List<BottlingCompany> getAllCompanies(){
        return service.getAllCompanies();
    }

    @GetMapping("/v1/company/{company_id}")
    public BottlingCompany getCompanyWithId(@PathVariable String company_id){
        return service.getCompanyWithId(company_id);
    }

    @PostMapping("/v1/company/")
    public String saveCompany(@RequestBody BottlingCompany company){
        service.saveCompany(company);
        return "saved";
    }

    @PatchMapping("/v1/company/{company_id}")
    public ResponseEntity<BottlingCompany> updateBottlingCompany(@PathVariable String company_id, @RequestBody BottlingCompany company){
        return service.updateBottlingCompany(company_id, company);
    }

    @GetMapping("/v1/company/{company_id}/superior/")
    public List<Superior> getAllSuperiors(@PathVariable String company_id){
        return service.getAllSuperiors(company_id);
    }

    @PostMapping("/v1/company/{company_id}/superior/")
    public Superior saveSuperior(@PathVariable String company_id, @RequestBody Superior newSuperior){
        return service.saveSuperior(company_id, newSuperior);
    }

    @GetMapping("/v1/company/{company_id}/superior/{superior_id}")
    public Superior getSuperiorById(@PathVariable String company_id, @PathVariable String superior_id){
//        System.out.println(company_id);
//        System.out.println(superior_id);
        return service.getSuperiorById(company_id, superior_id);
    }

    @PatchMapping("/v1/company/{company_id}/superior/{superior_id}")
    public ResponseEntity<Superior> updateSuperiorByIdInCompany(@PathVariable String company_id,@PathVariable String superior_id, @RequestBody Superior newData){
        return service.updateSuperiorByIdInCompany(company_id, superior_id, newData);
    }

    @DeleteMapping("/v1/company/{company_id}/superior/{superior_id}")
    public boolean deleteSuperiorByIdInCompany(@PathVariable String company_id, @PathVariable String superior_id){
        return service.deleteSuperiorByIdInCompany(company_id, superior_id);
    }

    // ================================================== SUPERVISORS =====================================================

    @GetMapping("/v1/company/{company_id}/superior/{superior_id}/supervisor")
    public List<Supervisor> getAllSupervisorsInCompanyUnderSuperior(@PathVariable String company_id, @PathVariable String superior_id){
        return service.getAllSupervisorsInCompanyUnderSuperior(company_id, superior_id);
    }

    @PostMapping("/v1/company/{company_id}/superior/{superior_id}/supervisor")
    public Supervisor saveSupervisorInCompanyUnderSuperior(@PathVariable String company_id, @PathVariable String superior_id, @RequestBody Supervisor newSupervisor){
        return service.saveSupervisorInCompanyUnderSuperior(company_id, superior_id, newSupervisor);
    }

    @GetMapping("/v1/company/{company_id}/superior/{superior_id}/supervisor/{supervisor_id}")
    public Supervisor getSupervisorByIdInCompanyUnderSuperior(@PathVariable String company_id, @PathVariable String superior_id, @PathVariable String supervisor_id){
        return service.getSupervisorByIdInCompanyUnderSuperior(company_id, superior_id, supervisor_id);
    }

    @PatchMapping("/v1/company/{company_id}/superior/{superior_id}/supervisor/{supervisor_id}")
    public ResponseEntity<Supervisor> updateSupervisorByIdInCompanyUnderSuperior(@PathVariable String company_id, @PathVariable String superior_id, @PathVariable String supervisor_id, @RequestBody Supervisor updatedSupervisor){
        return service.updateSupervisorByIdInCompanyUnderSuperior(company_id, superior_id, supervisor_id, updatedSupervisor);
    }

    @DeleteMapping("/v1/company/{company_id}/superior/{superior_id}/supervisor/{supervisor_id}")
    public boolean deleteSupervisorByIdInCompanyUnderSuperior(@PathVariable String company_id, @PathVariable String superior_id, @PathVariable String supervisor_id){
        return service.deleteSupervisorByIdInCompanyUnderSuperior(company_id, superior_id, supervisor_id);
    }
}
