package edu.eci.cvds.EciBienestarMod3.repository;

import edu.eci.cvds.EciBienestarMod3.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for managing Activity documents in MongoDB.
 */
@Repository
public interface ActivityMongoRepository extends MongoRepository<Activity, String> {

    /**
     * Finds an activity by type, year, and semester.
     */
    @Query("{ 'activityType' : ?0, 'year' : ?1, 'semester' : ?2 }")
    Activity getActivityByGeneralSchedules(String activityType, int year, int semester);

    /**
     * Finds activities filtered by year, semester, teacher name (partial match), and activity type (partial match).
     */
    @Query("{'year': ?0, 'semester': ?1, 'teacher': { $regex: ?2, $options:  'i' }, 'activityType': { $regex: ?3, $options:  'i' } }")
    List<Activity> findActivityByOptions(int year, int semester, String teacher, String activityType);
}
