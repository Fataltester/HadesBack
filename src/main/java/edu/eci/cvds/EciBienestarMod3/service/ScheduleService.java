package edu.eci.cvds.EciBienestarMod3.service;

import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.model.enumeration.ScheduleState;
import edu.eci.cvds.EciBienestarMod3.repository.ScheduleMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    ScheduleMongoRepository scheduleRepository;

    Schedule createSchedule(Schedule schedule){
        //Para evitar que se actualice
        if(scheduleRepository.existsById(schedule.getId())){
            return  null;
        }else{
            return scheduleRepository.save(schedule);
        }
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

}
