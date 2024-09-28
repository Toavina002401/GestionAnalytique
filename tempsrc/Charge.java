package source.model.charge;

import source.model.unite.UniteOeuvre;

public class Charge {
    private int id;
    private String rubrique;
    private double prix;
    private char nature;
    private UniteOeuvre uniteOeuvre;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRubrique(String rubrique) {
        this.rubrique = rubrique;
    }

    public String getRubrique() {
        return rubrique;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setPrix(String prix) throws Exception {
        try {
            double v = Double.parseDouble(prix);
            this.setPrix(v);
        } catch (NumberFormatException e) {
            throw new Exception("Le prix ne doit contenir que des chiffres");
        }
    }

    public double getPrix() {
        return prix;
    }

    public void setNature(char nature) {
        this.nature = nature;
    }

    public char getNature() {
        return nature;
    }

    public void setUniteOeuvre(UniteOeuvre uniteOeuvre) {
        this.uniteOeuvre = uniteOeuvre;
    }

    public void getUniteOeuvre(int id) throws Exception{
        UniteOeuvre uo = new UniteOeuvre();
        uo.getById(id);
        this.uniteOeuvre = uo;
    }
    
}
