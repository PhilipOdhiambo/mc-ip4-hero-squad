package dao;

import models.Hero;
import models.Squad;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


public class Sql2oSquadDaoTest {
    private static Sql2oSquadDao squadDao;
    private static Sql2oHeroDao heroDao;
    private static Connection conn;

    @BeforeAll
    static void setUp() {
        String connString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connString,"","");
        squadDao = new Sql2oSquadDao(sql2o);
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }

    @AfterAll
    static void tearDown() {
        conn.close();
    }

    @Test
    void add_addingSquadSetsId_int() {
        Squad squad = setupNewSquad();
        int originalSquadId = squad.getId();
        squadDao.add(squad);
        Assertions.assertNotEquals(originalSquadId,squad.getId());
    }

    @Test
    void findById_findSquadById_squad() {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        Squad foundSquad = squadDao.findById(squad.getId());
        Assertions.assertEquals(squad,foundSquad);
    }

    @Test
    void getAll_addedSquadsAreReturnedFromGetAll_squad() {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        Assertions.assertEquals(1,squadDao.getAll().size());
    }

    @Test
    void getAll_noSquadsReturnsEmptyList_0() {
        squadDao.clearAllSquads();
        Assertions.assertEquals(0,squadDao.getAll().size());
    }

    @Test
    void update_updateChangesContent_squadFields() {
        Squad squad = setupNewSquad();
        String initialName = squad.getName();
        int initialMaxSize = squad.getMaxSize();
        squadDao.add(squad);
        squadDao.update(squad.getId(),2,"updated squad","sexism");
        Squad updatedSquad =squadDao.findById(squad.getId());
        Assertions.assertNotEquals(initialName,updatedSquad.getName());
        Assertions.assertNotEquals(initialMaxSize,updatedSquad.getMaxSize());
    }

    @Test
    void deleteById_deletesCorrectSquad_0() {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        squadDao.deleteById(squad.getId());
        Assertions.assertEquals(0,squadDao.getAll().size());
    }

    @Test
    void clearAll_clearsAllSquads_0() {
        Squad squad = setupNewSquad();
        Squad anotherSquad = new Squad(3,"squad2","crime");
        squadDao.add(squad);
        squadDao.add(anotherSquad);
        int squadCount = squadDao.getAll().size();
        squadDao.clearAllSquads();
        Assertions.assertTrue(squadCount > 0 && squadCount > squadDao.getAll().size());
    }

    @Test
    void getAllHeroesBySquad_returnHeroesPerSquad_heroes() {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        int squadId = squad.getId();
        // quadId, name, age, power, weakness

        Hero hero1 = new Hero("Hero1",30,"Climbing","water", squadId);
        Hero hero2 = new Hero("Hero2",40,"Running","water", squadId);
        Hero hero3 = new Hero("Hero3",50,"Wisdom","water",squadId);

        heroDao.add(hero1);
        heroDao.add(hero2);

        Assertions.assertEquals(2,squadDao.getAllHeroesBySquad(squadId).size());
        Assertions.assertTrue(squadDao.getAllHeroesBySquad(squadId)
                .contains(hero1));
        Assertions.assertTrue(squadDao.getAllHeroesBySquad(squadId)
                .contains(hero2));
        Assertions.assertFalse(squadDao.getAllHeroesBySquad(squadId)
                .contains(hero3));
    }

    // helper methods
    public Squad setupNewSquad() {
        return new Squad(1,"testSquad","sexism");
    }


}