package edu.eci.cvds.EciBienestarMod3.service;

import edu.eci.cvds.EciBienestarMod3.dto.AssistanceRequest;
import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.repository.AssistanceMongoRepository;
import edu.eci.cvds.EciBienestarMod3.repository.ScheduleMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.eci.cvds.EciBienestarMod3.model.Assistance;

import java.util.List;
import java.util.ArrayList;

/**
 * Service class for managing Assistance data, including creation, retrieval,
 * update and deletion of user assistance records.
 */
@Service
public class AssistanceService {

    @Autowired
    private AssistanceMongoRepository assistanceRepo;

    @Autowired
    private ScheduleMongoRepository scheduleRepo;

    /**
     * Creates a new assistance record for a given schedule.
     *
     * @param schedule   The schedule ID to associate with the assistance.
     * @param assistance The request containing user and confirmation data.
     * @return The saved Assistance object.
     */
    public Assistance createAssistance(String schedule, AssistanceRequest assistance) {
        Assistance assistance1 = new Assistance();
        assistance1.setIdSchedule(schedule);
        assistance1.setUserId(assistance.getIdUser());
        assistance1.setUserName(assistance.getUserName());
        assistance1.setUserRol(assistance.getRolUser());
        assistance1.setConfirmation(assistance.getConfirmation());
        return assistanceRepo.save(assistance1);
    }

    /**
     * Creates an assistance record for a student with confirmation set to false.
     *
     * @param schedule   The schedule ID to associate with the assistance.
     * @param assistance The request containing student details.
     * @return The saved Assistance object with confirmation set to false.
     */
    public Assistance studentCreateAssistance(String schedule, AssistanceRequest assistance) {
        Assistance assistance1 = new Assistance();
        assistance1.setIdSchedule(schedule);
        assistance1.setUserId(assistance.getIdUser());
        assistance1.setUserName(assistance.getUserName());
        assistance1.setUserRol(assistance.getRolUser());
        assistance1.setConfirmation(false);
        return assistanceRepo.save(assistance1);
    }

    /**
     * Retrieves assistance records based on optional filter criteria such as
     * user ID, user name, role, and confirmation status.
     *
     * @param assistanceRequest Request containing filtering parameters.
     * @return A list of matching Assistance records.
     */
    public List<Assistance> getAssistanceByOptions(AssistanceRequest assistanceRequest, String schedule){
        int userId = assistanceRequest.getIdUser();
        String userName = assistanceRequest.getUserName();
        String userRol = assistanceRequest.getRolUser();
        Boolean confirmation = assistanceRequest.getConfirmation();
        List<Assistance> filteredAssistances;
        if (confirmation != null) {
            filteredAssistances = assistanceRepo.findAssistanceByOptions(
                    userId != 0 ? userId : 0,
                    userName != null ? userName : ".*",
                    userRol != null ? userRol : ".*",
                    confirmation
            );
        } else {
            filteredAssistances = assistanceRepo.findAssistanceWithoutConfirmation(
                    userId != 0 ? userId : 0,
                    userName != null ? userName : ".*",
                    userRol != null ? userRol : ".*"
            );
        }
        List<Assistance> totalAssistances = new ArrayList<>();
        for (Assistance filteredAssistance : filteredAssistances){
            if(filteredAssistance.getIdSchedule().equals(schedule)){
                totalAssistances.add(filteredAssistance);
            }
        }
        return totalAssistances;
    }

    /**
     * Updates the confirmation status of a user's assistance record if changed.
     *
     * @param assistanceRequest Request containing user name and new confirmation status.
     */
    public void updateConfirmationForAssitance(AssistanceRequest assistanceRequest, Schedule schedule){
        List<Integer> assistances = schedule.getAssistances();
        for(Integer assistanceId : assistances){
            if(assistanceId == assistanceRequest.getIdUser()){
                Assistance requiredAssistance = assistanceRepo.getAssistanceByUserId(assistanceId);
                requiredAssistance.setConfirmation(true);
                assistanceRepo.save(requiredAssistance);
            }
        }
    }

    /**
     * Confirms all assistance records associated with a given schedule.
     *
     * @param schedule The schedule containing assistance records to confirm.
     */
    public void confirmAllAssistances(Schedule schedule){
        List<Integer> assistances = schedule.getAssistances();
        for(Integer assistanceId : assistances){
            Assistance requiredAssistance = assistanceRepo.getAssistanceByUserId(assistanceId);
            requiredAssistance.setConfirmation(true);
            assistanceRepo.save(requiredAssistance);
        }
    }

    /**
     * Deletes a user's assistance record based on their user name.
     *
     * @param assistanceRequest Request containing the user name.
     */
    public void deleteAssistanceForUser(AssistanceRequest assistanceRequest){
        assistanceRepo.delete(assistanceRepo.getAssistanceByUserName(assistanceRequest.getUserName()));
    }
}
