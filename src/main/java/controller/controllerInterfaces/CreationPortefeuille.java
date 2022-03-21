package controller.controllerInterfaces;

import Gui.Main;
import com.jfoenix.controls.JFXComboBox;
import controller.ComboBoxClasses.ComboLocalisation;
import controller.ComboBoxClasses.ComboboxHome;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import controller.ModelTabs.PortefeuilleTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static Gui.Main.currentClient;


/**
 * Controlleur de la page de creation d'un portefeuille
 */
public class CreationPortefeuille implements Initializable {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    @FXML
    private TextField codePostal;
    @FXML
    private JFXComboBox<ComboLocalisation> maisons;
    @FXML
    private TextField montantPlafond;
    @FXML
    private TextField nomPortefeuille;
    @FXML
    private TextField numero;
    @FXML
    private TextField rue;

    @FXML
    private TextField typePortefeuille;

    @FXML
    private TextField ville;

    GeneralMethods generalMethods = new GeneralMethodsImpl();
    @FXML
    void annuler(ActionEvent event) {
        codePostal.setText(null);
        montantPlafond.setText(null);
        nomPortefeuille.setText(null);
        numero.setText(null);
        rue.setText(null);
        typePortefeuille.setText(null);
        ville.setText(null);
        maisons.setValue(null);
        maisons.setPromptText("Maison");
    }

    /**
     * Permet d'enregistrer un portefeuille et permet par ailleurs aussi a creer une maison si necessaire
     * @param event
     */

    @FXML
    void enregistrerPortefeuille(ActionEvent event) {
        JSONObject portefeuille = new JSONObject();
        if (maisons.getValue()!=null){
            portefeuille.put("name",nomPortefeuille.getText());
            portefeuille.put("type",typePortefeuille.getText());
            portefeuille.put("amout_ceiling",montantPlafond.getText());
            portefeuille.put("client",currentClient.getString("identifiant"));
            JSONObject home = new JSONObject();
            home.put("id",maisons.getValue().getId());
            home.put("street",maisons.getValue().getStreet());
            home.put("city",maisons.getValue().getCity());
            home.put("postal_code",maisons.getValue().getPostal_code());
            home.put("number",maisons.getValue().getNumber());
            portefeuille.put("home",home);

        }else{
            JSONObject home = new JSONObject();
            home.put("street",rue.getText());
            home.put("number",Integer.parseInt(numero.getText()));
            home.put("city",ville.getText());
            home.put("postal_code",codePostal.getText());
            home.put("user",currentClient);
            JSONObject createdHome = generalMethods.createObject(home,"home");
            portefeuille.put("name",nomPortefeuille.getText());
            portefeuille.put("type",typePortefeuille.getText());
            portefeuille.put("amout_ceiling",montantPlafond.getText());
            portefeuille.put("client",currentClient.getString("identifiant"));
            portefeuille.put("home",createdHome);
        }

        portefeuille.put("visibleByClient",true);
        JSONObject createdPortefeuille = generalMethods.createObject(portefeuille,"wallet");
        Main.logOperation(logger,"Creation reussie de portefeuille","");
        Main.afficherAlert("Creation reussie");
        if (createdPortefeuille !=null && !createdPortefeuille.isEmpty()){
            MenuPrincipaleController.portefeuilles.add(new PortefeuilleTable(createdPortefeuille));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JSONArray homes = generalMethods.find("home/user/"+currentClient.getString("identifiant"));
        for (int i = 0; i<homes.length(); i++){
            maisons.getItems().add(new ComboLocalisation((JSONObject) homes.get(i)));
        }
    }
}