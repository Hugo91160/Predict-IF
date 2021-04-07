/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.metier.modele;

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
    private Long id;
    private String nom;
    private String prenom;
    private String numerotel;
    private String mdp;
    private String adresseElectronique;

    public Utilisateur() {
    }
    
    public Utilisateur(String nom, String prenom, String numerotel, String mdp, String adresseElectronique) {
        this.nom = nom;
        this.prenom = prenom;
        this.numerotel = numerotel;
        this.mdp = mdp;
        this.adresseElectronique = adresseElectronique;
    }
    
}
