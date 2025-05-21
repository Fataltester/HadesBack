package edu.eci.cvds.EciBienestarMod3;

import edu.eci.cvds.EciBienestarMod3.model.Resource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

    @Test
    void testSetAndGetName() {
        Resource resource = new Resource();
        resource.setName("Computador");
        assertEquals("Computador", resource.getName());
    }

    @Test
    void testSetAndGetAmount() {
        Resource resource = new Resource();
        resource.setAmount("3");
        assertEquals("3", resource.getAmount());
    }

    @Test
    void testConstructor() {
        Resource r = new Resource();
        assertNull(r.getName());
        assertNull(r.getAmount());
    }
}
