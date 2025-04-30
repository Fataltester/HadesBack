package edu.eci.cvds.EciBienestarMod3.controller;

import edu.eci.cvds.EciBienestarMod3.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.eci.cvds.EciBienestarMod3.model.Assistance;

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

    @GetMapping("/activity/{idActivity}")
    public List<Assistance> getAssistancesByActivity(@PathVariable String idActivity) {
        return assistanceServ.getAssistancesByActivity(idActivity);
    }

    @GetMapping("/user/{userId}")
    public List<Assistance> getAssistancesByUser(@PathVariable int userId) {
        return assistanceServ.getAssistancesByUser(userId);
    }

    @GetMapping("/role/{userRol}")
    public List<Assistance> getAssistancesByUserRol(@PathVariable String userRol) {
        return assistanceServ.getAssistancesByUserRol(userRol);
    }

    @PostMapping("/register")
    public Assistance registerAssistance(@RequestBody Assistance assistance) {
        return assistanceServ.registerAssistance(assistance);
    }

    @DeleteMapping("/{id}")
    public void deleteAssistance(@PathVariable String id) {
        assistanceServ.deleteAssistance(id);
    }

    @PutMapping("/confirm-all/{idActivity}")
    public int confirmAllAssistancesForActivity(@PathVariable String idActivity) {
        return assistanceServ.confirmAllAssistancesForActivity(idActivity);
    }

    @GetMapping("/confirmed/{idActivity}")
    public List<Assistance> getConfirmedAssistancesByActivity(@PathVariable String idActivity) {
        return assistanceServ.getConfirmedAssistancesByActivity(idActivity);
    }

    @GetMapping("/check/{userId}/{idActivity}")
    public boolean hasUserAttendedActivity(@PathVariable int userId, @PathVariable String idActivity) {
        return assistanceServ.hasUserAttendedActivity(userId, idActivity);
    }
}
