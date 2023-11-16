package com.aquaclean.backend.repository;

import com.aquaclean.backend.models.PlantVisits;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantVisitsRepository extends MongoRepository<PlantVisits, String> {

    @Query("{ supervisorId: ?0 }")
    List<PlantVisits> getPlantVisitsBySupervisorId(String supervisorId);
}
