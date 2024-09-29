package source.controller;

import java.util.*;
import java.lang.Integer;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;

import source.model.unite.UniteOeuvre;
import source.model.centre.Centre;
import source.model.centreCharge.CentreCharge;
import source.model.charge.Charge;

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
        Charge newCharge = new Charge(1, rubrique, 0, nature.charAt(0), Integer.parseInt(unite));
        double prixTotal = 0;
        List<Centre> centres = newcentre.getAll();
        for (Centre centre : centres) {
            String prix = request.getParameter(centre.getId() + "_prix");
            prixTotal = prixTotal + Double.parseDouble(prix);
        }
        newCharge.setPrix(prixTotal);
        double totalPourcentage = 0;
        boolean isDirect = false;
        Centre getDirect = new Centre();
        for (Centre centre : centres) {
            String prix = request.getParameter(centre.getId() + "_prix");
            String pourcentage = request.getParameter(centre.getId() + "_pourcentage");
            totalPourcentage = totalPourcentage + Double.parseDouble(pourcentage);
            if (Double.parseDouble(pourcentage)==100) {
               isDirect = true; 
               getDirect = centre;
            }
            CentreCharge newCentreCharge = new CentreCharge(0, centre, newCharge, Double.parseDouble(prix), Double.parseDouble(pourcentage));
            newCentreCharge.create();
        }   
        newCharge.create();
        String reponse = "Le rubrique du nom "+rubrique+" est une charge ";
        if (totalPourcentage == 0) {
            reponse += "non incorporable car tous les pourcentages pour chaque centre sont egale a 0% .";
        }else{
            reponse += "incorporable mais c'est aussi une charge ";
            if (isDirect) {
                reponse += " direct car sont pourcentage est de 100% dans le centre "+ getDirect.getLibele() + " avec une prix de " + prixTotal + " AR ."; 
            }
        }
        ModelAndView mav = new ModelAndView("tableaux");
        mav.addObject("reponse", reponse);
        return mav;
    }

}