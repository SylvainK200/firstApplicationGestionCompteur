package controller.ModelTabs;

import Gui.Main;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConsommationTable {
    public String type_energie;
    public String type_compteur;
    public String consommateur;
    public String Fournisseur;
    public String dateCloture;
    public String ean;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    public ConsommationTable(JSONObject contract_supply_point){
        type_energie = contract_supply_point.getJSONObject("supplyPoint").getString("energy");
        type_compteur = "mono-horaire";
        consommateur = Main.currentClient.getString("name");
        Fournisseur = contract_supply_point.getJSONObject("provider").getString("company_name");
        dateCloture = df.format(contract_supply_point.getLong("date_end"));
        ean = contract_supply_point.getJSONObject("supplyPoint").getString("ean_18");
    }

    public String getEan() {
        return ean;
    }

    public String getType_energie() {
        return type_energie;
    }

    public String getType_compteur() {
        return type_compteur;
    }

    public String getConsommateur() {
        return consommateur;
    }

    public String getFournisseur() {
        return Fournisseur;
    }

    public String getDateCloture() {
        return dateCloture;
    }
}
