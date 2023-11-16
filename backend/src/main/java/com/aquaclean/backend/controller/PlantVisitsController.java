package com.aquaclean.backend.controller;

import com.aquaclean.backend.models.PlantVisits;
import com.aquaclean.backend.services.PlantVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/")
public class PlantVisitsController {

    @Autowired
    private PlantVisitsService service;

    @GetMapping("/allVisits/")
    public List<PlantVisits>  getAllPlantVisits(){
        return service.getAllPlantVisits();
    }

    @GetMapping("/supervisor/{supervisor_id}")
    public List<PlantVisits> getPlantVisitsOfSupervisor(@PathVariable String supervisor_id){
        return service.getPlantVisitsOfSupervisor(supervisor_id);
    }

    @PostMapping("/plantVisit/")
    public PlantVisits savePlantVisit(@RequestBody PlantVisits visit){
        return service.savePlantVisit(visit);
    }

    @GetMapping("/supervisor/{supervisor_id}/visit/{plant_visit_id}")
    public PlantVisits getSpecificPlantVisitOfSupervisor(@PathVariable String supervisor_id, @PathVariable String plant_visit_id){
        return service.getSpecificPlantVisitOfSupervisor(supervisor_id, plant_visit_id);
    }
}
