package controller.ModelTabs;

import javafx.scene.control.CheckBox;
import org.json.JSONObject;

/**
 * Classe qui permet de gerer l'affichage des invitations recues par un client
 */
public class InvitationClientTable {

    public String dateInvitation;
    public String droitAcces;
    public String invitant;
    public String maison;
    public String portefeuille;
    public CheckBox select;

    public InvitationClientTable(String dateInvitation, String droitAcces, String invitant, String maison, String portefeuille) {
        this.dateInvitation = dateInvitation;
        this.droitAcces = droitAcces;
        this.invitant = invitant;
        this.maison = maison;
        this.portefeuille = portefeuille;
        this.select = new CheckBox();
    }

    public String getDateInvitation() {
        return dateInvitation;
    }

    public void setDateInvitation(String dateInvitation) {
        this.dateInvitation = dateInvitation;
    }

    public String getDroitAcces() {
        return droitAcces;
    }

    public void setDroitAcces(String droitAcces) {
        this.droitAcces = droitAcces;
    }

    public String getInvitant() {
        return invitant;
    }

    public void setInvitant(String invitant) {
        this.invitant = invitant;
    }

    public String getMaison() {
        return maison;
    }

    public void setMaison(String maison) {
        this.maison = maison;
    }

    public String getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(String portefeuille) {
        this.portefeuille = portefeuille;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
}
