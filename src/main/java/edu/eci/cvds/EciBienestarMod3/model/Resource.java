package edu.eci.cvds.EciBienestarMod3.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "Resource")
public class Resource {

    @Id
    private String id;

    private String name;
    private String amount;

    public Resource() {}

    //SETTERS

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    //GETTERS

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }
}
