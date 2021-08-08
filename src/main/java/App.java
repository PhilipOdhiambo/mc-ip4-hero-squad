import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/",(req,res) -> {
            Map<String,Object> modal = new HashMap<>();
            return new ModelAndView(modal, "new-squad.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
