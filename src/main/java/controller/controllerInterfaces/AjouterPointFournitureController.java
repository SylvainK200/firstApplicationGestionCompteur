package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Controller permettant de gerer l'ajout des point de fourniture
 */
public  class AjouterPointFournitureController  implements Initializable {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    @FXML
    private TextField TextEAN;

    @FXML
    private ComboBox<String> comboboxPorteFeuille;

    JSONArray wallets;
    GeneralMethods generalMethods = new GeneralMethodsImpl();
    @Override
    public void initialize(URL url, ResourceBundle rb){
        Main.logOperation(logger,"Ouverture de la page de modification d'un point de fourniture","");
        wallets= generalMethods.find("wallet/identifiant/"+Main.currentClient.getString("identifiant"));
        if (wallets!=null){
            for (Object f : wallets){
                if (f instanceof  JSONObject){
                    comboboxPorteFeuille.getItems().add(((JSONObject)f).getString("name"));
                }
            }
        }

    }
    @FXML
    void ajouter(ActionEvent event) {
        String ean = TextEAN.getText();
        JSONObject pointFourniture = generalMethods.findUnique("PointFourniture/ean/"+ean);
        JSONObject j  = new JSONObject() ;
        if (pointFourniture !=null){
            String comboboxPorteFeuille  = this.comboboxPorteFeuille.getValue();
            Iterator<Object> it = wallets.iterator();
            while(it.hasNext()){
                j = (JSONObject) it.next();
                if (j.getString("name").equals(comboboxPorteFeuille)){
                    break;
                }
            }
            pointFourniture.getJSONArray("wallets").put(j);
        }else{
            Main.logOperation(logger,"","ce point de fourniture avec ean = "+ ean +"n'existe pas dans le systeme pour effecture l'ajout");
            Main.afficherAlert("ce point de fourniture avec ean = "+ ean +"n'existe pas dans le systeme");
        }

    }


    @FXML
    void annuler(ActionEvent event) {
        comboboxPorteFeuille.setValue(null);
        comboboxPorteFeuille.setPromptText("portefeuille");
    }

    @FXML
    void retour(ActionEvent event) {
        Main.newStage.close();
    }
}