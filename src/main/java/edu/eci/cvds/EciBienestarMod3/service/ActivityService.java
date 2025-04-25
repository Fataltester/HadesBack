package edu.eci.cvds.EciBienestarMod3.service;

import edu.eci.cvds.EciBienestarMod3.repository.ActivityMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityMongoRepository activityRepo;
}
