/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.dao;

import fr.insalyon.dasi.predictif.metier.modele.Utilisateur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author alex
 */
public class UtilisateurDao {
    
    public void creation(Utilisateur utilisateur)
    {
        JpaUtil.obtenirContextePersistance().persist(utilisateur);
    }
    
     public Utilisateur chercherParId(Long userId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Utilisateur.class, userId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Utilisateur chercherParMail(String userMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Utilisateur> query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.adresseElectronique = :mail", Utilisateur.class);
        query.setParameter("mail", userMail); // correspond au paramètre ":mail" dans la requête
        List<Utilisateur> users = query.getResultList();
        Utilisateur result = null;
        if (!users.isEmpty()) {
            result = users.get(0); // premier de la liste
        }
        return result;
    }
    
}
