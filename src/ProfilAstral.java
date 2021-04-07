package metier.modele;

public class ProfilAstral {
    
    private String signeZodiaque;
    private String signeAstroChinois;
    private String couleurPorteBonheur;
    private String animalTotem;

    public ProfilAstral() {
    }

    public ProfilAstral(String signeZodiaque, String signeAstroChinois, String couleurPorteBonheur, String animalTotem) {
        this.signeZodiaque = signeZodiaque;
        this.signeAstroChinois = signeAstroChinois;
        this.couleurPorteBonheur = couleurPorteBonheur;
        this.animalTotem = animalTotem;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public String getSigneAstroChinois() {
        return signeAstroChinois;
    }

    public void setSigneAstroChinois(String signeAstroChinois) {
        this.signeAstroChinois = signeAstroChinois;
    }

    public String getCouleurPorteBonheur() {
        return couleurPorteBonheur;
    }

    public void setCouleurPorteBonheur(String couleurPorteBonheur) {
        this.couleurPorteBonheur = couleurPorteBonheur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }
    
    
    
}
