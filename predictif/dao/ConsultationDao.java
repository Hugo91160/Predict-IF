/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.dao;

import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;

/**
 *
 * @author alex
 */
public class ConsultationDao {

    public void organisationConsultation(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
    
}
