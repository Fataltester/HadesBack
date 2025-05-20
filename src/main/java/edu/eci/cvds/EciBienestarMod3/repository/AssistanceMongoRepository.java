package edu.eci.cvds.EciBienestarMod3.repository;

import edu.eci.cvds.EciBienestarMod3.model.Assistance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for performing CRUD operations and custom queries on Assistance documents in MongoDB.
 */
@Repository
public interface AssistanceMongoRepository extends MongoRepository<Assistance, String> {

    /**
     * Finds assistance records by schedule ID.
     */
    @Query("{ 'idSchedule' : ?0 }")
    List<Assistance> findByIdSchedule(String idSchedule);

    /**
     * Finds all assistance records associated with a specific user ID.
     */
    @Query("{ 'userId' : ?0 }")
    List<Assistance> findByUserId(int userId);

    /**
     * Finds assistance records where the user's name contains the given substring (case-insensitive).
     */
    @Query("{ 'userName': { $regex: ?0, $options: 'i' } }")
    List<Assistance> findByUserNameContaining(String userName);

    /**
     * Finds assistance records by user role.
     */
    @Query("{ 'userRol' : ?0 }")
    List<Assistance> findByUserRol(String userRol);

    /**
     * Finds assistance records by schedule ID and confirmation status.
     */
    @Query("{ 'idSchedule' : ?0, 'confirmation' : ?1 }")
    List<Assistance> findByIdScheduleAndConfirmation(String idSchedule, boolean confirmation);

    /**
     * Finds a single assistance record by user ID and schedule ID.
     */
    @Query("{ 'userId' : ?0, 'idSchedule' : ?1 }")
    Assistance findByUserIdAndIdSchedule(int userId, String idSchedule);

    /**
     * Finds all assistance records that belong to any of the given schedule IDs.
     */
    @Query("{ 'idSchedule': { $in: ?0 } }")
    List<Assistance> findByScheduleIds(List<String> scheduleIds);

    /**
     * Finds a specific assistance record by user name, role, and schedule ID.
     */
    @Query("{ 'userName' : ?0, 'userRol' : ?1, 'idSchedule' : ?2 }")
    Assistance getAssistanceByGeneralSchedules(String userName, String userRol, String idSchedule);

    /**
     * Searches assistance records using a combination of optional parameters.
     * Performs partial matches for user name and role; exact matches for user ID and confirmation.
     */
    @Query("{ " +
            "'userId': { $gte: ?0 }, " +
            "'userName': { $regex: ?1, $options: 'i' }, " +
            "'userRol': { $regex: ?2, $options: 'i' }, " +
            "'confirmation': ?3 " +
            "}")
    List<Assistance> findAssistanceByOptions(int userId, String userName, String userRol, Boolean confirmation);

    /**
     * Finds a single assistance record by user ID.
     */
    Assistance findAssistanceByUserId(int userId);

    /**
     * Retrieves an assistance record by MongoDB ID.
     */
    Assistance getAssistanceById(String id);

    /**
     * Retrieves an assistance record by user ID (duplicate of above).
     */
    Assistance getAssistanceByUserId(int userId);

    /**
     * Retrieves an assistance record by user name.
     */
    Assistance getAssistanceByUserName(String userName);
}
