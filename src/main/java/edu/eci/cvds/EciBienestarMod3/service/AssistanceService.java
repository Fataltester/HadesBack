package edu.eci.cvds.EciBienestarMod3.service;

import edu.eci.cvds.EciBienestarMod3.repository.AssistanceMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.eci.cvds.EciBienestarMod3.model.Assistance;
import edu.eci.cvds.EciBienestarMod3.model.EciBienestarException;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;


@Service
public class AssistanceService {

    @Autowired
    private AssistanceMongoRepository assistanceRepo;

    public Assistance createAssistance(Assistance assistance) {
        return assistanceRepo.save(assistance);
    }

    public List<Assistance> getAllAssistances() {
        return assistanceRepo.findAll();
    }

    public Assistance getAssistanceById(String id) {
        return assistanceRepo.findById(id).orElse(null);
    }

    public void updateAssistanceConfirmation(String id, boolean confirmation) {
        Assistance assistance = assistanceRepo.findById(id).orElse(null);
        if (assistance != null) {
            assistance.setConfirmation(confirmation);
            assistanceRepo.save(assistance);
        }
    }


    public List<Assistance> getAssistancesBySchedule(String idSchedule) {
        return assistanceRepo.findByIdSchedule(idSchedule);
    }

    public List<Assistance> getAssistancesByUser(int userId) {
        return assistanceRepo.findByUserId(userId);
    }


    public List<Assistance> getAssistancesByUserNameContaining(String userName) {
        return assistanceRepo.findByUserNameContaining(userName);
    }

    public List<Assistance> getAssistancesByUserRol(String userRol) {
        List<Assistance> assistances = new ArrayList<>();
        for (Assistance assistance : assistanceRepo.findAll()) {
            if (assistance.getUserRol().equals(userRol)) {
                assistances.add(assistance);
            }
        }
        return assistances;
    }

    public void deleteAssistance(String id) {
        assistanceRepo.deleteById(id);
    }


    public int confirmAllAssistancesForSchedule(String idSchedule) {
        List<Assistance> assistances = assistanceRepo.findByIdSchedule(idSchedule);
        int count = 0;

        for (Assistance assistance : assistances) {
            if (!assistance.isConfirmation()) {
                assistance.setConfirmation(true);
                assistanceRepo.save(assistance);
                count++;
            }
        }

        return count;
    }

    public List<Assistance> getConfirmedAssistancesBySchedule(String idSchedule) {
        return assistanceRepo.findByIdScheduleAndConfirmation(idSchedule, true);
    }

    public boolean hasUserAttendedSchedule(int userId, String idSchedule) {
        Assistance assistance = assistanceRepo.findByUserIdAndIdSchedule(userId, idSchedule);
        return assistance != null && assistance.isConfirmation();
    }
}
