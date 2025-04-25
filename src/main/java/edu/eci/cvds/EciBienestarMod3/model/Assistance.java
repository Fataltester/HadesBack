package edu.eci.cvds.EciBienestarMod3.model;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;

@Document(collection = "Assistance")
public class Assistance {

    @Id
    private String id;
}
