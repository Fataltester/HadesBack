package edu.eci.cvds.EciBienestarMod3.repository;

import edu.eci.cvds.EciBienestarMod3.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityMongoRepository extends MongoRepository<Activity, String> {
}
