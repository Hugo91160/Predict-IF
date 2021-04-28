/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.dao;

import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Client;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import javax.persistence.EntityManager;

/**
 *
 * @author alex
 */
public class ClientDao {
    public void inscription(Client client)
    {
        JpaUtil.obtenirContextePersistance().persist(client);
    }
    
    public Client actualise(Client c)
    {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(c);
    }
    
    

    
}
