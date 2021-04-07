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
public abstract class Medium {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String denomination;
    private String genre;
    private String presentation;

    public Medium() {
    }

    public Medium(String denomination, String genre, String presentation) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
    }
    
    
}
