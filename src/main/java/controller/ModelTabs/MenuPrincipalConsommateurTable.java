package controller.ModelTabs;

import Gui.Controllers.Methods.GeneralMethods;
import Gui.Controllers.Methods.GeneralMethodsImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class MenuPrincipalConsommateurTable {
    public String ean_18;
    public String type_energie;
    public String type_compteur;
    public String Fournisseur;
    public String date_affectation;
    public String date_cloture;
    public String wallet;
    public String consommation;
    public boolean allocated;
    public String cloture;
    GeneralMethods generalMethods = new GeneralMethodsImpl();
    public MenuPrincipalConsommateurTable(JSONObject contract_supply_point ) {
        System.out.println("debut ligne");

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(Objects.isNull(contract_supply_point.get("dateCloture")));
        if (!Objects.nonNull(contract_supply_point.get("dateCloture"))){
            cloture  = df.format(contract_supply_point.getLong("dateCloture"));

        }else {
            cloture = "non defini";
        }
        ean_18 = contract_supply_point.getJSONObject("supplyPoint").getString("ean_18");
        type_energie = contract_supply_point.getJSONObject("supplyPoint").getString("energy");
        type_compteur = contract_supply_point.getString("meter_type");
        Fournisseur = contract_supply_point.getJSONObject("provider").getString("company_name");
        date_affectation = df.format(contract_supply_point.getLong("date_begin"));
        date_cloture = df.format(contract_supply_point.getLong("date_end"));
        if (!Objects.nonNull(contract_supply_point.get("wallet"))){
            wallet = contract_supply_point.getJSONObject("wallet").getString("name");
        }
        else{
            wallet = "non defini";
        }
        long id = contract_supply_point.getJSONObject("supplyPoint").getLong("id");

        JSONArray consommations = generalMethods.find("consommationValue/historiqueRecent/"+id);
        if (!Objects.nonNull(consommations)){
            consommation = consommations.getJSONObject(0).getDouble("value")+"";
        }else {
            consommation = "0";
        }
    }

    public String getCloture(){
        return cloture;
    }
    public boolean isAllocated() {
        return allocated;
    }

    public String getConsommation(){
        return consommation;
    }
    public String getWallet(){
        return wallet;
    }
    public String getEan_18() {
        return ean_18;
    }

    public void setEan_18(String ean_18) {
        this.ean_18 = ean_18;
    }

    public String getType_energie() {
        return type_energie;
    }

    public void setType_energie(String type_energie) {
        this.type_energie = type_energie;
    }

    public String getType_compteur() {
        return type_compteur;
    }

    public void setType_compteur(String type_compteur) {
        this.type_compteur = type_compteur;
    }

    public String getFournisseur() {
        return Fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        Fournisseur = fournisseur;
    }

    public String getDate_affectation() {
        return date_affectation;
    }

    public void setDate_affectation(String date_affectation) {
        this.date_affectation = date_affectation;
    }

    public String getDate_cloture() {
        return date_cloture;
    }

    public void setDate_cloture(String date_cloture) {
        this.date_cloture = date_cloture;
    }
}
