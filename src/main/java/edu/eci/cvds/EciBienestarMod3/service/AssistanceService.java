package edu.eci.cvds.EciBienestarMod3.service;

import edu.eci.cvds.EciBienestarMod3.repository.AssistanceMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.eci.cvds.EciBienestarMod3.model.Assistance;


@Service
public class AssistanceService {

    @Autowired
    private AssistanceMongoRepository assistanceRepo;

    public Assistance createAssistance(Assistance assistance) {
        if (assistance.getId() == null || assistance.getId().isEmpty()) {
            assistance.setId(UUID.randomUUID().toString());
        }
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

    public List<Assistance> getAssistancesByActivity(String idActivity) {
        return assistanceRepo.findByIdActivity(idActivity);
    }

    public List<Assistance> getAssistancesByUser(int userId) {
        return assistanceRepo.findByUserId(userId);
    }

    public List<Assistance> getAssistancesByUserRol(String userRol) {
        return assistanceRepo.findByUserRol(userRol);
    }

    public Assistance registerAssistance(Assistance assistance) {
        // Verificar si ya existe una asistencia para este usuario y actividad
        Assistance existingAssistance = assistanceRepo.findByUserIdAndIdActivity(
                assistance.getUserId(), assistance.getIdActivity());

        if (existingAssistance != null) {
            // Si ya existe, retornamos la existente
            return existingAssistance;
        }

        // Si no existe, guardamos la nueva asistencia
        if (assistance.getId() == null || assistance.getId().isEmpty()) {
            assistance.setId(UUID.randomUUID().toString());
        }
        return assistanceRepo.save(assistance);
    }

    public void deleteAssistance(String id) {
        assistanceRepo.deleteById(id);
    }

    public int confirmAllAssistancesForActivity(String idActivity) {
        List<Assistance> assistances = assistanceRepo.findByIdActivity(idActivity);
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

    public List<Assistance> getConfirmedAssistancesByActivity(String idActivity) {
        return assistanceRepo.findByIdActivityAndConfirmation(idActivity, true);
    }

    public boolean hasUserAttendedActivity(int userId, String idActivity) {
        Assistance assistance = assistanceRepo.findByUserIdAndIdActivity(userId, idActivity);
        return assistance != null && assistance.isConfirmation();
    }
}
