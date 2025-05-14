package edu.eci.cvds.EciBienestarMod3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@NoArgsConstructor
public class Resource {

    private String name;
    private String amount;
}
