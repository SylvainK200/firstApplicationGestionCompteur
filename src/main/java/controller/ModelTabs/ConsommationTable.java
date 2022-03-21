package controller.ModelTabs;

import Gui.Main;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Classe utilisee pour les tablesviews dans l'onglet Consommation
 */

public class ConsommationTable {
    public String type_energie;
    public String type_compteur;
    public String consommateur;
    public String Fournisseur;
    public String date_lecture;
    public String ean;
    public String name;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    public String quantiteConsommee;
    public String numero_compteur;
    public ConsommationTable(JSONObject historical){
        name= historical.getJSONObject("supplyPoint").getString("name");
        type_energie =historical.getJSONObject("supplyPoint").getString("energy");
        type_compteur = "mono-horaire";
        consommateur = Main.currentClient.getString("name");
        Fournisseur = historical.getJSONObject("supplyPoint").getJSONObject("pointFourniture").getJSONObject("provider").getString("company_name");
        date_lecture= historical.getString("date");
        ean = historical.getJSONObject("supplyPoint").getString("ean_18");
        quantiteConsommee = historical.getDouble("consommation")+"";
        numero_compteur = historical.getJSONObject("supplyPoint").getLong("id")+"";
    }

    public String getQuantiteConsommee() {
        return quantiteConsommee;
    }

    public String getNumero_compteur() {
        return numero_compteur;
    }
    public String getName(){
        return this.name;
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

    public String getDate_lecture() {
        return date_lecture;
    }
}
