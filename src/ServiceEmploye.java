/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.metier.service;

import fr.insalyon.dasi.predictif.metier.modele.Employe;
import fr.insalyon.dasi.predictif.dao.JpaUtil;
import fr.insalyon.dasi.predictif.dao.UtilisateurDao;

/**
 *
 * @author alex
 */
public class ServiceEmploye {
    public Employe inscriptionEmploye(Employe emp)
    {
        UtilisateurDao userDao = new UtilisateurDao();
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            userDao.inscription(emp);
            JpaUtil.validerTransaction();
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            emp = null; //dans le cas où le client a déjà un mail dans la base
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        return emp;
    }
    
}
