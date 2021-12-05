package controller.ModelTabs;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HistoriqueTable {
    private String ean;
    private String type_energy;
    private String date;
    private String consommation;
    private String fournisseur;

    public HistoriqueTable (JSONObject consommationelt, String fournis){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Object supply_point = consommationelt.get("supplyPoint");
        if (supply_point instanceof JSONObject) {
            ean = consommationelt.getJSONObject("supplyPoint").getString("ean_18");
            type_energy = consommationelt.getJSONObject("supplyPoint").getString("energy");
        } else {
            ean = "non defini";
            type_energy = "non defini";
        }
        date = df.format(consommationelt.getLong("date"))+"";
        consommation = consommationelt.getDouble("value")+"";
        fournisseur = fournis;
    }
    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getType_energy() {
        return type_energy;
    }

    public void setType_energy(String type_energy) {
        this.type_energy = type_energy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConsommation() {
        return consommation;
    }

    public void setConsommation(String consommation) {
        this.consommation = consommation;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
}
