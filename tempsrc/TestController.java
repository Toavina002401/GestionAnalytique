package source.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import source.model.centre.Centre;
import source.model.unite.UniteOeuvre;

@Controller
public class TestController {
    public TestController() {
    }

    @GetMapping({ "/ajouterCentre" })
    public ModelAndView ajouterCentre() {
        ModelAndView mv = new ModelAndView("test");
        return mv;
    }

    @PostMapping("/ajouterCentre")
    public String ajouterCentre(@RequestParam("libele") String libele) {
        Centre unite = new Centre();
        unite.setLibele(libele);

        try {
            unite.create();
            return "redirect:/listeUnites"; // Redirection après l'ajout réussi
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Page d'erreur à définir
        }
    }
}
