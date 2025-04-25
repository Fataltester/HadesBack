package edu.eci.cvds.EciBienestarMod3.repository;

import edu.eci.cvds.EciBienestarMod3.model.Assistance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistanceMongoRepository extends MongoRepository<Assistance, String> {
}
