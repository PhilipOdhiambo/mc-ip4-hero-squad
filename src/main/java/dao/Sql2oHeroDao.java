package dao;

import models.Hero;
import org.sql2o.*;
import java.util.List;

public class Sql2oHeroDao implements HeroDao {

    private final Sql2o sql2o;


    // Constructor

    public Sql2oHeroDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    // CREATE a new Hero
    @Override
    public void add(Hero hero) {
        String sql = "INSERT INTO heroes (name, age, power, weakness, squadId) VALUES (:name, :age, :power,:weakness, :squadId)";
        // Open connection
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(hero)
                    .executeUpdate()
                    //int id is now the row number (row “key”) of db
                    .getKey();
            //update object to set id now from database
            hero.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    // Return all Heroes
    @Override
    public List<Hero> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM heroes")
                    .executeAndFetch(Hero.class);
        }
    }

    // Return a hero with an id
    @Override
    public Hero findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM heroes WHERE id = :id")
                    .addParameter("id", id)
                    //fetch an individual item
                    .executeAndFetchFirst(Hero.class);
        }
    }

    // Update a hero
    @Override
    public void update(int id, int squadId, int age, String name,
                       String power, String weakness){
        String sql = "UPDATE heroes SET (squadId, name, age, power, weakness) = (:squadId, :name, :age, :power, :weakness) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("squadId", squadId)
                    .addParameter("name", name)
                    .addParameter("age", age)
                    .addParameter("power", power)
                    .addParameter("weakness", weakness)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    // Delete Hero with given id
    @Override
    public void deleteById(int id) {
        String sql = "DELETE from heroes WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    // Delete all heroes
    @Override
    public void clearAllHeroes() {
        String sql = "DELETE from heroes";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
