package edu.eci.cvds.EciBienestarMod3.controller;

import edu.eci.cvds.EciBienestarMod3.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.eci.cvds.EciBienestarMod3.model.Assistance;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@RestController
@RequestMapping("/api/assistance")
public class AssistanceController {

    @Autowired
    private AssistanceService assistanceServ;

    @PostMapping("/newAssistance")
    public Assistance createAssistance(@RequestBody Assistance assistance) {
        return assistanceServ.createAssistance(assistance);
    }

    @GetMapping("/historial")
    public List<Assistance> getAllAssistances() {
        return assistanceServ.getAllAssistances();
    }

    @GetMapping("/{id}")
    public Assistance getAssistanceById(@PathVariable String id) {
        return assistanceServ.getAssistanceById(id);
    }

    @PutMapping("/confirmation/{id}")
    public void updateAssistanceConfirmation(@PathVariable String id, @RequestBody boolean confirmation) {
        assistanceServ.updateAssistanceConfirmation(id, confirmation);
    }

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

    /*@PostMapping("/register")
    public Assistance registerAssistance(@RequestBody Assistance assistance) {
        return assistanceServ.registerAssistance(assistance);
    }*/

    @DeleteMapping("/{id}")
    public void deleteAssistance(@PathVariable String id) {
        assistanceServ.deleteAssistance(id);
    }

    @PutMapping("/confirm-all/{idSchedule}")
    public int confirmAllAssistancesForSchedule(@PathVariable String idSchedule) {
        return assistanceServ.confirmAllAssistancesForSchedule(idSchedule);
    }

    @GetMapping("/confirmed/{idSchedule}")
    public List<Assistance> getConfirmedAssistancesBySchedule(@PathVariable String idSchedule) {
        return assistanceServ.getConfirmedAssistancesBySchedule(idSchedule);
    }

    @GetMapping("/check/{userId}/{idSchedule}")
    public boolean hasUserAttendedSchedule(@PathVariable int userId, @PathVariable String idSchedule) {
        return assistanceServ.hasUserAttendedSchedule(userId, idSchedule);
    }
}
