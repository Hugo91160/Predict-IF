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
@Entity
@Table(name="Astrologue")
public class Astrologue extends Medium{
    private String formation;
    private String promotion;

    public Astrologue() {
    }
    
    public Astrologue(String denomination, String genre, String presentation, String formation, String promotion) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }
    
}
