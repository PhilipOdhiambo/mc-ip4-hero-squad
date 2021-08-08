package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    @Test
    void newHeroCreatedCorrectly_true() {
        Hero hero = setupNewHero();
        assertEquals(true, hero instanceof Hero);
    }

    @Test
    void newHero_nameInstantiatedCorrectly_string() {
        Hero hero = setupNewHero();
        assertEquals("Hero name",hero.getName());
    }

    @Test
    void newHero_ageInstantiatedCorrectly_int() {
        Hero hero = setupNewHero();
        assertEquals(30,hero.getAge());
    }

    @Test
    void newHero_strengthInstantiatedCorrectly_string() {
        Hero hero = setupNewHero();
        assertEquals("strength", hero.getStrength());
    }

    @Test
    void newHero_weaknessInstantiatedCorrectly() {
        Hero hero = setupNewHero();
        assertEquals("weakness", hero.getWeakness());
    }

    // helper methods
    public  Hero setupNewHero() {
        return new Hero("Hero name",30,"strength","weakness",1);
    }
}