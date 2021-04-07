/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.metier.modele;
import java.text.ParseException;
import javax.persistence.*;

/**
 *
 * @author alex
 */

@Entity
@Table(name="Employe")
public class Employe extends Utilisateur{
    private boolean disponibilite;

    public Employe() {
    }

    public Employe(String nom, String prenom, String numerotel, String mdp, String adresseElectronique, boolean disponibilite) {
        super(nom, prenom, numerotel, mdp, adresseElectronique);
        this.disponibilite = disponibilite;
    }
    
    
}
