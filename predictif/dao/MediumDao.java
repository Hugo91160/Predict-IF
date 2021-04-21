/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.dao;

import static fr.insalyon.dasi.PredictIF.predictif.dao.JpaUtil.obtenirContextePersistance;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hugol
 */
public class MediumDao {
    public void creation(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(medium);
    }
    
    //Recupere le top5 des mediums
    public List <Medium> getTop5() {

        Query requete = obtenirContextePersistance().createQuery("Select m from Medium m order by m.NombreConsultation DESC").setMaxResults(5);

        List <Medium> listeMedium = requete.getResultList();
        return listeMedium;
    }
    
    public Medium actualiseNbConsult(Medium medium)
    {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(medium);
    }
}
