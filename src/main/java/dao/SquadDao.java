package dao;

import models.Hero;
import models.Squad;

import java.util.List;

public interface SquadDao {
    // LIST
    List<Squad> getAll();

    // LIST
    public List<Hero> getAllHeroesBySquad(int squadId);

    // CREATE
    void add (Squad squad);

    // READ
    Squad findById(int id);

    // UPDATE
    void update(int id, int maxSize, String name,String cause);

    // DELETE
    void deleteById(int id);
    void  clearAllSquads();
}
