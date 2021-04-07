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
@Entity
@Table(name="Cartomancien")
public class Cartomancien extends Medium {
    
    public Cartomancien(String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
    }
    
}
