package edu.eci.cvds.EciBienestarMod3.service;

import edu.eci.cvds.EciBienestarMod3.repository.AssistanceMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistanceService {

    @Autowired
    private AssistanceMongoRepository assistanceRepo;
}
