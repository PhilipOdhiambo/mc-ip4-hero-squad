package dao;

import models.Hero;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

class Sql2oHeroDaoTest {

    private static Sql2oHeroDao heroDao;
    private static Connection conn;
    @BeforeAll
    static void beforeAll() {
        String connString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connString,"","");
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }

    @AfterAll
    static void afterAll() {
        conn.close();
    }

    @Test
    void add_addingHeroSetsId_int() {
        Hero hero = setupNewHero();
        int initialId = hero.getId(); // 0
        heroDao.add(hero);
        Assertions.assertNotEquals(initialId,hero.getId());
    }

    @Test
    void findById_findHeroById_hero() {
        Hero hero = setupNewHero();
        heroDao.add(hero);
        Hero foundHero = heroDao.findById(hero.getId());
        Assertions.assertEquals(hero,foundHero);
    }

    @Test
    void getAll_addedHeroesReturned_int() {
        Hero hero = setupNewHero();
        heroDao.add(hero);
        Assertions.assertTrue(heroDao.getAll().size() > 0);
    }

    @Test
    void update_changeHeroContents_hero() {
        Hero hero = setupNewHero();
        heroDao.add(hero);
        heroDao.update(hero.getId(),1,5,"changedHero","run very fast","not eating");
        Hero updatedHero = heroDao.findById(hero.getId());
        Assertions.assertNotEquals(hero.getName(),updatedHero.getName());
        Assertions.assertNotEquals(hero,updatedHero);
    }

    @Test
    void deleteById_deleteHeroById_null() {
        Hero hero = setupNewHero();
        heroDao.add(hero);
        int id = hero.getId();
        heroDao.deleteById(hero.getId());
        Assertions.assertNull(heroDao.findById(id));
    }

    @Test
    void clearAll_clearAllHeroes_0() {
        Hero hero = setupNewHero();
        Hero hero1 = new Hero("Hero1",5,"crying","sleep",5);
        heroDao.add(hero);
        heroDao.add(hero1);
        int daoSize = heroDao.getAll().size();
        heroDao.clearAllHeroes();
        Assertions.assertTrue(daoSize > 0 && daoSize > heroDao.getAll().size());
    }

    @Test
    void squadIdReturnedCorrectly_int() {
        Hero hero = setupNewHero();
        int initialSquadId = hero.getSquadId();
        heroDao.add(hero);
        Assertions.assertEquals(initialSquadId,heroDao.findById(hero.getId())
                .getSquadId());
    }

    // Helper function for testing
    public Hero setupNewHero() {
        return new Hero("Hero0",15,"run","food",1);
    }
}