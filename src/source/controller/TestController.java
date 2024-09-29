package source.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import source.model.centre.Centre;

@Controller
public class TestController {

    @GetMapping({ "/ajouterCentre" })
    public ModelAndView ajouterCentre() {
        ModelAndView mv = new ModelAndView("test");
        return mv;
    }

    @PostMapping({ "/ajouter" })
    public ModelAndView ajouterCentre(@RequestParam("libele") String libele) {
        Centre centre = new Centre();
        centre.setLibele(libele);
        String erreur = "";
        try {
            centre.create();
        } catch (Exception e) {
            erreur = e.getMessage();
        }
        ModelAndView mv = new ModelAndView("test");
        mv.addObject("erreur", erreur);
        return mv;
    }

    @GetMapping("/getCentre")
    public ModelAndView getCentre() {
        Centre centre = new Centre();
        String erreur = null;
        try {
            centre.getById(1);
        } catch (Exception e) {
            erreur = e.getMessage();
            // TODO: handle exception
        }

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("erreur", erreur);
        mv.addObject("centre", centre);
        return mv;

    }
}
