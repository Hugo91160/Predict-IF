/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.metier;

import fr.insalyon.dasi.PredictIF.predictif.dao.ClientDao;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.*;
import fr.insalyon.dasi.PredictIF.predictif.dao.ConsultationDao;
import fr.insalyon.dasi.PredictIF.predictif.dao.EmployeDao;
import fr.insalyon.dasi.PredictIF.predictif.dao.JpaUtil;
import fr.insalyon.dasi.PredictIF.predictif.dao.MediumDao;
import fr.insalyon.dasi.PredictIF.predictif.dao.UtilisateurDao;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.ProfilAstral;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Utilisateur;
import fr.insalyon.dasi.PredictIF.predictif.util.AstroNetApi;
import fr.insalyon.dasi.PredictIF.predictif.util.Message;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
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
             //dans le cas où le client a déjà un mail dans la base
            
            //envoyer le message erreur
            StringWriter corps = new StringWriter();
            PrintWriter mailWriter = new PrintWriter(corps);
            mailWriter.println("Bonjour "+client.getPrenom()+", votre inscription au service PREDICT’IF a malencontreusement échoué..");
            mailWriter.println("Merci de recommencer ultérieurement.");
            Message.envoyerMail("contact@predict.if", client.getAdresseElectronique(), "Bienvenue chez PREDICT'IF", corps.toString());
            client = null;
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
        

        return med;
        
    }
    
    public Consultation demanderConsultation(Client client, Medium medium)
    {
        EmployeDao employeDao = new EmployeDao();
        MediumDao mediumDao = new MediumDao();
        Consultation consultation = new Consultation();
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            //Pour récupérer la liste des employées disponibles et avec le bon genre
            List <Employe> listeEmploye = employeDao.filtrerEmployer(medium); 
            
            //Si la liste est vide
            if(listeEmploye.isEmpty()){
                System.out.println("Le médium que vous avez demandé n'est pas disponible. Veuiller réessayer plus tard");
                consultation = null;
            }else{
                Employe e = listeEmploye.get(0); //Premier employe de la liste = celui qui a le moins de consultations
                e.setDisponibilite(false);
                //Créer la consultation
                Date dateDebut = new Date();
                consultation = new Consultation( dateDebut, client, e, medium);
                medium.incrementeConsultation();
                mediumDao.actualise(medium);
                
                System.out.println("Vous avez une consultation avec " +medium.getDenomination());
                e.incrementeConsultation();
                employeDao.actualise(e);
                System.out.println("Son nb de consultation est " +(e.getNbConsultation()));
                System.out.println(" L'employé correspondant est " +(e.getNom()));
                }
                JpaUtil.validerTransaction();
            
        }
        catch (Exception ex)
        {
            //ex.printStackTrace();
            JpaUtil.annulerTransaction();
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
            return consultation;
        }
        
        //return consultation;
       
    }
    
    public Consultation finirConsultation(Consultation consultation, String commentaire)
    {
        ConsultationDao consultationDao = new ConsultationDao();
        EmployeDao employeDao = new EmployeDao();
        ClientDao clientDao = new ClientDao();
        
        Employe e = consultation.getEmploye();
        Client c = consultation.getClient();
        
        consultation.setCommentaire(commentaire);
        Date dateFin = new Date();
        consultation.setDateFin(dateFin);

       
        try
        {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            e.setDisponibilite(true);
            employeDao.actualise(e);
            consultationDao.creerConsultation(consultation); //persist dans la bdd la consultation
            c.setHistorique(consultationDao.getHistorique(c));
            clientDao.actualise(c);
            JpaUtil.validerTransaction();
        }
        catch (Exception ex)
        {
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
    
    public List<Medium> top5(){

        MediumDao mediumDao = new MediumDao();
        List <Medium> ListeMedium = null;

        try {

            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            ListeMedium = mediumDao.getTop5();

        }
        catch (Exception ex)
        {
            JpaUtil.annulerTransaction();
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        return ListeMedium;
    }

}