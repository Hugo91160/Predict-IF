/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.metier.modele;

import java.util.Date;

/**
 *
 * @author alex
 */
public class Consultation {
    private Date date;
    private String heureDebut;
    private String heureFin;
    private Client client;
    private Employe employe;
    private Medium medium;

    public Consultation(Date date, String heureDebut, String heureFin, Client client, Employe employe, Medium medium) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.client = client;
        this.employe = employe;
        this.medium = medium;
    }
    
}
