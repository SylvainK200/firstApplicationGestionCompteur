package controller.ModelTabs;

import Gui.Main;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class InvitationTable {
    public long id;
    public String destinataire;
    public String portefeuille;
    public String acces;
    public String statut;
    public String date_envoie;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    public InvitationTable(JSONObject invitation, boolean f){
        id = invitation.getLong("id");
        if (f){
            destinataire = invitation.getString("name");
            date_envoie= df.format(invitation.getLong("dateInvitation"));
        }
        else{
            destinataire = invitation.getJSONObject("user").getString("name");
            date_envoie = df.format(invitation.getLong("dateValidation"));
        }
        portefeuille = invitation.getJSONObject("wallet").getString("name");
        acces = invitation.getString("droitAcces");
        statut = invitation.getString("decision");
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
