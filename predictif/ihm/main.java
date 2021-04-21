package fr.insalyon.dasi.PredictIF.predictif.ihm;


import fr.insalyon.dasi.PredictIF.predictif.dao.JpaUtil;
import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.*;
import java.io.IOException;
import java.util.List;

class Main {

    public static void main(String[] args) throws ParseException, IOException {
        
        JpaUtil.init();
        
        //testInscriptionClient();
        //testInitialisationEmploye();
        //testAuthentificationUtilisateur();
        //testDemandeConsultation();
        testTop5();
        //testConsultation();
        JpaUtil.destroy();
    }
    
    public static SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date d;
    
     public static void initialiserUtilisateur() throws ParseException, IOException{
        Client client;
        Date d = DateFormat.parse("19/12/2000");
        client = new Client("alami", "meryem", "meryem.alami00@gmail.com", "meryemalami", "0651815318", d, "Einstein");
            Service serviceClient = new Service();
            serviceClient.inscriptionClient(client);    
            
    }
    
    public static void  testInscriptionClient() throws ParseException, IOException{
        //Inscription Client
        Date d = DateFormat.parse("19/12/2000");
        Client client = new Client("alami", "meryem", "meryem.alami@gmail.com", "meryemalami", "0651815318", d, "Einstein");
        Service serviceClient = new Service();
        client = serviceClient.inscriptionClient(client);

        if (client == null) { 
            System.out.println("Echec inscription");
            
        }else {
            System.out.println("Succès inscription\n");
            System.out.println(" nom="+client.getNom()+"; prénom="+client.getPrenom()+"; mail="+client.getAdresseElectronique()+"; motDePasse="+client.getMdp()+ "; numeroTel="+client.getNumerotel()+"; dateNaissance="+client.getDateNaissance()+ "a bien été enregistré");
        }    
    }
    
    public static void testInitialisationEmploye(){
        //Inscription employe
        Service serviceEmploye = new Service();
        //serviceEmploye.creationEmploye();  
    }
   
    
    public static void testDemandeConsultation() throws ParseException, IOException{
        Service serviceConsultation = new Service();
        Date d = DateFormat.parse("19/12/2000");
        Date d1 = DateFormat.parse("10/12/2000");

        Client client = new Client("alami", "meryem", "meryem.alamiii@gmail.com", "meryemalami", "0651815318", d, "Einstein");
        Client client1 = new Client("tram", "hugo", "hugo.tram@gmail.com", "mdp", "0651233318", d1 , "Einstein");

        Cartomancien carto = new Cartomancien("Mme Irma","F","blabla");
        
        serviceConsultation.demanderConsultation(client,carto);
        serviceConsultation.demanderConsultation(client1,carto);

    }
    
    public static void testConsultation() throws IOException, ParseException
    {
        Service service= new Service();
        Date d = DateFormat.parse("19/12/2000");
        Date d1 = DateFormat.parse("10/12/2000");
        Client client = new Client("alami", "meryem", "meryem.alamiii@gmail.com", "meryemalami", "0651815318", d, "Einstein");
        service.inscriptionClient(client);
        Employe employe = new Employe("a","b", "06", "mdp", "mail", true, "F");
        service.initialisationEmploye(employe);
        Cartomancien carto = new Cartomancien("Mme Irma","F","blabla");
        service.initialisationMedium(carto);

        Consultation consultation;
        consultation = service.demanderConsultation(client, carto);
        if(consultation != null)
        {
            String com = "NUL";
            service.finirConsultation(consultation, com);
        }
        else {
            System.out.println("C LA MERDE");
        }
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
    
    public static void testTop5() throws ParseException, IOException{
        Medium medium = new Cartomancien("Mme Irma","F","blabla");
        Medium medium1= new Cartomancien("m1","F","blabla");
        Medium medium2= new Cartomancien("m2","F","blabla");
        Medium medium3= new Cartomancien("m3","F","blabla");
        Medium medium4= new Cartomancien("m4","F","blabla");
        Medium medium5= new Cartomancien("m5","F","blabla");
        Medium medium6= new Cartomancien("m6","F","blabla");
        Medium medium7= new Cartomancien("m7","F","blabla"); 

        Service service = new Service();
        Date d = DateFormat.parse("19/12/2000");
        Client client = new Client("alami", "meryem", "meryem.alamiii@gmail.com", "meryemalami", "0651815318", d, "Einstein");
        Employe employe = new Employe("a","b", "06", "mdp", "mail", true, "F");
        service.initialisationEmploye(employe);
        service.inscriptionClient(client);
        service.initialisationMedium(medium);
        service.initialisationMedium(medium1);
        service.initialisationMedium(medium2);
        service.initialisationMedium(medium3);
        service.initialisationMedium(medium4);
        service.initialisationMedium(medium5);
        service.initialisationMedium(medium6);
        service.initialisationMedium(medium7);
        
        Consultation consultation;
        consultation = service.demanderConsultation(client, medium);
        
        if(consultation != null)
        {
            String com = "NUL";
            service.finirConsultation(consultation, com);
        }
        
        System.out.println(medium.getNombreConsultation());
        
       
        List <Medium> listeTopCinq = service.top5();
        for(int i=0; i<5; i++ ){
            System.out.println(listeTopCinq.get(i).getDenomination());
        }
    }

    
    

   
}
