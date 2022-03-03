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

import javax.swing.*;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public  class AjouterPointFournitureController  implements Initializable {
    @FXML
    private TextField TextEAN;

    @FXML
    private ComboBox<String> comboboxPorteFeuille;

    @FXML
    private Button ButtonAnnuler;

    @FXML
    private Label LabelNomClient;

    @FXML
    private MenuButton ButtonCompte;

    @FXML
    private MenuItem MenuParametres;

    @FXML
    private MenuItem MenuSeDeconnecter;

    @FXML
    private Button ButtonAjouter;

    @FXML
    private Button ButtonRetour;
    JSONObject pointDeFourniture;

    JSONArray wallets;
    GeneralMethods generalMethods = new GeneralMethodsImpl();
    @Override
    public void initialize(URL url, ResourceBundle rb){
        wallets= generalMethods.find("wallet/identifiant/"+Main.currentClient.getString("identifiant"));
        if (wallets!=null){
            System.out.println(wallets.length());
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
            JOptionPane.showMessageDialog(null,"ce point de fourniture avec ean = "+ ean +"n'existe pas dans le systeme");
        }

    }


    @FXML
    void annuler(ActionEvent event) {
        comboboxPorteFeuille.setValue("");
    }

    @FXML
    void retour(ActionEvent event) {
        Main.newStage.close();
    }
}