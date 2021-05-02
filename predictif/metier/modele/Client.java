/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.PredictIF.predictif.metier.modele;
import fr.insalyon.dasi.PredictIF.predictif.util.AstroNetApi;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author alex
 */
@Entity
public class Client extends Utilisateur {
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;    
    private String adressePostale;
    @Embedded
    private ProfilAstral profilAstral; 
    @OneToMany(mappedBy="client")
    private List <Consultation> historique;
    
    public Client() {
    }

    /**
     *
     * @param dateNaissance
     * @param adressePostale
     * @throws ParseException
     */
    public Client(String nom, String prenom, String numerotel, String mdp, String adresseElectronique, Date dateNaissance, String adressePostale) throws ParseException, IOException {
        super(nom, prenom, numerotel, mdp, adresseElectronique);
        this.dateNaissance = dateNaissance;
        this.adressePostale = adressePostale;
        //this.profilAstral = null;
        //this.historique = null;
    }
    
    public void addConsultation(Consultation consult)
    {
        this.historique.add(consult);   
    }

   
    public List<Consultation> getHistorique() {
        return historique;
    }

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }
    
    
    

}
