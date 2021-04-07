/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.dao;

import fr.insalyon.dasi.predictif.metier.modele.Spirite;
import javax.persistence.EntityManager;

/**
 *
 * @author hugol
 */
public class SpiriteDao {
    public void creation(Spirite spirite) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(spirite);
    }
}
