/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.dao;

import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;

/**
 *
 * @author alex
 */
public class EmployeDao {
    
    public void creation(Employe employe)
    {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
    
    
    
    
    
}
