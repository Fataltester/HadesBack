package edu.eci.cvds.EciBienestarMod3.controller;

import edu.eci.cvds.EciBienestarMod3.dto.AssistanceRequest;
import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.EciBienestarException;
import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.repository.ScheduleMongoRepository;
import edu.eci.cvds.EciBienestarMod3.service.ActivityService;
import edu.eci.cvds.EciBienestarMod3.service.AssistanceService;
import edu.eci.cvds.EciBienestarMod3.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.eci.cvds.EciBienestarMod3.model.Assistance;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Month;
import java.util.List;


@RestController
@RequestMapping("/api/assistance")
public class AssistanceController {

    @Autowired
    private AssistanceService assistanceServ;
    @Autowired
    private ActivityService activityServ;
    @Autowired
    private ScheduleMongoRepository scheduleRep;


    @PostMapping("/newAssistance")
    public Assistance createAssistance(@RequestBody AssistanceRequest assistanceRequest) throws EciBienestarException {
        Activity requiredActivity = activityServ.getActivityBySchedule(activity);
        List<String> schedules = requiredActivity.getSchedules();
        for(String schedule : schedules){
            Schedule actSchedule = scheduleRep.getScheduleBy(schedule);
            if(actSchedule.getNumberDay() == assistanceRequest.getNumberDay() && actSchedule.getMonth() == assistanceRequest.getMonth()){
                return assistanceServ.createAssistance(schedule,assistanceRequest);
            }
        }
        throw new EciBienestarException(EciBienestarException.TYPE_NOT_FOUND);
    }

    @GetMapping("/historial")
    public List<Assistance> getAllAssistances() {
        return assistanceServ.getAllAssistances();
    }

    // refactorizar
    @PutMapping("/confirmation/{id}")
    public void updateAssistanceConfirmation(@PathVariable String id, @RequestBody boolean confirmation) {
        assistanceServ.updateAssistanceConfirmation(id, confirmation);
    }
    // refactorizar
    @GetMapping("/schedule/{idSchedule}")
    public List<Assistance> getAssistancesBySchedule(@PathVariable String idSchedule) {
        return assistanceServ.getAssistancesBySchedule(idSchedule);
    }

    @GetMapping("/user/{userId}")
    public List<Assistance> getAssistancesByUser(@PathVariable int userId) {
        return assistanceServ.getAssistancesByUser(userId);
    }

    @GetMapping("/role/{userRol}")
    public List<Assistance> getAssistancesByUserRol(@PathVariable String userRol) {
        return assistanceServ.getAssistancesByUserRol(userRol);
    }
    // refactorizar
    @DeleteMapping("/{id}")
    public void deleteAssistance(@PathVariable String id) {
        assistanceServ.deleteAssistance(id);
    }
    // refactorizar
    @PutMapping("/confirm-all/{idSchedule}")
    public int confirmAllAssistancesForSchedule(@PathVariable String idSchedule) {
        return assistanceServ.confirmAllAssistancesForSchedule(idSchedule);
    }
    // refactorizar
    @GetMapping("/confirmed/{idSchedule}")
    public List<Assistance> getConfirmedAssistancesBySchedule(@PathVariable String idSchedule) {
        return assistanceServ.getConfirmedAssistancesBySchedule(idSchedule);
    }
    // refactorizar
    @GetMapping("/check/{userId}/{idSchedule}")
    public boolean hasUserAttendedSchedule(@PathVariable int userId, @PathVariable String idSchedule) {
        return assistanceServ.hasUserAttendedSchedule(userId, idSchedule);
    }
}
