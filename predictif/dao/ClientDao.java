/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.dao;

import fr.insalyon.dasi.predictif.metier.modele.Client;

/**
 *
 * @author alex
 */
public class ClientDao {
    public void inscription(Client client)
    {
        JpaUtil.obtenirContextePersistance().persist(client);
    }
    
    

    
}
