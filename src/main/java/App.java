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


        // Landing page
        get("/",(req,res) -> {
            Map<String,Object> modal = new HashMap<>();
            return new ModelAndView(modal, "landing-page.hbs");
        }, new HandlebarsTemplateEngine());

        // Hero-squad page
        get("/hero-squad", (req,res) -> {
            List<Squad> squads = squadDao.getAll();
            Map<String,Object> model = new HashMap<>();
            model.put("squads",squads);
            return new ModelAndView(model,"hero-squad.hbs");
        }, new HandlebarsTemplateEngine());

        // Squad Detail Page
        get("/hero-squad/:id",(req,res) -> {
            Squad activeSquad = squadDao.findById(Integer.parseInt(req.params("id")));
            List<Hero> squadHeroes = squadDao.getAllHeroesBySquad(activeSquad.getId());
            Map<String,Object> model = new HashMap<>();
            model.put("squad",activeSquad);
            model.put("heroes",squadHeroes);
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

    }
}
