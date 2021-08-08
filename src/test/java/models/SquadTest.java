package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquadTest {

    @Test
    void newSquadCreatedCorrectly_true() {
        Squad squad = setupNewSquad();
        assertTrue(squad instanceof Squad);
    }

    @Test
    void newSquad_nameInstantiatedCorrectly_string() {
        Squad squad = setupNewSquad();
        assertEquals("squad1", squad.getName());
    }

    @Test
    void newSquad_maxNumberInitiateCorrectly_int() {
        Squad squad = setupNewSquad();
        assertEquals(1,squad.getMaxSize());
    }

    @Test
    void newSquad_causeInitiatedCorrectly_string() {
        Squad squad = setupNewSquad();
        assertEquals("sexism", squad.getCause());
    }


    // helper methods
    public  Squad setupNewSquad() {
        return new Squad(1,"squad1","sexism");
    }

}