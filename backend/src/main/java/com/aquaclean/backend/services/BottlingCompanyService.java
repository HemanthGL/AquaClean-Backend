package com.aquaclean.backend.services;

import com.aquaclean.backend.models.BottlingCompany;
import com.aquaclean.backend.models.Superior;
import com.aquaclean.backend.models.Supervisor;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BottlingCompanyService {

    // ======================= COMPANY =============================
    /**
     *  GET
     * @return List of All companies registered [for superAdmin]
     */
    public List<BottlingCompany> getAllCompanies();

    /**
     *  GET
     * @param companyId ID of the specific company
     * @return company object if present else null
     */
    public BottlingCompany getCompanyWithId(String companyId);

    /**
     *  POST
     * @param company Company Object to be saved to the DB
     * @return company Object which has been saved.
     */
    public BottlingCompany saveCompany(BottlingCompany company);

    /**
     *  PATCH
     * @param CompanyId ID of the company
     * @param company company object to be patched/updated
     * @return  updates the document, returns the updated else null
     */
    public ResponseEntity<BottlingCompany> updateBottlingCompany(String CompanyId, BottlingCompany company);

    // DELETE COMPANY ?

    // ======================== SUPERIORS ==============================
    /**
     *  GET
     * @param companyId ID of the company
     * @return List of all superiors at the company ID
     */
    public List<Superior> getAllSuperiors(String companyId);

    /**
     * POST
     * @param companyId CompanyID
     * @param newSuperior  new Superior to be added
     * @return Superior Object Added.
     */
    public Superior saveSuperior(String companyId, Superior newSuperior);

    /**
     * GET
     * @param companyId Company ID
     * @param superiorId Superior ID [Employee ID]
     * @return Superior object of associated ID, in companyID
     */
    public Superior getSuperiorById(String companyId, String superiorId);

    /**
     * PATCH
     * @param companyId company ID
     * @param superiorID Superior ID
     * @param newData new Superior Object to be replaced with
     * @return updates the superior, returns the updated else null
     */
    public ResponseEntity<Superior> updateSuperiorByIdInCompany(String companyId, String superiorID, Superior newData);

    /**
     * DELETE
     * @param companyId company ID
     * @param superiorId Superior's ID to be deleted
     * @return  boolean whether successfully deleted or not. [ exceptions ]
     */
    public boolean deleteSuperiorByIdInCompany(String companyId, String superiorId);

    // ========================= SUPERVISOR =======================

    /**
     * GET
     * @param companyId company's unique ID
     * @param superiorId Superior's Unique ID
     * @return  All Supervisors under the Superior in the Company
     */
    public List<Supervisor> getAllSupervisorsInCompanyUnderSuperior(String companyId, String superiorId);

    /**
     * POST
     * @param companyId company ID
     * @param superiorId superior's Unique ID
     * @param newSupervisor Supervisor Object to be saved in DB
     * @return  Saved Supervisor Object
     */
    public Supervisor saveSupervisorInCompanyUnderSuperior(String companyId, String superiorId, Supervisor newSupervisor);

    /**
     * GET
     * @param companyId Company Unique ID
     * @param superiorId Superior's Unique ID
     * @param supervisorId Supervisor's Unique ID
     * @return Supervisor Object with all details
     */
    public Supervisor getSupervisorByIdInCompanyUnderSuperior(String companyId, String superiorId, String supervisorId);

    /**
     * PATCH
     * @param companyId Company's Unique ID
     * @param superiorId Superior's Unique ID
     * @param supervisorId Supervisor's Unique ID
     * @param updatedSupervisor Updated Object to be sent to DB
     * @return Updated Obj if success else Null
     */
    public ResponseEntity<Supervisor> updateSupervisorByIdInCompanyUnderSuperior(String companyId, String superiorId, String supervisorId, Supervisor updatedSupervisor);

    /**
     * DELETE
     * @param companyId Company's Unique ID
     * @param superiorId Superior's Unique ID
     * @param supervisorId Supervisor's Unique ID
     * @return Boolean, true if success, else false
     */
    public boolean deleteSupervisorByIdInCompanyUnderSuperior(String companyId, String superiorId, String supervisorId);
}
