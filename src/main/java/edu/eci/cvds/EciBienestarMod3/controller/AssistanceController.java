package edu.eci.cvds.EciBienestarMod3.controller;

import edu.eci.cvds.EciBienestarMod3.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assistance")
public class AssistanceController {

    @Autowired
    private AssistanceService assistanceServ;
}
