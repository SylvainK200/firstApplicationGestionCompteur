package controller.ModelTabs;

import org.json.JSONObject;

public class PointDeFournitureTable {
    public String nom_portefeuille;
    public String type_energy;
    public String numero_compteur;
    public String statut;
    public String ean;
    public String administrateur;
    public String ouvert;
    public PointDeFournitureTable(JSONObject contract_supply_point){
       nom_portefeuille = contract_supply_point.getJSONObject("wallet").getString("name");
       type_energy = contract_supply_point.getJSONObject("supplyPoint").getString("energy");
       numero_compteur = Long.toString(contract_supply_point.getJSONObject("supplyPoint").getLong("id"));
       ean = contract_supply_point.getJSONObject("supplyPoint").getString("ean_18");
       administrateur = contract_supply_point.getJSONObject("wallet").getString("client");
       statut  = "actif";
       ouvert = "En cours";
    }
    public String getNom_portefeuille() {
        return nom_portefeuille;
    }

    public String getType_energy() {
        return type_energy;
    }

    public String getNumero_compteur() {
        return numero_compteur;
    }

    public String getOuvert() {
        return ouvert;
    }

    public String getEan() {
        return ean;
    }

    public String getAdministrateur() {
        return administrateur;
    }
}
