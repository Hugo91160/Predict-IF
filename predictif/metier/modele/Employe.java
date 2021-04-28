package fr.insalyon.dasi.PredictIF.predictif.metier.modele;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name="Employe")
public class Employe extends Utilisateur implements Serializable {


    private boolean disponibilite;
    private String genre;
    private int nbConsultation = 0;
    
 

    public Employe() {
        
    }

    public Employe(String nom, String prenom,  String numeroTel, String motDePasse, String mail ,boolean disponibilite, String genre) {
        super(nom, prenom, numeroTel, motDePasse, mail);
        this.disponibilite = disponibilite;
        this.genre = genre;
    }

    public boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getGenre() {
        return genre;
    }

    public int getNbConsultation() {
        return nbConsultation;
    }

    public void setNbConsultation(int nbConsultation) {
        this.nbConsultation = nbConsultation;
    }

    public void incrementeConsultation() {
        this.nbConsultation++;
    }
    
    
}
