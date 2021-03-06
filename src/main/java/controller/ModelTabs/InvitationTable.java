package controller.ModelTabs;

import Gui.Main;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Classe permettant de gerer l'affichage des invitations dans les tablesviews
 */
public class InvitationTable {
    public long id;
    public String destinataire;
    public String portefeuille;
    public String acces;
    public String statut;
    public String date_envoie;
    private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
    public InvitationTable(JSONObject invitation, boolean f){
        id = invitation.getLong("id");
        if (f){
            destinataire = invitation.getString("name");
            date_envoie= invitation.getString("dateInvitation");
        }
        else{
            destinataire = invitation.getJSONObject("user").getString("name");
            date_envoie = invitation.getString("dateValidation");
        }
        portefeuille = invitation.getJSONObject("wallet").getString("name");
        acces = invitation.getString("droitAcces");
        statut = invitation.getString("statutInvitation");
    }

    public InvitationTable(InvitationTable inv){
        this.id = inv.id;
        this.acces = inv.acces;
        this.statut = inv.statut;
        this.date_envoie = inv.date_envoie;
        this.portefeuille = inv.portefeuille;
        this.destinataire = inv.destinataire;
    }

    public long getId() {
        return id;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public String getPortefeuille() {
        return portefeuille;
    }

    public String getAcces() {
        return acces;
    }

    public String getStatut() {
        return statut;
    }

    public String getDate_envoie() {
        return date_envoie;
    }

    public DateFormat getDf() {
        return df;
    }
}
