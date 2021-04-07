/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.dao;

import fr.insalyon.dasi.predictif.metier.modele.Astrologue;
import javax.persistence.EntityManager;

/**
 *
 * @author hugol
 */
public class AstrologueDao {
    public void creation(Astrologue astrologue) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(astrologue);
    }
}
