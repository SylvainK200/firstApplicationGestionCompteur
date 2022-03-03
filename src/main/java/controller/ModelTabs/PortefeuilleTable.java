package controller.ModelTabs;

import Gui.Main;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PortefeuilleTable {

    public String nom;
    public String adresse;
    public int nombreCompteur;

    public  PortefeuilleTable(JSONObject wallet){
        nom = wallet.getString("name");
        try{
            JSONObject home = wallet.getJSONObject("home");
            if (!home.isEmpty()){

            adresse = home.getString("city")+"-"+ home.getString("street");
            }
        }catch (Exception e ){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,
                    "Proble avec l'objet home du JSONObject dans le fichier PortefeuilleTable.java\n\n"
                            +e.getMessage());
        }

        // Creer route pour avoir le nombre de contrat pour un porte feuille
        nombreCompteur = wallet.getJSONArray("pointFournitures").length();
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getNombreCompteur() {
        return nombreCompteur;
    }
}
