package fr.insalyon.dasi.PredictIF.predictif.ihm;


import fr.insalyon.dasi.PredictIF.predictif.dao.JpaUtil;
import fr.insalyon.dasi.PredictIF.predictif.dao.MediumDao;
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
        //testTop5();
        testConsultation();
        //testHistorique();
        JpaUtil.destroy();
    }
    
    public static SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date d;
    
     public static void initialiserUtilisateur() throws ParseException, IOException{
        Client client;
        Date d = DateFormat.parse("09/11/1982");
        client = new Client("Sing", "Ainhoa", "0590232772", "ASmdp","asing8183@free.com", d, "Einstein");
        Service serviceClient = new Service();
        serviceClient.inscriptionClient(client);    
            
    }
    
    public static Client testInscriptionClient() throws ParseException, IOException{
        //Inscription Client
        Client client;
        Date d = DateFormat.parse("09/11/1982");
        client = new Client("Sing", "Ainhoa", "0590232772", "ASmdp","asing8183@free.com", d, "Einstein");
        Service serviceClient = new Service();
        client = serviceClient.inscriptionClient(client);

        if (client == null) { 
            System.out.println("Echec inscription");
            
        }else {
            System.out.println("Succès inscription\n");
            System.out.println(" nom="+client.getNom()+"; prénom="+client.getPrenom()+"; mail="+client.getAdresseElectronique()+"; motDePasse="+client.getMdp()+ "; numeroTel="+client.getNumerotel()+"; dateNaissance="+client.getDateNaissance()+ "a bien été enregistré");
        }
        
        return client;
    }
        
    public static void testDemandeConsultation() throws ParseException, IOException{
        Service service = new Service();
        
        Client client;
        Date d = DateFormat.parse("09/11/1982");
        client = new Client("Sing", "Ainhoa", "0590232772", "ASmdp","asing8183@free.fr", d, "Einstein");
        Client client2;
        d = DateFormat.parse("17/11/1994");
        client2 = new Client("Mie", "Romain", "0307363387", "MRmdp","romain.mie@free.fr", d, "Einstein");

        Medium carto = new Cartomancien("Mme Irma","F","Comprenez votre entourage grâce à mes cartes! Résultats rapides.");
        service.initialisationMedium(carto);
        
        carto = service.getMedium("Mme Irma");
        
        System.out.println("***********"+ carto.getPresentation()+"***********");
        service.demanderConsultation(client,carto);
        
        service.demanderConsultation(client2,carto);

    }
    
    public static void testConsultation() throws IOException, ParseException
    {
        Service service= new Service();
        Client client;
        Date d = DateFormat.parse("09/11/1982");
        client = new Client("Sing", "Ainhoa", "0590232772", "ASmdp","asing8183@free.fr", d, "Einstein");
        Client client2;
        d = DateFormat.parse("17/11/1994");
        client2 = new Client("Mie", "Romain", "0307363387", "MRmdp","romain.mie@free.fr", d, "Einstein");
        
        service.inscriptionClient(client);
        service.inscriptionClient(client2);
        
        Employe employe = new Employe("Unlu","Adrien", "0367699654", "UAmdp", "adrien.umlu@laposte.net", true, "F");
        service.initialisationEmploye(employe);
        
        
        Medium carto = new Cartomancien("Mme Irma","F","Comprenez votre entourage grâce à mes cartes! Résultats rapides.");
        service.initialisationMedium(carto);
        carto = service.getMedium("Mme Irma"); //Test pour récupérer un medium depuis la bdd

        Consultation consultation;
        consultation = service.demanderConsultation(client, carto);
        Consultation consultation2;
        consultation2 = service.demanderConsultation(client2, carto);
        if(consultation != null)
        {
            String com = "NUL";
            service.finirConsultation(consultation, com);
        }
        else {
            System.out.println("Erreur, pas de consultation disponible");
        }
    }
   
    public static void testAuthentificationUtilisateur()
    {
        //Cas bon :
        Service service = new Service();
        Employe user = new Employe("Unlu","Adrien", "0367699654", "UAmdp", "adrien.umlu@laposte.net", true, "M");
        service.initialisationEmploye(user);
        
        //Cas de succès
        service.authentifierUtilisateur("adrien.umlu@laposte.net", "UAmdp"); //employe
        
        //Cas erreur : 
        if(service.authentifierUtilisateur("HUGO", "yo") == null ) //fausse addresse elec
        {
            System.out.println("Erreur d'authentification, veuillez réessayer.");
        }
       
        
        
    }
    
    public static void testTop5() throws ParseException, IOException{
        //Déclarations : 
        Medium medium = new Cartomancien("Mme Irma","F","Comprenez votre entourage grâce à mes cartes! Résultats rapides.");
        Medium medium2 = new Spirite("Professeur Tran", "H", "Votre avenir est devant vous : regardons-le ensemble !", "Marc de café, boule de cristal, oreilles de lapin");
        Medium medium3 = new Spirite("Gwanaelle", "F", "Spécialiste des grandes conversions au-delà de toutes les frontières", "Boule de cristal");
        Medium medium4 = new Cartomancien("Endora", "F", "Mes cartes répondront à toutes vos questions personnelles");
        Medium medium5 = new Astrologue("Séréna", "F", "Basée à Champigny-Sur-Marne, Séréna vous révèlera votre avenir pour éclaire votre passé", "Ecole Normale Supérieur d'Astrologie", "2006");
        Medium medium6 = new Astrologue("Mr M", "H", "Avenir, avenir, que nous réserves-tu? N'attendez plus, demandez à me consulter!", " Institut des Nouveaux Savoirs Astrologiques", "2010");
       
        Service service = new Service();
        Consultation consultation;
        
       
        Date d = DateFormat.parse("19/12/2000");
        Client client = new Client("Sing", "Ainhoa", "0590232772", "ASmdp","asing8183@free.fr", d, "Einstein");
        Employe employe = new Employe("Unlu","Adrien", "0367699654", "UAmdp", "adrien.umlu@laposte.net", true, "M");
        
        //Ajout dans la bdd : 
        service.initialisationEmploye(employe);
        service.inscriptionClient(client);
        service.initialisationMedium(medium);
        service.initialisationMedium(medium2);
        service.initialisationMedium(medium3);
        service.initialisationMedium(medium4);
        service.initialisationMedium(medium5);
        service.initialisationMedium(medium6);
        service.initialisationMedium(medium6);
        
        
        consultation = service.demanderConsultation(client, medium);
        
        if(consultation != null)
        {
            String com = "test"; 
            service.finirConsultation(consultation, com);
        }
        
        System.out.println(medium.getNombreConsultation());
        
       
        List <Medium> listeTopCinq = service.top5();
        for(int i=0; i<5; i++ ){
            System.out.println(listeTopCinq.get(i).getDenomination());
        }
    }
    
    public static void testHistorique() throws ParseException, IOException{
        testInscriptionClient();
        Service service= new Service();
        
        Client client;
        client = (Client) service.authentifierUtilisateur("asing8183@free.com", "ASmdp");//client
       
        Employe employe = new Employe("Unlu","Adrien", "0367699654", "UAmdp", "adrien.umlu@laposte.net", true, "M");
        service.initialisationEmploye(employe);
        Medium medium = new Cartomancien("Mme Irma","F","Comprenez votre entourage grâce à mes cartes! Résultats rapides.");
        service.initialisationMedium(medium);
        
        Employe employe1 = new Employe("HONRY", "Matteo", "0482381862", "HMmdp", "matteo.honry@yahoo.com", true, "M");
        service.initialisationEmploye(employe1);
        Medium medium2 = new Spirite("Professeur Tran", "H", "Votre avenir est devant vous : regardons-le ensemble !", "Marc de café, boule de cristal, oreilles de lapin");

        service.initialisationMedium(medium2);
        Employe employe2 = new Employe("CECCANI", "Kevin", "0664426037", "CKmdp", "kevin.ceccani@hotmail.com", true, "M");
        service.initialisationEmploye(employe2);
        Medium medium3 = new Spirite("Gwanaelle", "F", "Spécialiste des grandes conversions au-delà de toutes les frontières", "Boule de cristal");
        service.initialisationMedium(medium3);
        
        Employe employe3 = new Employe("VOYRET", "Alice", "0486856520", "VAmdp", "alice.voyret@hotmail.com", true, "F");
        service.initialisationEmploye(employe3);
        Medium medium5 = new Astrologue("Séréna", "F", "Basée à Champigny-Sur-Marne, Séréna vous révèlera votre avenir pour éclaire votre passé", "Ecole Normale Supérieur d'Astrologie", "2006");
        service.initialisationMedium(medium5);
        
        Consultation consultation;
        consultation = service.demanderConsultation(client, medium);
        if(consultation != null)
        {
            String com = "NUL";
            service.finirConsultation(consultation, com);
        }
        
        Consultation consultation1;
        consultation1 = service.demanderConsultation(client, medium2);
        if(consultation1 != null)
        {
            String com = "NUL";
            service.finirConsultation(consultation1, com);
        }
        
        Consultation consultation2;
        consultation2 = service.demanderConsultation(client, medium3);
        if(consultation2 != null)
        {
            String com = "NUL";
            service.finirConsultation(consultation2, com);
        }
        
        Consultation consultation3;
        consultation3 = service.demanderConsultation(client, medium5);
        if(consultation3 != null)
        {
            String com = "NUL";
            service.finirConsultation(consultation3, com);
        }
        
        
        
        for(int i=0; i<3; i++ ){
            System.out.println(client.getHistorique().get(i).getMedium().getDenomination());
        }
    } 


    
    

   
}
