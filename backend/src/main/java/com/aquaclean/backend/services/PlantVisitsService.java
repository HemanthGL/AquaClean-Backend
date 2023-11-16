package com.aquaclean.backend.services;

import com.aquaclean.backend.models.PlantVisits;

import java.util.List;

public interface PlantVisitsService {

    /**
     * GET
     * @return List of All Plant Visits of all Plants
     */
    public List<PlantVisits> getAllPlantVisits();

    /**
     * GET
     * @param supervisorId Employee ID of the supervisor
     * @return All PlantVisits by specific Supervisor
     */
    public List<PlantVisits> getPlantVisitsOfSupervisor(String supervisorId);

    /**
     * GET
     * @param supervisorId Employee ID of the supervisor
     * @param plantVisitId Specific Plant ID
     * @return Specific PlantVisit by the specific Supervisor
     */
    public PlantVisits getSpecificPlantVisitOfSupervisor(String  supervisorId, String plantVisitId);

    /**
     * POST
     * @param visit Object of Plant Visit
     * @return PlantVisit saved into the DB
     */
    public PlantVisits savePlantVisit(PlantVisits visit);
}
