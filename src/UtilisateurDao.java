/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.dao;

import fr.insalyon.dasi.predictif.metier.modele.Utilisateur;

/**
 *
 * @author alex
 */
public class UtilisateurDao {
    
    public void inscription(Utilisateur utilisateur)
    {
        JpaUtil.obtenirContextePersistance().persist(utilisateur);
    }
    
    
}
