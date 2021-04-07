/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.dao;

import fr.insalyon.dasi.predictif.metier.modele.Employe;

/**
 *
 * @author alex
 */
public class EmployeDao {
    
    public void inscription(Employe employe)
    {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
    
}
