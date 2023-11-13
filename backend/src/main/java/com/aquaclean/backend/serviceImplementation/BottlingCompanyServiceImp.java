package com.aquaclean.backend.serviceImplementation;

import com.aquaclean.backend.models.BottlingCompany;
import com.aquaclean.backend.models.Superior;
import com.aquaclean.backend.models.Supervisor;
import com.aquaclean.backend.repository.BottlingCompanyRepository;
import com.aquaclean.backend.services.BottlingCompanyService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BottlingCompanyServiceImp implements BottlingCompanyService {

    @Autowired
    private BottlingCompanyRepository bCompanyRepo;

    @Override
    public List<BottlingCompany> getAllCompanies(){
        return bCompanyRepo.findAll();
    }

    @Override
    public BottlingCompany getCompanyWithId(String companyId){
        Optional<BottlingCompany> companyById = bCompanyRepo.findById(companyId);

        return companyById.orElse(null);
    }

    @Override
    public BottlingCompany saveCompany(BottlingCompany company){
        if (company == null){
            System.out.println("Company object is null");
            return null;
        }
        bCompanyRepo.save(company);
        System.out.println("Company object saved into DB");
        return company;
    }

    @Override
    public ResponseEntity<BottlingCompany> updateBottlingCompany(String companyId, BottlingCompany company){
        Optional<BottlingCompany> old = bCompanyRepo.findById(companyId);

        if (old.isPresent()){
            BottlingCompany updated = old.get();

            if (company.getCompanyId() != null)
                updated.setCompanyId(company.getCompanyId());

            if (company.getCompanyUserName() != null)
                updated.setCompanyUserName(company.getCompanyUserName());

            if (company.getPassword() != null)
                updated.setPassword(company.getPassword());

            if (company.getSuperiors() != null){
                List<Superior> newList = new ArrayList<Superior>();
                newList.addAll(updated.getSuperiors());
                newList.addAll(company.getSuperiors());

                updated.setSuperiors(newList);
            }

            bCompanyRepo.save(updated);
            return ResponseEntity.ok(updated);
        }
        return  null;
    }

    // ================================= SUPERIORS ======================
    @Override
    public List<Superior> getAllSuperiors(String companyId){
        Optional<BottlingCompany> obj = bCompanyRepo.findById(companyId);

        return obj.map(BottlingCompany::getSuperiors).orElse(null);

    }

    @Override
    public Superior saveSuperior(String companyId, Superior newSuperior){
        Optional<BottlingCompany> obj = bCompanyRepo.findById(companyId);

        if (obj.isPresent()){
            BottlingCompany saved = obj.get();

            if (newSuperior != null){
                List<Superior> updSuperiors = new ArrayList<>();

                if (saved.getSuperiors() != null)
                    updSuperiors.addAll(saved.getSuperiors());

                updSuperiors.add(newSuperior);

                saved.setSuperiors(updSuperiors);

                bCompanyRepo.save(saved);
                return newSuperior;
            }
        }
        return null;
    }

    @Override
    public Superior getSuperiorById(String companyId, String superiorId){
//        System.out.println("Entered");
        Optional<BottlingCompany> searchCom = bCompanyRepo.findById(companyId);

        if (searchCom.isPresent()){
            List<Superior> searchSup = searchCom.get().getSuperiors();

            if (searchSup != null){
                Superior findSup = searchSup.stream()
                        .filter(superior -> superiorId.equals(superior.getEmployeeId()))
                        .findAny()
                        .orElse(null);

                if (findSup == null) {
                    System.out.println("Superior not present in Company");
                    return null;
                }
                else
                    return findSup;
            }
            System.out.println("No Superiors present in Company");
            return  null;
        }
        System.out.println("Company Not Present");
        return null;
    }

    @Override
    public ResponseEntity<Superior> updateSuperiorByIdInCompany(String companyId, String superiorID, Superior newData){
        Optional<BottlingCompany> searchCompanyOptional = bCompanyRepo.findById(companyId);

        if (searchCompanyOptional.isPresent()){
            List<Superior> searchSup = searchCompanyOptional.get().getSuperiors();

            if (searchSup != null){
                Superior findSup = searchSup.stream()
                        .filter(superior -> superiorID.equals(superior.getEmployeeId()))
                        .findAny()
                        .orElse(null);

                if (findSup == null){
                    System.out.println("Superior Not Present in the Collection");
                } else {
                    if (newData.getSuperiorFullName() != null){
                        findSup.setSuperiorFullName((newData.getSuperiorFullName()));
                    }
                    if (newData.getPassword() != null)
                        findSup.setPassword(newData.getPassword());

                    if (newData.getEmployeeId() != null)
                        findSup.setEmployeeId(newData.getEmployeeId());

                    if (newData.getSupervisors() != null){
                        List<Supervisor> list = new ArrayList<>();

                        list.addAll(findSup.getSupervisors());
                        list.addAll(newData.getSupervisors());

                        findSup.setSupervisors(list);
                    }

                    searchSup.add(findSup);
                    BottlingCompany updatedData = searchCompanyOptional.get();
                    updatedData.setSuperiors(searchSup);
                    bCompanyRepo.save(updatedData);

                    return ResponseEntity.ok(findSup);
                }
                return null;
            }
            System.out.println("Superiors List is Empty");
            return null;
        }
        System.out.println("Company is not Present");
        return null;
    }

    @Override
    public boolean deleteSuperiorByIdInCompany(String companyId, String superiorId){

        Optional<BottlingCompany> searchBCompanyOptional = bCompanyRepo.findById(companyId);

        if (searchBCompanyOptional.isPresent()){
            List<Superior> superiorsInCompany = searchBCompanyOptional.get().getSuperiors();

            Superior deleteSup = superiorsInCompany.stream()
                    .filter(sup -> superiorId.equals(sup.getEmployeeId()))
                    .findAny()
                    .orElse(null);
            if (deleteSup == null){
                System.out.println("Superior not in company");
                return false;
            }

            superiorsInCompany.remove(deleteSup);
            BottlingCompany updatedCompany = searchBCompanyOptional.get();

            updatedCompany.setSuperiors(superiorsInCompany);

            bCompanyRepo.save(updatedCompany);

            System.out.println("Superior Deleted");
            return true;
        }
        System.out.println("Company not present in DB");
        return false;
    }

    // ========================================== SUPERVISOR ===================================

//    public List<Supervisor> getAllSupervisorsInCompanyUnderSuperior(String companyId, String superiorID){
//        Optional<BottlingCompany> searchBCompanyOptional = bCompanyRepo.findById(companyId);
//    }



}
