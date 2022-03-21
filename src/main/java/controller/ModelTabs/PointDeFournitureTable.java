package controller.ModelTabs;

import org.json.JSONObject;

/**
 * Classe permettant l'affichage dans un tableview des points de fourniture
 */
public class PointDeFournitureTable {
    public String nom_portefeuille;
    public String type_energy;
    public String ean;
    public String administrateur;
    public PointDeFournitureTable(JSONObject pointDeFourniture, JSONObject wallet){
       nom_portefeuille = wallet.getString("name");
       type_energy = pointDeFourniture.getString("energy");
       ean = pointDeFourniture.getString("ean_18");
       administrateur = wallet.getString("client");

    }

    public String getNom_portefeuille() {
        return nom_portefeuille;
    }

    public void setNom_portefeuille(String nom_portefeuille) {
        this.nom_portefeuille = nom_portefeuille;
    }

    public String getType_energy() {
        return type_energy;
    }

    public void setType_energy(String type_energy) {
        this.type_energy = type_energy;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(String administrateur) {
        this.administrateur = administrateur;
    }
}
