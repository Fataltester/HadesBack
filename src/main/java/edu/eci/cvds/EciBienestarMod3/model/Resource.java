package edu.eci.cvds.EciBienestarMod3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a resource required for an activity.
 * Examples include musical instruments, balls, mats, etc.
 */
@Setter
@Getter
@NoArgsConstructor
public class Resource {

    private String name;
    private String amount;
}
