/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.predictif.metier.modele;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "Client")
public class Client extends Utilisateur {
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String adressePostale;

    public Client() {
    }

    /**
     *
     * @param dateNaissance
     * @param adressePostale
     * @throws ParseException
     */
    public Client(String nom, String prenom, String numerotel, String mdp, String adresseElectronique, String dateNaissance, String adressePostale) throws ParseException {
        super(nom, prenom, numerotel, mdp, adresseElectronique);
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sf.parse(dateNaissance);
        //System.out.println(sf.format(d));
        this.dateNaissance = d;
        this.adressePostale = adressePostale;
    }

    
}
