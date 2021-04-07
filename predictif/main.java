package vue;


import dao.JpaUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import metier.modele.Client;
import metier.modele.Medium;
import metier.modele.Utilisateur;
import metier.service.Service;


public class Main {

    public static void main(String[] args) throws ParseException {
        
        JpaUtil.init();
        
        testInscriptionClient();
        testInitialisationEmploye();
        testAuthentificationUtilisateur();
        testDemandeConsultation();
        
    
        JpaUtil.destroy();
    }
    
    public static SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date d;
    
     public static void initialiserUtilisateur() throws ParseException{
        Client client;
        Date d = DateFormat.parse("19/12/2000");
        client = new Client("alami", "meryem", "meryem.alami00@gmail.com", "meryemalami", "0651815318", d, "Einstein");
            Service serviceClient = new Service();
            serviceClient.inscriptionClient(client);    
            
    }
    
    public static void  testInscriptionClient() throws ParseException{
        //Inscription Client
        Date d = DateFormat.parse("19/12/2000");
        Client client = new Client("alami", "meryem", "meryem.alami@gmail.com", "meryemalami", "0651815318", d, "Einstein");
        Service serviceClient = new Service();
        client = serviceClient.inscriptionClient(client);

        if (client == null) { 
            System.out.println("Echec inscription");
            
        }else {
            System.out.println("Succès inscription\n");
            System.out.println(" nom="+client.getNom()+"; prénom="+client.getPrenom()+"; mail="+client.getMail()+"; motDePasse="+client.getMotDePasse()+ "; numeroTel="+client.getNumeroTel()+"; dateNaissance="+client.getDateNaissance()+ "a bien été enregistré");
        }    
    }
    
    public static void testInitialisationEmploye(){
        //Inscription employe
        Service serviceEmploye = new Service();
        serviceEmploye.creationEmploye();  
    }
   
    
    public static void testDemandeConsultation() throws ParseException{
        Service serviceConsultation = new Service();
        Date d = DateFormat.parse("19/12/2000");
        Date d1 = DateFormat.parse("10/12/2000");

        Client client = new Client("alami", "meryem", "meryem.alamiii@gmail.com", "meryemalami", "0651815318", d, "Einstein");
        Client client1 = new Client("tram", "hugo", "hugo.tram@gmail.com", "mdp", "0651233318", d1 , "Einstein");

        Medium medium= new Medium("Mme Irma","F","blabla");
        
        serviceConsultation.demanderConsultation(client,medium);
        serviceConsultation.demanderConsultation(client1,medium);

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
