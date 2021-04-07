/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.metier.service;

import fr.insalyon.dasi.PredictIF.predictif.metier.modele.*;
import fr.insalyon.dasi.PredictIF.predictif.dao.ConsultationDao;
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
	
	public void creationEmploye() {
        Employe employe1 = new Employe("alice", "voyret", "0691276818", "aliceV", "alice.voyret@gmail.com",  true, "F");
        Employe employe2 = new Employe( "adrien","mi", "0651815318", "adri", "adrien.mi@gmail.com", false, "H");
        Employe employe3 = new Employe( "louis", "crapin", "0645678490", "loulou", "louis.crapin@gmail.com", true, "H");  
        Employe employe4 = new Employe( "stephanie", "durand", "0634567091",  "steph", "stephanie.durant@gmail.com", false, "F" );  
        Employe employe5 = new Employe( "diana", "frat", "0759310313", "dianaFrat", "diana.frat@gmail.com", true, "F");  
        
        initialisationEmploye(employe1);
        initialisationEmploye(employe2);
        initialisationEmploye(employe3);
        initialisationEmploye(employe4);
        initialisationEmploye(employe5);
        
         if (employe1 == null || employe2 == null || employe3 == null || employe4 == null || employe5 == null) { 
            System.out.println("Echec inscription");
            
        }else {
            System.out.println("Succès inscription\n");
            
            System.out.println(" nom="+employe1.getNom()+"; prénom="+employe1.getPrenom()+"; mail="+employe1.getAdresseElectronique()+"; motDePasse="+employe1.getMdp()+ "; numeroTel="+employe1.getNumerotel()+ " a bien été enregistré");
            System.out.println(" nom="+employe2.getNom()+"; prénom="+employe2.getPrenom()+"; mail="+employe2.getAdresseElectronique()+"; motDePasse="+employe2.getMdp()+ "; numeroTel="+employe2.getNumerotel()+ " a bien été enregistré");
            System.out.println(" nom="+employe3.getNom()+"; prénom="+employe3.getPrenom()+"; mail="+employe3.getAdresseElectronique()+"; motDePasse="+employe3.getMdp()+ "; numeroTel="+employe3.getNumerotel()+ " a bien été enregistré");
            System.out.println(" nom="+employe4.getNom()+"; prénom="+employe4.getPrenom()+"; mail="+employe4.getAdresseElectronique()+"; motDePasse="+employe4.getMdp()+ "; numeroTel="+employe4.getNumerotel()+ " a bien été enregistré");
            System.out.println(" nom="+employe5.getNom()+"; prénom="+employe5.getPrenom()+"; mail="+employe5.getAdresseElectronique()+"; motDePasse="+employe5.getMdp()+ "; numeroTel="+employe5.getNumerotel()+ " a bien été enregistré");

         }

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
    
	public void demanderConsultation(Client client, Medium medium)
    {
        ConsultationDao consultationDao = new ConsultationDao();
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            //Pour récupérer la liste des employées disponibles et avec le bon genre
            List <Employe> listeEmploye = consultationDao.filtrerEmployer(medium);
            
            //Si la liste est vide
            if(listeEmploye.isEmpty()){
                System.out.println("Le médium que vous avez demandé n'est pas disponible. Veuiller réessayer plus tard");
            }else{
                Employe e = listeEmploye.get(0); //Premier employe de la liste = celui qui a le moins de consultations
                e.setDisponibilite(false);
                //Créer la consultation
                Consultation consultation = new Consultation( Calendar.getInstance().getTime() , client, e, medium);
                System.out.println("Vous avez une consultation avec " +medium.getDenomination());
                e.setNbConsultation(e.getNbConsultation()+1);
                System.out.println("Son nb de consultation est " +(e.getNbConsultation()));
                System.out.println(" L'employé correspondant est " +(e.getNom()));

                }
                JpaUtil.validerTransaction();
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
       
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