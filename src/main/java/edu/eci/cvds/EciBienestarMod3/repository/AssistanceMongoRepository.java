package edu.eci.cvds.EciBienestarMod3.repository;

import edu.eci.cvds.EciBienestarMod3.model.Assistance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistanceMongoRepository extends MongoRepository<Assistance, String> {
    @Query("{ 'idActivity' : ?0 }")
    List<Assistance> findByIdActivity(String idActivity);

    @Query("{ 'userId' : ?0 }")
    List<Assistance> findByUserId(int userId);

    @Query("{ 'userName': { $regex: ?0, $options: 'i' } }")
    List<Assistance> findByUserNameContaining(String userName);

    @Query("{ 'userRol' : ?0 }")
    List<Assistance> findByUserRol(String userRol);

    @Query("{ 'idActivity' : ?0, 'confirmation' : ?1 }")
    List<Assistance> findByIdActivityAndConfirmation(String idActivity, boolean confirmation);

    @Query("{ 'userId' : ?0, 'idActivity' : ?1 }")
    Assistance findByUserIdAndIdActivity(int userId, String idActivity);
}
