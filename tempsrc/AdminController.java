package source.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;

import source.model.unite.UniteOeuvre;
import source.model.centre.Centre;

@Controller
public class AdminController {
    @GetMapping("/")
    public ModelAndView index() throws Exception{
        UniteOeuvre uniteOeuvre = new UniteOeuvre();
        List<UniteOeuvre> listeUniteOeuvre = uniteOeuvre.getAll();
        Centre centre = new Centre();
        List<Centre> listeCentre = centre.getAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listeUniteOeuvre", listeUniteOeuvre);
        mav.addObject("listeCentre", listeCentre);
        return mav;
    }

    @GetMapping("/ajoutUnite")
    public ModelAndView ajoutUnite(@RequestParam("uniteAjout") String uniteAjout) throws Exception{
        UniteOeuvre uniteOeuvre = new UniteOeuvre(uniteAjout);
        uniteOeuvre.create();
        List<UniteOeuvre> listeUniteOeuvre = uniteOeuvre.getAll();
        Centre centre = new Centre();
        List<Centre> listeCentre = centre.getAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listeUniteOeuvre", listeUniteOeuvre);
        mav.addObject("listeCentre", listeCentre);
        return mav;
    }

    @GetMapping("/ajoutNouveauCentre")
    public ModelAndView ajoutCentre(@RequestParam("centreAjout") String centreAjout) throws Exception{
        Centre centre = new Centre(centreAjout);
        centre.create();
        UniteOeuvre uniteOeuvre = new UniteOeuvre();
        List<UniteOeuvre> listeUniteOeuvre = uniteOeuvre.getAll();
        List<Centre> listeCentre = centre.getAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listeUniteOeuvre", listeUniteOeuvre);
        mav.addObject("listeCentre", listeCentre);
        return mav;
    }

    @GetMapping("/addCharge")
    public ModelAndView ajouterCharge(HttpServletRequest request)throws Exception {
        String rubrique = request.getParameter("rubrique");
        String unite = request.getParameter("unite");
        String nature = request.getParameter("nature");

        Centre newcentre = new Centre();
        List<Centre> centres = newcentre.getAll();
        Map<Integer, Map<String, String>> centreValues = new HashMap<>();
        
        for (Centre centre : centres) {
            Integer centreId = centre.getId();
            String libele = centre.getLibele();
            String prix = request.getParameter(centreId + "_prix");
            String pourcentage = request.getParameter(centreId + "_pourcentage");
            
            Map<String, String> values = new HashMap<>();
            values.put("libele", libele);
            values.put("prix", prix);
            values.put("pourcentage", pourcentage);
            
            centreValues.put(centreId, values);
        }
        
        ModelAndView mav = new ModelAndView("tableaux");
        mav.addObject("rubrique", rubrique);
        mav.addObject("unite", unite);
        mav.addObject("nature", nature);
        mav.addObject("centreValues", centreValues);
        return mav;
    }

}