/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.metier.service;

import fr.insalyon.dasi.predictif.metier.modele.*;
import fr.insalyon.dasi.predictif.dao.ClientDao;
import fr.insalyon.dasi.predictif.dao.ConsultationDao;
import fr.insalyon.dasi.predictif.dao.JpaUtil;
import fr.insalyon.dasi.predictif.dao.UtilisateurDao;
import fr.insalyon.dasi.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.predictif.metier.modele.Employe;
import fr.insalyon.dasi.predictif.metier.modele.Medium;
import fr.insalyon.dasi.predictif.metier.modele.ProfilAstral;
import fr.insalyon.dasi.predictif.metier.modele.Utilisateur;
import fr.insalyon.dasi.predictif.util.AstroNetApi;
import fr.insalyon.dasi.predictif.util.Message;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class Service {
    public Client inscriptionClient(Client client)
    {
        UtilisateurDao userDao = new UtilisateurDao();
        
        try {
            //Récupération du profil astral : 
            AstroNetApi astroApi = new AstroNetApi();
            List<String> list = astroApi.getProfil(client.getPrenom(), client.getDateNaissance());
            ProfilAstral profilAstral = new ProfilAstral(list.get(0), list.get(1), list.get(2), list.get(3));
            client.setProfilAstral(profilAstral);
            
            //Création dans la bdd : 
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            userDao.creation(client);
            JpaUtil.validerTransaction();
            
            //envoie le message positif
            StringWriter corps = new StringWriter();
            PrintWriter mailWriter = new PrintWriter(corps);

            mailWriter.println("Bonjour "+client.getPrenom()+", nous vous confirmons votre inscriptition au service PREDICT'IF. Rendez-");
            mailWriter.println("vous  vite  sur  notre  site  pour  consulter  votre profil  astrologique  et  profiter  des  dons");
            mailWriter.println("incroyables de nos mediums");
        
            Message.envoyerMail("contact@predict.if", client.getAdresseElectronique(), "Bienvenue chez PREDICT'IF", corps.toString());
            
            
        }
        catch (Exception ex)
        {
            //ex.printStackTrace();
            JpaUtil.annulerTransaction();
            client = null; //dans le cas où le client a déjà un mail dans la base
            
            //envoyer le message erreur
            StringWriter corps = new StringWriter();
            PrintWriter mailWriter = new PrintWriter(corps);
            mailWriter.println("Bonjour "+client.getPrenom()+", votre inscription au service PREDICT’IF a malencontreusement échoué..");
            mailWriter.println("Merci de recommencer ultérieurement.");

            Message.envoyerMail("contact@predict.if", client.getAdresseElectronique(), "Bienvenue chez PREDICT'IF", corps.toString());
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        return client;
    }
    
    public Employe initialisationEmploye(Employe emp)
    {
        UtilisateurDao userDao = new UtilisateurDao();
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            userDao.creation(emp);
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
    
    public Medium initialisationMedium(Medium med)
    {
        MediumDao medDao = new MediumDao();
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            medDao.creation(med);
            JpaUtil.validerTransaction();
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            med = null; //dans le cas où le client a déjà un mail dans la base
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        if(med instanceof Spirite)
        {
            Spirite spirite = (Spirite) med 
        }
        return med;
        
    }
    
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
    
    
    public List<String> getPredictions(String couleur, String animal, int amour, int sante, int travail) throws IOException {
        AstroNetApi astroApi = new AstroNetApi();
        return astroApi.getPredictions(couleur, animal, amour, sante, travail);
    }
    
    protected UtilisateurDao utilisateurDao = new UtilisateurDao();
    
    public Utilisateur authentifierUtilisateur(String mail, String motDePasse) {
        Utilisateur resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Utilisateur user = utilisateurDao.chercherParMail(mail);
            if (user != null) {
                // Vérification du mot de passe
                if (user.getMdp().equals(motDePasse)) {
                    resultat = user;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

}
