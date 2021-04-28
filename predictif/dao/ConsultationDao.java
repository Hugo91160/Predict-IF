/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.dao;

import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Client;

import  fr.insalyon.dasi.PredictIF.predictif.dao.JpaUtil.*;
import static fr.insalyon.dasi.PredictIF.predictif.dao.JpaUtil.obtenirContextePersistance;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author meryem
 */
public class ConsultationDao {

    
    public Consultation creerConsultation(Consultation consultation)
    {
        JpaUtil.obtenirContextePersistance().persist(consultation);
        return consultation;
    }
    /*
    public List<Consultation> getHistorique(Client client)
    {
        //JpaUtil.obtenirContextePersistance().persist(medium);
        Query requete = obtenirContextePersistance().createQuery("Select c from Consultation c where c.client=:client");
        requete.setParameter("client", client);
        List <Consultation> listeConsultation = requete.getResultList();
        return listeConsultation;
    }
    */
    
    public Consultation actualise(Consultation c)
    {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(c);
    }
    
    
    
      
}