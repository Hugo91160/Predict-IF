/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.dao;

import static fr.insalyon.dasi.PredictIF.predictif.dao.JpaUtil.obtenirContextePersistance;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author alex
 */
public class EmployeDao {
    
    public void creation(Employe employe)
    {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
    
     //Filtre les employes ayant le meme genre que le medium
    public List <Employe> filtrerEmployer(Medium medium) {
        //JpaUtil.obtenirContextePersistance().persist(medium);
        Query requete = obtenirContextePersistance().createQuery("Select e from Employe e where e.genre=:genre and e.disponibilite=true order by e.nbConsultation ASC");
        requete.setParameter("genre", medium.getGenre());
        List <Employe> listeEmploye = requete.getResultList();
        return listeEmploye;
    }
    
    public Employe actualise(Employe e)
    {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(e);
    }
    
    public List<Employe> getListeEmploye() {

        Query requete = obtenirContextePersistance().createQuery("Select e from Employe e order by e.nbConsultation DESC");

        List<Employe> listeEmploye = requete.getResultList();
        return listeEmploye;
    }
    
  
    public Consultation getConsultation(Employe e) {
        
        Query requete = obtenirContextePersistance().createQuery("Select c from Consultation c where c.employe=:emp and c.dateFin is null");
        requete.setParameter("emp", e);
        List<Consultation> resultat = requete.getResultList();
        if (resultat.isEmpty())
        {
            System.out.println("Résultat de la requête nulle");
            return null;
        }
        
        return resultat.get(0);
    }
   
}
