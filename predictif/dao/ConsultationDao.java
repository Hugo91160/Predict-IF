/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.dao;

import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;

import static dao.JpaUtil.obtenirContextePersistance;
import java.util.List;
import javax.persistence.Query;


/**
 *
 * @author meryem
 */
public class ConsultationDao {

    //Filtre les employes ayant le meme genre que le medium
    public List <Employe> filtrerEmployer(Medium medium) {
        //JpaUtil.obtenirContextePersistance().persist(medium);
        Query requete = obtenirContextePersistance().createQuery("Select e from Employe e where e.genre=:genre and e.disponibilite=true order by e.nbConsultation ASC");
        requete.setParameter("genre", medium.getGenre());
        List <Employe> listeEmploye = requete.getResultList();
        return listeEmploye;
    }
    
    
      
}