package controller.ModelTabs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AllContract {
    private String nom_client;
    private String num_contrat;
    private String type_contrat;
    private  String debut_contrat;
    private String fin_contrat;
    private String compteur;
    private String energy;
    private String etat_compteur;
    private double meter_rate;
    private double network_manager_cost;
    private double over_tax_rate;
    private double tax_rate;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public AllContract (JSONObject client , JSONObject contract_supply_point){
        nom_client = client.getString("identifiant");
        meter_rate = contract_supply_point.getDouble("meter_rate");
        network_manager_cost = contract_supply_point.getDouble("network_manager_cost");
        tax_rate = contract_supply_point.getDouble("tax_rate");

        num_contrat = contract_supply_point.getString("numero_contract");
        String deb = df.format(contract_supply_point.getLong("date_begin"));
        String fin = df.format(contract_supply_point.getLong("date_end"));
        debut_contrat = deb;
        fin_contrat = fin;
        Object json = contract_supply_point.get("supplyPoint");
        if (json instanceof  JSONObject){
            compteur = ((((JSONObject) json).getString("ean_18")));
            energy = ((JSONObject)json).getString("energy");
            etat_compteur = getEtat_compteur(contract_supply_point);
        }

    }

        private String getEtat_compteur(JSONObject contract_supply_point){
            Object json  = contract_supply_point.get("wallet");
            if (json instanceof  JSONObject){
                if (((JSONObject) json).isEmpty()){
                    return "Non alloue";
                }
                else
                {
                    return "Alloue";
                }
            }
            return "Non alloue";
        }

    public String getNom_client() {
        return nom_client;
    }

    public String getNum_contrat() {
        return num_contrat;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public String getDebut_contrat() {
        return debut_contrat;
    }

    public String getFin_contrat() {
        return fin_contrat;
    }

    public String getCompteur() {
        return compteur;
    }

    public String getEnergy() {
        return energy;
    }

    public String getEtat_compteur() {
        return etat_compteur;
    }

    public double getMeter_rate() {
        return meter_rate;
    }

    public double getNetwork_manager_cost() {
        return network_manager_cost;
    }

    public double getOver_tax_rate() {
        return over_tax_rate;
    }

    public double getTax_rate() {
        return tax_rate;
    }
}
