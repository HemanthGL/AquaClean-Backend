package com.aquaclean.backend.repository;

import com.aquaclean.backend.models.PlantVisits;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PlantVisitsRepository {

    @Query("{ supervisorId: ?0 }")
    List<PlantVisits> getPlantVisitsBySupervisorId(String supervisorId);
}
