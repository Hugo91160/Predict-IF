/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.metier.service;

import fr.insalyon.dasi.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.predictif.dao.ConsultationDao;
import fr.insalyon.dasi.predictif.dao.JpaUtil;

/**
 *
 * @author alex
 */
public class ServiceConsultation {
    public Consultation organisationConsultation(Consultation consultation)
    {
        ConsultationDao consultationDao = new ConsultationDao();
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            consultationDao.organisationConsultation(consultation);
            JpaUtil.validerTransaction();
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            consultation = null;
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        return consultation;
    }

    
}
