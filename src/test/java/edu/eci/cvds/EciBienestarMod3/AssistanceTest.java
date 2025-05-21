package edu.eci.cvds.EciBienestarMod3;

import edu.eci.cvds.EciBienestarMod3.model.Assistance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AssistanceTest {

    private Assistance assistance;

    @BeforeEach
    void setUp() {
        assistance = new Assistance();
    }

    @Test
    void testSettersAndGetters() {
        assistance.setId("1234");
        assistance.setConfirmation(true);
        assistance.setUserName("John Doe");
        assistance.setUserId(5678);
        assistance.setUserRol("Student");
        assistance.setIdSchedule("1356");

        assertEquals("1234", assistance.getId());
        assertTrue(assistance.getConfirmation());
        assertEquals("John Doe", assistance.getUserName());
        assertEquals(5678, assistance.getUserId());
        assertEquals("Student", assistance.getUserRol());
        assertEquals("1356", assistance.getIdSchedule());
    }

    @Test
    void testFullConstructor() {
        Assistance a = new Assistance("1234", true, "John Doe",
                5678, "Student", "1356");

        assertEquals("1234", a.getId());
        assertTrue(a.getConfirmation());
        assertEquals("John Doe", a.getUserName());
        assertEquals(5678, a.getUserId());
        assertEquals("Student", a.getUserRol());
        assertEquals("1356", a.getIdSchedule());
    }
}
