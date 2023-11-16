package com.aquaclean.backend.serviceImplementation;

import com.aquaclean.backend.models.PlantVisits;
import com.aquaclean.backend.repository.PlantVisitsRepository;
import com.aquaclean.backend.services.PlantVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PlantVisitsServiceImp implements PlantVisitsService {

    @Autowired
    private PlantVisitsRepository pVisitsRepo;

    @Override
    public List<PlantVisits> getAllPlantVisits(){
        return pVisitsRepo.findAll();
    }

    @Override
    public List<PlantVisits> getPlantVisitsOfSupervisor(String supervisorId){
        Optional<List<PlantVisits>> searchPlantVisits = Optional.ofNullable(pVisitsRepo.getPlantVisitsBySupervisorId(supervisorId));

        if (searchPlantVisits.isPresent()){
            System.out.println(searchPlantVisits.get().size());

            return searchPlantVisits.get();
        }

        return null;
    }

    @Override
    public PlantVisits savePlantVisit(PlantVisits visit){
        if (visit == null){
            System.out.println("visit Object is null");
            return null;
        }
        pVisitsRepo.save(visit);

        System.out.println("visit saved to DB");
        return visit;
    }

    @Override
    public PlantVisits getSpecificPlantVisitOfSupervisor(String supervisorId, String plantVisitId){
        Optional<List<PlantVisits>> visits = Optional.ofNullable(pVisitsRepo.getPlantVisitsBySupervisorId(supervisorId));

        if (visits.isPresent()){
            PlantVisits specificVisit = visits.get().stream()
                    .filter(visit ->  visit.getPlantVisitId().equals(plantVisitId))
                    .findAny()
                    .orElse(null);
            if (specificVisit != null){
                System.out.println("specific visit to plant by supervisor Found");
                return specificVisit;
            }
            System.out.println("No such visit to plant by Supervisor");
            return null;
        }
        System.out.println("No plant visits at all by Supervisor.");
        return null;
    }
}
