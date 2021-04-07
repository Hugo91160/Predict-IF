/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.metier.modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author 
 */
@Entity
@Table(name = "COnsultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Medium medium;

    public Consultation(Date dateDebut, Client client, Employe employe, Medium medium) {
        this.dateDebut = dateDebut;
        this.client = client;
        this.employe = employe;
        this.medium = medium;
    }

    public Consultation() {
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date date) {
        this.dateDebut = date;
    }
    
    
}