package com.aquaclean.backend.repository;

import com.aquaclean.backend.models.BottlingCompany;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BottlingCompanyRepository extends MongoRepository<BottlingCompany, String> {

//    @Query("{companyId: ?0")
//    List<BottlingCompany> getCompanyById(String companyId);

}
