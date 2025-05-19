package edu.eci.cvds.EciBienestarMod3.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import edu.eci.cvds.EciBienestarMod3.dto.AssistanceRequest;
import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.repository.AssistanceMongoRepository;
import edu.eci.cvds.EciBienestarMod3.repository.ScheduleMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.eci.cvds.EciBienestarMod3.model.Assistance;
import edu.eci.cvds.EciBienestarMod3.model.EciBienestarException;

import java.time.Month;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;


@Service
public class AssistanceService {

    @Autowired
    private AssistanceMongoRepository assistanceRepo;
    @Autowired
    private ScheduleMongoRepository scheduleRepo;

    public Assistance createAssistance(String schedule, AssistanceRequest assistance) {
        Assistance assistance1 = new Assistance();
        assistance1.setIdSchedule(schedule);
        assistance1.setUserId(assistance.getIdUser());
        assistance1.setUserName(assistance.getUserName());
        assistance1.setUserRol(assistance.getRolUser());
        assistance1.setConfirmation(assistance.getConfirmation());
        return assistanceRepo.save(assistance1);
    }

    public List<Assistance> getAssistanceByOptions(AssistanceRequest assistanceRequest){
        int userId = assistanceRequest.getIdUser();
        String userName = assistanceRequest.getUserName();
        String userRol = assistanceRequest.getRolUser();
        Boolean confirmation = assistanceRequest.getConfirmation();
        return assistanceRepo.findAssistanceByOptions(
                userId != 0 ? userId : 0, // 0 es no valida
                userName != null ? userName : ".*",
                userRol != null ? userRol : ".*",
                confirmation != null ? confirmation: true
        );
    }

    public List<Assistance> getAssistancesByUser(AssistanceRequest assistanceRequest, List<String> schedules){
        List<Assistance> totalAssistances = new ArrayList<>();
        for(String schedule : schedules){
            Schedule actSchedule = scheduleRepo.getScheduleById(schedule);
            List<Integer> actassistance = actSchedule.getAssistances();
            for(Integer assistance : actassistance){
                if(assistance == assistanceRequest.getIdUser()){
                    totalAssistances.add(assistanceRepo.findAssistanceByUserId(assistance));
                }
            }
        }
        return totalAssistances;
    }

    public void updateConfirmationForAssitance(AssistanceRequest assistanceRequest){
        Assistance currentAssistance = assistanceRepo.getAssistanceByUserName(assistanceRequest.getUserName());
        if(currentAssistance.getConfirmation() != assistanceRequest.getConfirmation()){
            currentAssistance.setConfirmation(assistanceRequest.getConfirmation());
            assistanceRepo.save(currentAssistance);
        }
    }

    public void deleteAssistanceForUser(AssistanceRequest assistanceRequest){
        assistanceRepo.delete(assistanceRepo.getAssistanceByUserName(assistanceRequest.getUserName()));
    }



//    public List<Assistance> getAllAssistances() {
//        return assistanceRepo.findAll();
//    }
//
//    public Assistance getAssistanceById(String id) {
//        return assistanceRepo.findById(id).orElse(null);
//    }
//
//    public void updateAssistanceConfirmation(String id, boolean confirmation) {
//        Assistance assistance = assistanceRepo.findById(id).orElse(null);
//        if (assistance != null) {
//            assistance.setConfirmation(confirmation);
//            assistanceRepo.save(assistance);
//        }
//    }
//
//
//    public List<Assistance> getAssistancesBySchedule(String idSchedule) {
//        return assistanceRepo.findByIdSchedule(idSchedule);
//    }
//
//    public List<Assistance> getAssistancesByUser(int userId) {
//        return assistanceRepo.findByUserId(userId);
//    }
//
//
//    public List<Assistance> getAssistancesByUserNameContaining(String userName) {
//        return assistanceRepo.findByUserNameContaining(userName);
//    }
//
//    public List<Assistance> getAssistancesByUserRol(String userRol) {
//        List<Assistance> assistances = new ArrayList<>();
//        for (Assistance assistance : assistanceRepo.findAll()) {
//            if (assistance.getUserRol().equals(userRol)) {
//                assistances.add(assistance);
//            }
//        }
//        return assistances;
//    }
//
//    public void deleteAssistance(String id) {
//        assistanceRepo.deleteById(id);
//    }
//
//
//    public int confirmAllAssistancesForSchedule(String idSchedule) {
//        List<Assistance> assistances = assistanceRepo.findByIdSchedule(idSchedule);
//        int count = 0;
//
//        for (Assistance assistance : assistances) {
//            if (!assistance.isConfirmation()) {
//                assistance.setConfirmation(true);
//                assistanceRepo.save(assistance);
//                count++;
//            }
//        }
//
//        return count;
//    }
//
//    public List<Assistance> getConfirmedAssistancesBySchedule(String idSchedule) {
//        return assistanceRepo.findByIdScheduleAndConfirmation(idSchedule, true);
//    }
//
//    public boolean hasUserAttendedSchedule(int userId, String idSchedule) {
//        Assistance assistance = assistanceRepo.findByUserIdAndIdSchedule(userId, idSchedule);
//        return assistance != null && assistance.isConfirmation();
//    }
}
