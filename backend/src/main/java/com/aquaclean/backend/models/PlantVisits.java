package com.aquaclean.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantVisits {

    // plant quality check metrics
    // timestamp for plant visit

    private String supervisorId;


}
