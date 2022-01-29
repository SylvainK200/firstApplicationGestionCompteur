package controller.controllerInterfaces;

import Gui.Main;
import controller.ComboBoxClasses.ComboLocalisation;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class ModifierPortefeuilleController  implements  Initializable{

    @FXML
    private Label LabelNomClient;

    @FXML
    private Button button_annuler;

    @FXML
    private Button button_valider;

    @FXML
    private ComboBox<ComboLocalisation> localisation;

    @FXML
    private TextField nom;
    JSONObject wallet ;

    GeneralMethods generalMethods = new GeneralMethodsImpl();
    @FXML
    void annuler(ActionEvent event) {
        Main.ajouterInteractionAuClic(button_annuler);
        nom.setText(MenuPrincipalePortefeuilleController.nomPortefeuilleAModifier);

    }

    @FXML
    void valider(ActionEvent event) {
        Main.ajouterInteractionAuClic(button_valider);
        wallet.remove("name");
        wallet.put("name",nom.getText());
        JSONObject user = wallet.getJSONObject("user");
        wallet.remove("home");
        JSONObject home = new JSONObject();
        home.put("id",localisation.getValue().getId());
        home.put("street",localisation.getValue().getStreet());
        home.put("city",localisation.getValue().getCity());
        home.put("postal_code",localisation.getValue().getPostal_code());
        home.put("number",localisation.getValue().getNumber());
        home.put("user",user);
        wallet.put("home",home);
        generalMethods.updateObject(wallet,"wallet");

    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        LabelNomClient.setText(Main.currentClient.getString("name"));
        wallet  = generalMethods.findUnique("wallet/name/"+MenuPrincipalePortefeuilleController.nomPortefeuilleAModifier);
        nom.setText(wallet.getString("name"));
        List<ComboLocalisation> localisations = new ArrayList<>();
        JSONArray homes = Main.currentClient.getJSONArray("homes");
        for (Object h : homes){
            if (h instanceof JSONObject){
               localisations.add(new ComboLocalisation((JSONObject) h));
            }
        }
        localisation.getItems().addAll(localisations);
    }

}
