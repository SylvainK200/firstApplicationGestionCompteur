package controller.ModelTabs;

import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

import static Gui.Main.extractConsommations;

public class MenuPrincipalTable {
    private String ean_18;
    private double consommation;
    private double cout ;
    private String type_compteur;
    private String date_affectation;
    private String date_cloture;
    private String nameWallet ;
    GeneralMethods generalMethods = new GeneralMethodsImpl();
    public String getNameWallet() {
        return nameWallet;
    }

    public MenuPrincipalTable (JSONObject contract_supply) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        type_compteur = contract_supply.getJSONObject("supplyPoint").getString("energy");
        String deb = df.format(contract_supply.getLong("date_begin"));
        String fin = df.format(contract_supply.getLong("date_end"));
        cout = contract_supply.getDouble("meter_rate");
        nameWallet = "";
        Object json = contract_supply.get("wallet");
        System.out.println(Objects.isNull(json));
        if (Objects.isNull(json)){
            nameWallet = contract_supply.getJSONObject("wallet").getString("name");
        }
        JSONArray consommationValues = generalMethods.find("supplyPoint");
        if (contract_supply.get("supplyPoint") instanceof  JSONObject)
        {
            ArrayList<JSONObject> consommations = extractConsommations(consommationValues,contract_supply.getJSONObject("supplyPoint").getLong("id")) ;
            if (consommations.size()>0){
                consommation = consommations.get(consommations.size()-1).getDouble("value");
            }
            ean_18 = contract_supply.getJSONObject("supplyPoint").getString("ean_18");

        }


            date_affectation = deb;
            date_cloture =fin;

    }

    public String getEan_18() {
        return ean_18;
    }

    public double getConsommation() {
        return consommation;
    }

    public double getCout() {
        return cout;
    }

    public String getType_compteur() {
        return type_compteur;
    }

    public String getDate_affectation() {
        return date_affectation;
    }

    public String getDate_cloture() {
        return date_cloture;
    }
}
