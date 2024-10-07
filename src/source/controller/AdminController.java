package source.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;

import source.model.unite.UniteOeuvre;
import source.model.centre.Centre;
import source.model.centreCharge.CentreCharge;
import source.model.charge.Charge;
import source.model.divers.Repartition;

@Controller
public class AdminController {

    @GetMapping("/")
    public ModelAndView index() throws Exception {
        Charge charge = new Charge();
        List<Charge> allCharge = charge.getAll();
        CentreCharge centreCharge = new CentreCharge();
        List<CentreCharge> allCentreCharge = centreCharge.getAll();
        Centre centre = new Centre();
        List<Centre> listeCentre = centre.getAll();
        double[] totalVariableCentre = centreCharge.getSommePrixParCentre('V');
        double[] totalFixeCentre = centreCharge.getSommePrixParCentre('F');
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("allCharge", allCharge);
        mav.addObject("allCentreCharge", allCentreCharge);
        mav.addObject("listeCentre", listeCentre);
        mav.addObject("totalVariableCentre", totalVariableCentre);
        mav.addObject("totalFixeCentre", totalFixeCentre);
        return mav;
    }

    @GetMapping("/changeDash")
    public ModelAndView changeDash() {
        ModelAndView mav = new ModelAndView("dashboard");
        return mav;
    }

    @GetMapping("/inserCharge")
    public ModelAndView insertionCharge() throws Exception {
        Centre centre = new Centre();
        List<Centre> listeCentre = centre.getAll();
        UniteOeuvre uniteOeuvre = new UniteOeuvre();
        List<UniteOeuvre> listeUniteOeuvre = uniteOeuvre.getAll();
        ModelAndView mav = new ModelAndView("insertion");
        mav.addObject("listeUniteOeuvre", listeUniteOeuvre);
        mav.addObject("listeCentre", listeCentre);
        return mav;
    }

    @GetMapping("/inserCentre")
    public ModelAndView insertionCentre() {
        ModelAndView mav = new ModelAndView("centre");
        return mav;
    }

    @GetMapping("/ajoutNouveauCentre")
    public ModelAndView ajoutCentre(@RequestParam("centreAjout") String centreAjout,
            @RequestParam("typeCentre") int typeCentre) throws Exception {
        Centre centre = new Centre(centreAjout, typeCentre);
        centre.create();
        UniteOeuvre uniteOeuvre = new UniteOeuvre();
        List<UniteOeuvre> listeUniteOeuvre = uniteOeuvre.getAll();
        List<Centre> listeCentre = centre.getAll();
        ModelAndView mav = new ModelAndView("insertion");
        mav.addObject("listeUniteOeuvre", listeUniteOeuvre);
        mav.addObject("listeCentre", listeCentre);
        return mav;
    }

    @GetMapping("/ajoutUnite")
    public ModelAndView ajoutUnite(@RequestParam("uniteAjout") String uniteAjout) throws Exception {
        UniteOeuvre uniteOeuvre = new UniteOeuvre(uniteAjout);
        uniteOeuvre.create();
        List<UniteOeuvre> listeUniteOeuvre = uniteOeuvre.getAll();
        Centre centre = new Centre();
        List<Centre> listeCentre = centre.getAll();
        ModelAndView mav = new ModelAndView("insertion");
        mav.addObject("listeUniteOeuvre", listeUniteOeuvre);
        mav.addObject("listeCentre", listeCentre);
        return mav;
    }

    @GetMapping("/addCharge")
    public ModelAndView ajouterCharge(HttpServletRequest request) throws Exception {
        String rubrique = request.getParameter("rubrique");
        String unite = request.getParameter("unite");
        String nature = request.getParameter("nature");
        String prixname = request.getParameter("prix");

        Centre newcentre = new Centre();
        Charge newCharge = new Charge(1, rubrique, Double.parseDouble(prixname), nature.charAt(0),
                Integer.parseInt(unite));
        double prixTotal = 0;
        List<Centre> centres = newcentre.getAll();
        for (Centre centre : centres) {
            String prix = request.getParameter(centre.getId() + "_prix");
            prixTotal = prixTotal + Double.parseDouble(prix);
        }
        newCharge.setPrix(prixTotal);
        newCharge.create();
        double totalPourcentage = 0;
        boolean isDirect = false;
        Charge vaovao = Charge.getById(rubrique);
        Centre getDirect = new Centre();

        for (Centre centre : centres) {
            String prix = request.getParameter(centre.getId() + "_prix");
            if (prix == null) {
                break;
            }
            String pourcentage = request.getParameter(centre.getId() + "_pourcentage");
            totalPourcentage = totalPourcentage + Double.parseDouble(pourcentage);

            if (Double.parseDouble(pourcentage) == 100) {
                isDirect = true;
                getDirect = centre;
            }
            CentreCharge newCentreCharge = new CentreCharge(0, centre, vaovao, Double.parseDouble(prix),
                    Double.parseDouble(pourcentage));
            newCentreCharge.create();
        }

        String reponse = "Le rubrique du nom " + rubrique + " est une charge ";
        if (totalPourcentage == 0) {
            reponse += "non incorporable car tous les pourcentages pour chaque centre sont egale a 0% .";
        } else {
            reponse += "incorporable mais c'est aussi une charge ";
            if (isDirect) {
                reponse += " direct car sont pourcentage est de 100% dans le centre " + getDirect.getLibele()
                        + " avec une prix de " + prixname + " AR .";
            } else {
                reponse += "indirect car les pourcentage sont partagée par plus de 2 centre";
            }
        }

        Centre centre = new Centre();
        List<Centre> listeCentre = centre.getAll();
        UniteOeuvre uniteOeuvre = new UniteOeuvre();
        List<UniteOeuvre> listeUniteOeuvre = uniteOeuvre.getAll();
        ModelAndView mav = new ModelAndView("insertion");
        mav.addObject("listeUniteOeuvre", listeUniteOeuvre);
        mav.addObject("listeCentre", listeCentre);
        mav.addObject("reponse", reponse);
        return mav;
    }

    @GetMapping("repartition")
    public ModelAndView repartition() throws Exception {
        Charge charge = new Charge();
        List<Charge> allCharge = charge.getAll();
        CentreCharge centreCharge = new CentreCharge();
        List<CentreCharge> allCentreCharge = centreCharge.getAll();
        Centre centre = new Centre();
        List<Centre> listeCentre = centre.getAll();
        double[] totalVariableCentre = centreCharge.getSommePrixParCentre('V');
        double[] totalFixeCentre = centreCharge.getSommePrixParCentre('F');

        Repartition rep = new Repartition();
        Map<String, Object> data = rep.calculerCles(2);

        ModelAndView mav = new ModelAndView("repartition");
        mav.addObject("allCharge", allCharge);
        mav.addObject("allCentreCharge", allCentreCharge);
        mav.addObject("listeCentre", listeCentre);
        mav.addObject("totalVariableCentre", totalVariableCentre);
        mav.addObject("totalFixeCentre", totalFixeCentre);
        mav.addObject("data", data);
        return mav;
    }

    @GetMapping("repart")
    public ModelAndView repart(@RequestParam("quantite") int quantite) throws Exception{
        Charge charge = new Charge();
        List<Charge> allCharge = charge.getAll();
        CentreCharge centreCharge = new CentreCharge();
        List<CentreCharge> allCentreCharge = centreCharge.getAll();
        Centre centre = new Centre();
        List<Centre> listeCentre = centre.getAll();
        double[] totalVariableCentre = centreCharge.getSommePrixParCentre('V');
        double[] totalFixeCentre = centreCharge.getSommePrixParCentre('F');

        Repartition rep = new Repartition();
        Map<String, Object> data = rep.calculerCles(2, quantite);

        ModelAndView mav = new ModelAndView("repartition");
        mav.addObject("allCharge", allCharge);
        mav.addObject("allCentreCharge", allCentreCharge);
        mav.addObject("listeCentre", listeCentre);
        mav.addObject("totalVariableCentre", totalVariableCentre);
        mav.addObject("totalFixeCentre", totalFixeCentre);
        mav.addObject("data", data);
        return mav;
    }

    @GetMapping("/supr")
    public ModelAndView supprimer(@RequestParam("id") int idCharge) throws Exception {
        CentreCharge supCentreCharge = new CentreCharge();
        supCentreCharge.deleteFromCharge(idCharge);
        Charge supCharge = new Charge();
        supCharge.getById(idCharge);
        supCharge.delete();

        Charge charge = new Charge();
        List<Charge> allCharge = charge.getAll();
        CentreCharge centreCharge = new CentreCharge();
        List<CentreCharge> allCentreCharge = centreCharge.getAll();
        Centre centre = new Centre();
        List<Centre> listeCentre = centre.getAll();
        double[] totalVariableCentre = centreCharge.getSommePrixParCentre('V');
        double[] totalFixeCentre = centreCharge.getSommePrixParCentre('F');
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("allCharge", allCharge);
        mav.addObject("allCentreCharge", allCentreCharge);
        mav.addObject("listeCentre", listeCentre);
        mav.addObject("totalVariableCentre", totalVariableCentre);
        mav.addObject("totalFixeCentre", totalFixeCentre);
        return mav;
    }

}