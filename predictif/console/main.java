/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.console;

import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Cartomancien;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Client;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import fr.insalyon.dasi.PredictIF.predictif.metier.service.Service;
import fr.insalyon.dasi.PredictIF.predictif.dao.JpaUtil;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Utilisateur;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alex
 */
public class main {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws ParseException, IOException {
        JpaUtil.init();
        testInscriptionClient();
        testInitialisationEmploye();
        testAuthentificationUtilisateur();
        //testerOrganisationConsultation();
        JpaUtil.destroy();
    }
    
    
    public static void testInscriptionClient() throws ParseException, IOException
    {
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sf.parse("25/05/2000");
        Client client = new Client("Alexandre2", "Dumarquez", "0611345895", "mdp", "a@gmail.com", d, "rue Lyon");
        Service serviceClient = new Service();
        client = serviceClient.inscriptionClient(client);
        
        if(client == null) //si le client revenvoyé par inscription client
        {
            System.out.println("Erreur dans l'inscription du client");
        } else 
        {
            System.out.printf("Client bien inscrit");
        }
        
    }
    
    public static void testInitialisationEmploye() {
        Employe emp = new Employe("Alexandre3", "Dumarquez", "0611345895", "mdp2", "a2@gmail.com", false);
        Service serviceEmploye = new Service();
        emp = serviceEmploye.initialisationEmploye(emp);
        
        if(emp == null) //si le client revenvoyé par inscription client
        {
            System.out.println("Erreur dans l'inscription du emp");
        } else 
        {
            System.out.printf("emp bien inscrit");
        }
    }
    
    public static void testerOrganisationConsultation() throws ParseException, IOException
    {
        Employe emp = new Employe("Alexandre3", "Dumarquez", "0611345895", "mdp", "a@gmail.com", false);
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sf.parse("25/05/2000");
        Client client = new Client("Alexandre2", "Dumarquez", "0611345895", "mdp2", "a2@gmail.com", d, "rue Lyon");
        Cartomancien carto = new Cartomancien("Irma", "F", "Endora");
        /** TODO
         * tester la consultation en l'ajoutant dans la BDD
         */
    }
    
    public static void testAuthentificationUtilisateur()
    {
        //Cas bon :
        Service service = new Service();
        Utilisateur user = service.authentifierUtilisateur("a@gmail.com", "mdp");//client
        if(user instanceof Client)
        {
            Client client = (Client) user;
            System.out.println("*********"+user.getPrenom());
        }
        
        service.authentifierUtilisateur("a2@gmail.com", "mdp2"); //employe
        
        //Cas erreurs : 
        if(service.authentifierUtilisateur("HUGO", "yo") == null ) //fausse addresse elec
        {
            System.out.println("Erreur d'authentification, veuillez réessayer.");
        }
        service.authentifierUtilisateur("a@gmail.com", "YO"); //mdp erroné
        
        
    }
}
