/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.dao;

import static fr.insalyon.dasi.PredictIF.predictif.dao.JpaUtil.obtenirContextePersistance;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Utilisateur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
    
    public Medium actualise(Medium medium)
    {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(medium);
    }
    
    public Medium chercherParDenomination(String denomination) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT m FROM Medium m WHERE m.denomination = :denomination", Medium.class);
        query.setParameter("denomination", denomination); // correspond au paramètre ":mail" dans la requête
        List<Medium> mediums = query.getResultList();
        Medium result = null;
        if (!mediums.isEmpty()) {
            result = mediums.get(0); // premier de la liste
        }
        return result;
    }
}
