package com.aquaclean.backend.serviceImplementation;

import com.aquaclean.backend.models.BottlingCompany;
import com.aquaclean.backend.models.Superior;
import com.aquaclean.backend.models.Supervisor;
import com.aquaclean.backend.repository.BottlingCompanyRepository;
import com.aquaclean.backend.services.BottlingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public List<Supervisor> getAllSupervisorsInCompanyUnderSuperior(String companyId, String superiorID){
        Optional<BottlingCompany> searchBCompanyOptional = bCompanyRepo.findById(companyId);

        if (searchBCompanyOptional.isPresent()){
            List<Superior> superiorsList = searchBCompanyOptional.get().getSuperiors();

            if (superiorsList != null){
                Superior findSup = superiorsList.stream()
                        .filter(superior -> superiorID.equals(superior.getEmployeeId()))
                        .findAny()
                        .orElse(null);

                if (findSup == null) {
                    System.out.println("Superior not present in Company");
                    return null;
                }
                else
                    return findSup.getSupervisors();
            }
            System.out.println("No Superiors present in Company");
            return  null;
        }
        System.out.println("Company Not Present");
        return null;
    }

    public Supervisor saveSupervisorInCompanyUnderSuperior(String companyId, String superiorId, Supervisor newSupervisor){
        Optional<BottlingCompany> searchBCompanyOptional = bCompanyRepo.findById(companyId);

        if (searchBCompanyOptional.isPresent()){
            List<Superior> superiorsList = searchBCompanyOptional.get().getSuperiors();

            if (superiorsList != null){
                Superior findSup = superiorsList.stream()
                        .filter(superior -> superiorId.equals(superior.getEmployeeId()))
                        .findAny()
                        .orElse(null);

                if (findSup == null) {
                    System.out.println("Superior not present in Company");
                    return null;
                }
                else {
                    List<Supervisor> list = new ArrayList<>();

                    if (findSup.getSupervisors() != null)
                        list.addAll(findSup.getSupervisors());
                    list.add(newSupervisor);

                    findSup.setSupervisors(list);

                    BottlingCompany updatedComp = searchBCompanyOptional.get();
                    bCompanyRepo.save(updatedComp);
                    return newSupervisor;
                }
            }
            System.out.println("No Superiors present in Company");
            return  null;
        }
        System.out.println("Company Not Present");
        return null;
    }

    public Supervisor getSupervisorByIdInCompanyUnderSuperior(String companyId, String superiorId, String supervisorId){
        Optional<BottlingCompany> searchBCompanyOptional = bCompanyRepo.findById(companyId);

        if (searchBCompanyOptional.isPresent()){
            List<Superior> superiorsList = searchBCompanyOptional.get().getSuperiors();

            if (superiorsList != null){
                Superior findSup = superiorsList.stream()
                        .filter(superior -> superiorId.equals(superior.getEmployeeId()))
                        .findAny()
                        .orElse(null);

                if (findSup == null) {
                    System.out.println("Superior not present in Company");
                    return null;
                }
                else {
                    List<Supervisor> supervisorsList = findSup.getSupervisors();

                    if (supervisorsList != null){
                        Supervisor findSupervisor = supervisorsList.stream()
                                .filter(supervisor -> supervisorId.equals(supervisor.getEmployeeId()))
                                .findAny()
                                .orElse(null);

                        if (findSupervisor == null){
                            System.out.println("Supervisor not found among supervisors Available under Superior");
                            return null;
                        }
                        else {
                            return findSupervisor;
                        }

                    }
                    System.out.println("No Supervisors Available under Superior");
                    return null;
                }
            }
            System.out.println("No Superiors present in Company");
            return  null;
        }
        System.out.println("Company Not Present");
        return null;
    }

    public ResponseEntity<Supervisor> updateSupervisorByIdInCompanyUnderSuperior(String companyId, String superiorId, String supervisorId, Supervisor updatedSupervisor){
        Optional<BottlingCompany> searchCompanyOptional = bCompanyRepo.findById(companyId);

        if (searchCompanyOptional.isPresent()){
            List<Superior> searchSup = searchCompanyOptional.get().getSuperiors();

            if (searchSup != null){
                Superior findSup = searchSup.stream()
                        .filter(superior -> superiorId.equals(superior.getEmployeeId()))
                        .findAny()
                        .orElse(null);

                if (findSup == null){
                    System.out.println("Superior Not Present in the Collection");
                    return null;
                } else {
                    List<Supervisor> searchSupervisor = findSup.getSupervisors();

                    if (searchSupervisor == null){
                        System.out.println("Supervisors List Empty under given Superior");
                        return null;
                    } else {
                        Supervisor findSupervisor = searchSupervisor.stream()
                                .filter(supervisor -> supervisor.getEmployeeId().equals(updatedSupervisor.getEmployeeId()))
                                .findAny()
                                .orElse(null);

                        if (findSupervisor ==  null) {
                            System.out.println("Cannot Find Supervisor under the given Superior");
                            return null;
                        } else {
                            if (updatedSupervisor.getSupervisorFullName() != null)
                                findSupervisor.setSupervisorFullName(updatedSupervisor.getSupervisorFullName());
                            if (updatedSupervisor.getPassword() != null)
                                findSupervisor.setPassword(updatedSupervisor.getPassword());
                            if (updatedSupervisor.getPlantId() != null)
                                findSupervisor.setPlantId(updatedSupervisor.getPlantId());
                            if (updatedSupervisor.getPlantName() != null)
                                findSupervisor.setPlantName(updatedSupervisor.getPlantName());

                            List<Supervisor> list = new ArrayList<>();
                            list.addAll(searchSupervisor);
                            list.add(findSupervisor);

                            findSup.setSupervisors(list);

                            List<Superior> listSuperior = new ArrayList<>();
                            listSuperior.addAll(searchSup);
                            listSuperior.add(findSup);

                            BottlingCompany updatedCompany = searchCompanyOptional.get();

                            updatedCompany.setSuperiors(listSuperior);
                            bCompanyRepo.delete(searchCompanyOptional.get());
                            bCompanyRepo.save(updatedCompany);
                            return ResponseEntity.ok(updatedSupervisor);

                        }
                    }
                }
            }
            System.out.println("Superiors List is Empty");
            return null;
        }
        System.out.println("Company is not Present");
        return null;
    }

    public boolean deleteSupervisorByIdInCompanyUnderSuperior(String companyId, String superiorId, String supervisorId){
        Optional<BottlingCompany> searchBCompanyOptional = bCompanyRepo.findById(companyId);
        System.out.println(companyId);
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
            else {
                List<Supervisor> supervisorsInCompanyUnderSuperior = deleteSup.getSupervisors();

                Supervisor deleteSupervisor = supervisorsInCompanyUnderSuperior.stream()
                        .filter(supervisor -> supervisor.getEmployeeId().equals(supervisorId))
                        .findAny()
                        .orElse(null);

                if (deleteSupervisor == null) {
                    System.out.println("No Such Supervisor under Superior in Company");
                    return false;
                } else {
                    supervisorsInCompanyUnderSuperior.remove(deleteSupervisor);
                    Superior updatedSuperior = deleteSup;

                    superiorsInCompany.remove(deleteSup);
                    updatedSuperior.setSupervisors(supervisorsInCompanyUnderSuperior);
                    superiorsInCompany.add(updatedSuperior);

                    BottlingCompany updatedCompany = searchBCompanyOptional.get();
                    updatedCompany.setSuperiors(superiorsInCompany);

                    bCompanyRepo.save(updatedCompany);
                    System.out.println("Supervisor Deleted Succesfully");
                    return true;
                }
            }
        }
        System.out.println("Company not present in DB");
        return false;
    }

}
