/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.metier.modele;

import javax.persistence.*;

/**
 *
 * @author alex
 */
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long id;
    protected String nom;
    protected String prenom;
    protected String numerotel;
    protected String mdp;
    protected String adresseElectronique;

    public Utilisateur() {
    }
    
    public Utilisateur(String nom, String prenom, String numerotel, String mdp, String adresseElectronique) {
        this.nom = nom;
        this.prenom = prenom;
        this.numerotel = numerotel;
        this.mdp = mdp;
        this.adresseElectronique = adresseElectronique;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresseElectronique() {
        return adresseElectronique;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNumerotel() {
        return numerotel;
    }

    public String getMdp() {
        return mdp;
    }
    
    
    
    
}
