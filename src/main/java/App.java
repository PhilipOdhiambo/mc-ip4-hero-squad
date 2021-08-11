import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import models.Hero;
import models.Squad;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/herosquad";
        Sql2o sql2o = new Sql2o(connectionString, "philip", "1234");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);


        /*+++++++++++++++++++++++++++ squad routes ++++++++++++++++++++++++++++++**/

        // Landing page
        get("/",(req,res) -> {
            Map<String,Object> modal = new HashMap<>();
            return new ModelAndView(modal, "landing-page.hbs");
        }, new HandlebarsTemplateEngine());

        // Get all squads
        get("/hero-squad", (req,res) -> {
            List<Squad> squads = squadDao.getAll();
            Map<String,Object> model = new HashMap<>();
            model.put("squads",squads);
            return new ModelAndView(model,"hero-squad.hbs");
        }, new HandlebarsTemplateEngine());

        // Get a single squad with nested heroes
        get("/hero-squad/:id",(req,res) -> {
            Squad activeSquad = squadDao.findById(Integer.parseInt(req.params("id")));
            List<Hero> squadHeroes = squadDao.getAllHeroesBySquad(activeSquad.getId());
            int numberOfHeroes = squadHeroes.size();
            Map<String,Object> model = new HashMap<>();
            model.put("squad",activeSquad);
            model.put("numberOfHeroes", numberOfHeroes);
            model.put("heroes",squadHeroes);
            model.put("maxReached", numberOfHeroes == activeSquad.getMaxSize());
            return new ModelAndView(model,"hero-squad-detail.hbs");
        }, new HandlebarsTemplateEngine());

        // Add new squad
        post("/new-hero-squad",(req,res) -> {
            String squadName = req.queryParams("new-squad-name");
            String squadCause = req.queryParams("new-squad-cause");
            String squadMaxSize = req.queryParams("new-squad-maxSize");
            Squad squad = new Squad(Integer.parseInt(squadMaxSize),squadName,squadCause);
            squadDao.add(squad);
            res.redirect("/hero-squad");
            return null;
        });

        // Edit a squad

        // Delete a single squad

        /* +++++++++++++++++++++++++ Hero routes +++++++++++++++++++++++++++++++++++*/

        // Load new hero form
        get("/hero-squad/:id/new-hero-form",(req,res)  -> {
            Map<String,Object> model = new HashMap<>();
            model.put("id",req.params("id"));
            return new ModelAndView(model,"new-hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        // Post new Hero
        post("/hero-squad/add-hero-form",(req,res)  -> {
            String heroName = req.queryParams("hero-name");
            String heroPower = req.queryParams("hero-power");
            String heroWeakness = req.queryParams("hero-weakness");
            int heroAge = Integer.parseInt(req.queryParams("hero-age"));
            int squadId = Integer.parseInt(req.queryParams("id"));
            Hero hero = new Hero(heroName,heroAge,heroPower,heroWeakness,squadId);
            heroDao.add(hero);
            res.redirect("/hero-squad/" + req.queryParams("id"));
            return null;
        });

        // Load hero update form
        get("/heroes/:id/update", (req,res)-> {
            Map<String,Object> model = new HashMap<>();
            Hero hero = heroDao.findById(Integer.parseInt(req.params(":id")));
            model.put("hero",hero);
            return new ModelAndView(model, "update-hero.hbs");
        }, new HandlebarsTemplateEngine());

        // Update hero
        post ("/hero/update",(req,res)->{
            int id = Integer.parseInt(req.queryParams("id"));
            int squadId = heroDao.findById(id).getSquadId();
            int age = Integer.parseInt(req.queryParams("edit-hero-age"));
            String name = req.queryParams("edit-hero-name");
            String power = req.queryParams("edit-hero-power");
            String weakness = req.queryParams("edit-hero-weakness");
            heroDao.update(id,squadId,age,name,power,weakness);
            res.redirect("/hero-squad/" + squadId);
            return null;
        });

        // Delete one hero
        get("/heroes/:id/delete",(req,res)-> {
            int heroId = Integer.parseInt(req.params(":id"));
            int squadId = heroDao.findById(heroId).getSquadId();
            heroDao.deleteById(heroId);
            res.redirect("/hero-squad/" + squadId);
            return null;
        });

        // Delete all heroes

    }
}
