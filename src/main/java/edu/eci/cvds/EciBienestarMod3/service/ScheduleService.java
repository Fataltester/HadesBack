package edu.eci.cvds.EciBienestarMod3.service;

import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.model.enumeration.ScheduleState;
import edu.eci.cvds.EciBienestarMod3.repository.ActivityMongoRepository;
import edu.eci.cvds.EciBienestarMod3.repository.ScheduleMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleService {

    @Autowired
    ScheduleMongoRepository scheduleRepository;

    @Autowired
    ActivityMongoRepository activityRepository;

    Schedule createSchedule(Schedule schedule){
        //Genero un id aleatorio
        if (schedule.getId() == null) {
            schedule.setId(UUID.randomUUID().toString());
        }

        //Para evitar que se actualice
        if(scheduleRepository.existsById(schedule.getId())){
            return  null;
        }else{
            return scheduleRepository.save(schedule);
        }
    }

    public List<String> createScheduleBetweenTwoDates(LocalDate startDate, LocalDate endDate, String activityId){
        Optional<Activity> activityOptional = activityRepository.findById(activityId);
        if(activityOptional.isEmpty()){
            return new ArrayList<>();
        }

        List<String> schedules = new ArrayList<>();

        List<LocalDate> fechas = getDatesForWeekday(startDate,endDate,activityOptional.get().getDayWeek());
        for(LocalDate fecha: fechas){
            Schedule schedule = new Schedule();
            schedule.setNumberDay(fecha.getDayOfMonth());
            schedule.setMonth(fecha.getMonth());
            schedule.setYear(fecha.getYear());
            schedule.setIdActivity(activityId);
            Schedule newSchedule = scheduleRepository.save(schedule);
            schedules.add(newSchedule.getId());
        }

        return schedules;
    }
    
    Schedule deleteSchedule(Schedule schedule){
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(schedule.getId());

        if(scheduleOptional.isPresent()){
            Schedule scheduleUpdate = scheduleOptional.get();
            if(scheduleUpdate.getState() != ScheduleState.CANCELADA && scheduleUpdate.getState() != ScheduleState.TERMINADA ) {
                scheduleUpdate.setState(ScheduleState.CANCELADA);
                return scheduleRepository.save(scheduleUpdate);
            }else{
                return scheduleUpdate;
            }
        }else{
            return  null;
        }
    }

    public void deleteAdminShcedule(String schedule){
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(schedule);

        if(scheduleOptional.isPresent()) {
            Schedule scheduleDelete = scheduleOptional.get();
            scheduleRepository.deleteById(scheduleDelete.getId());
        }
    }

    Schedule changeState(String id, ScheduleState newState){
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);

        if(scheduleOptional.isPresent()){
            Schedule schedule = scheduleOptional.get();
            schedule.setState(newState);
            return scheduleRepository.save(schedule);
        }else{
            return  null;
        }
    }

    List<Schedule> findByState(ScheduleState state){
        return scheduleRepository.findByState(state);
    }

    List<Schedule> findByStates(List<ScheduleState> states){
        return  scheduleRepository.findByStateIn((states));
    }

    private List<LocalDate> getDatesForWeekday(LocalDate startDate, LocalDate endDate, DayOfWeek targetDay) {
        List<LocalDate> dates = new ArrayList<>();

        LocalDate current = startDate;
        while (current.getDayOfWeek() != targetDay) {
            current = current.plusDays(1);
        }

        while (!current.isAfter(endDate)) {
            dates.add(current);
            current = current.plusWeeks(1);
        }

        return dates;
    }
}
