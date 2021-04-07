/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.metier.service;

import fr.insalyon.dasi.predictif.metier.modele.Client;
import fr.insalyon.dasi.predictif.dao.ClientDao;
import fr.insalyon.dasi.predictif.dao.JpaUtil;
import fr.insalyon.dasi.predictif.dao.UtilisateurDao;

/**
 *
 * @author alex
 */
public class ServiceClient {
    public Client inscriptionClient(Client client)
    {
        UtilisateurDao userDao = new UtilisateurDao();
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            userDao.inscription(client);
            JpaUtil.validerTransaction();
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            client = null; //dans le cas où le client a déjà un mail dans la base
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        return client;
    }

}
