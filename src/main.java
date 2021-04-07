/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.console;

import fr.insalyon.dasi.predictif.metier.modele.Cartomancien;
import fr.insalyon.dasi.predictif.metier.modele.Client;
import fr.insalyon.dasi.predictif.metier.modele.Employe;
import fr.insalyon.dasi.predictif.metier.service.ServiceClient;
import fr.insalyon.dasi.predictif.metier.service.ServiceEmploye;
import fr.insalyon.dasi.predictif.dao.JpaUtil;
import java.text.ParseException;

/**
 *
 * @author alex
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        JpaUtil.init();
        testInscriptionClient();
        testInscriptionEmploye();
        testerOrganisationConsultation();
        JpaUtil.destroy();
    }
    
    
    public static void testInscriptionClient() throws ParseException
    {
        Client client = new Client("Alexandre2", "Dumarquez", "0611345895", "mdp", "a@gmail.com", "25/05/2000", "rue Lyon");
        ServiceClient serviceClient = new ServiceClient();
        client = serviceClient.inscriptionClient(client);
        
        if(client == null) //si le client revenvoyé par inscription client
        {
            System.out.println("Erreur dans l'inscription du client");
        } else 
        {
            System.out.printf("Client bien inscrit");
        }
        
    }
    
    public static void testInscriptionEmploye() {
        Employe emp = new Employe("Alexandre3", "Dumarquez", "0611345895", "mdp", "a@gmail.com", false);
        ServiceEmploye serviceEmploye = new ServiceEmploye();
        emp = serviceEmploye.inscriptionEmploye(emp);
        
        if(emp == null) //si le client revenvoyé par inscription client
        {
            System.out.println("Erreur dans l'inscription du emp");
        } else 
        {
            System.out.printf("emp bien inscrit");
        }
    }
    
    public static void testerOrganisationConsultation() throws ParseException
    {
        Employe emp = new Employe("Alexandre3", "Dumarquez", "0611345895", "mdp", "a@gmail.com", false);
        Client client = new Client("Alexandre2", "Dumarquez", "0611345895", "mdp", "a@gmail.com", "25/05/2000", "rue Lyon");
        Cartomancien carto = new Cartomancien("Irma", "F", "Endora");
        /** TODO
         * tester la consultation en l'ajoutant dans la BDD
         */
    }
}
