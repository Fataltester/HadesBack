package edu.eci.cvds.EciBienestarMod3.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import edu.eci.cvds.EciBienestarMod3.dto.AssistanceRequest;
import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.notifications.IEmailService;
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
    @Autowired
    private IEmailService emailService;

    public Assistance createAssistance(String schedule, AssistanceRequest assistance, Activity actualActivity, Schedule actSchedule) {
        Assistance assistance1 = new Assistance();
        assistance1.setIdSchedule(schedule);
        assistance1.setUserId(assistance.getIdUser());
        assistance1.setUserName(assistance.getUserName());
        assistance1.setUserRol(assistance.getRolUser());
        assistance1.setConfirmation(assistance.getConfirmation());
        assistance1.setUserEmail(assistance.getUserEmail());
        String userEmail = assistance.getUserEmail();
        String subject = "Confirmación de Inscripcion a la clase"+ " " + actualActivity.getActivityType().toString();
        String message = String.format(
                "Hola, tu inscripción al evento está programada para el %d de %s de %d.",
                actSchedule.getNumberDay(),
                actSchedule.getMonth(),
                actSchedule.getYear()
        );
        emailService.sendEmail(new String[]{userEmail}, subject, message);
        return assistanceRepo.save(assistance1);
    }

    public Assistance studentCreateAssistance(String schedule, AssistanceRequest assistance, Activity actualActivity, Schedule actSchedule) {
        Assistance assistance1 = new Assistance();
        assistance1.setIdSchedule(schedule);
        assistance1.setUserId(assistance.getIdUser());
        assistance1.setUserName(assistance.getUserName());
        assistance1.setUserRol(assistance.getRolUser());
        assistance1.setUserEmail(assistance.getUserEmail());
        assistance1.setConfirmation(false);
        String userEmail = assistance.getUserEmail();
        String subject = "Confirmación de Inscripcion a la clase"+ " " + actualActivity.getActivityType().toString();
        String message = String.format(
                "Hola, tu inscripción al evento está programada para el %d de %s de %d.",
                actSchedule.getNumberDay(),
                actSchedule.getMonth(),
                actSchedule.getYear()
        );
        emailService.sendEmail(new String[]{userEmail}, subject, message);
        return assistanceRepo.save(assistance1);
    }

    public List<Assistance> getAssistanceByOptions(AssistanceRequest assistanceRequest, String schedule){
        int userId = assistanceRequest.getIdUser();
        String userName = assistanceRequest.getUserName();
        String userRol = assistanceRequest.getRolUser();
        String userEmail = assistanceRequest.getUserEmail();
        Boolean confirmation = assistanceRequest.getConfirmation();
        List<Assistance> filteredAssistances;
        if (confirmation != null) {
            filteredAssistances = assistanceRepo.findAssistanceByOptions(
                    userId != 0 ? userId : 0,
                    userName != null ? userName : ".*",
                    userRol != null ? userRol : ".*",
                    userEmail != null ? userEmail : ".*",
                    confirmation
            );
        } else {
            filteredAssistances = assistanceRepo.findAssistanceWithoutConfirmation(
                    userId != 0 ? userId : 0,
                    userName != null ? userName : ".*",
                    userRol != null ? userRol : ".*",
                    userEmail != null ? userEmail : ".*"
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

    public void updateConfirmationForAssitance(AssistanceRequest assistanceRequest, Schedule schedule, Activity actActivity, Schedule actSchedule){
        List<Integer> assistances = schedule.getAssistances();
        for(Integer assistanceId : assistances){
            if(assistanceId == assistanceRequest.getIdUser()){
                Assistance requiredAssistance = assistanceRepo.getAssistanceByUserId(assistanceId);
                requiredAssistance.setConfirmation(true);
                assistanceRepo.save(requiredAssistance);
                String userEmail = requiredAssistance.getUserEmail();
                String subject = "Confirmación de Inscripcion a la clase"+ " " + actActivity.getActivityType().toString();
                String message = String.format(
                        "Hola, tu asistencia al evento del %d de %s de %d ha sido confirmada.",
                        actSchedule.getNumberDay(),
                        actSchedule.getMonth(),
                        actSchedule.getYear()
                );
                emailService.sendEmail(new String[]{userEmail}, subject, message);
            }
        }

    }

    public void confirmAllAssistances(AssistanceRequest assistanceRequest, Schedule schedule, Activity actActivity, Schedule actSchedule){
        List<Integer> assistances = schedule.getAssistances();
        for(Integer assistanceId : assistances){
            Assistance requiredAssistance = assistanceRepo.getAssistanceByUserId(assistanceId);
            requiredAssistance.setConfirmation(true);
            String userEmail = requiredAssistance.getUserEmail();
            String subject = "Confirmación de Inscripcion a la clase"+ " " + actActivity.getActivityType();
            String message = String.format(
                    "Hola, tu asistencia al evento del %d de %s de %d ha sido confirmada.",
                    actSchedule.getNumberDay(),
                    actSchedule.getMonth(),
                    actSchedule.getYear()
            );
            emailService.sendEmail(new String[]{userEmail}, subject, message);
            assistanceRepo.save(requiredAssistance);
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
