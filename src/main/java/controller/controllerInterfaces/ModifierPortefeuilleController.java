package controller.controllerInterfaces;

import Gui.Main;
import controller.ComboBoxClasses.ComboLocalisation;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import controller.ModelTabs.PortefeuilleTable;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

import static Gui.Main.currentClient;
import static controller.controllerInterfaces.MenuPrincipaleController.listPortefeuille;
import static controller.controllerInterfaces.MenuPrincipaleController.portefeuilles;

/**
 * Classe controlleur de modification de portefeuille
 */
public class ModifierPortefeuilleController  implements  Initializable{
    private Logger logger = Logger.getLogger(this.getClass().getName());

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

    }

    /**
     * permet de gerer la validation ou l'enregistrement des modifications faites pour un portefeuille
     * @param event
     */
    @FXML
    void valider(ActionEvent event) {
        Main.ajouterInteractionAuClic(button_valider);
        wallet.remove("name");
        wallet.put("name",nom.getText());
        wallet.remove("home");
        JSONObject home = new JSONObject();
        if (localisation.getValue()!=null){
            home.put("id",localisation.getValue().getId());
            home.put("street",localisation.getValue().getStreet());
            home.put("city",localisation.getValue().getCity());
            home.put("postal_code",localisation.getValue().getPostal_code());
            home.put("number",localisation.getValue().getNumber());
            wallet.put("home",home);
            generalMethods.updateObject(wallet,"wallet");

            List<PortefeuilleTable> portefeuilleTables = new ArrayList<>();
            System.out.println("Requete aboutissant aux elts");
            JSONArray elts = generalMethods.find("wallet/identifiant/"+ currentClient.getString("identifiant"));
            Iterator it = elts.iterator();
            while (it.hasNext()){
                portefeuilleTables.add(new PortefeuilleTable((JSONObject) it.next()));
            }
            listPortefeuille.removeAll(listPortefeuille);
            listPortefeuille.addAll(portefeuilleTables);
            portefeuilles.removeAll(portefeuilles);
            portefeuilles.addAll(listPortefeuille);

        }else{
            Main.afficherAlert("Vous n'avez pas choisi la maison correspondante a votre portefeuille");
        }

           }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        LabelNomClient.setText(Main.currentClient.getString("name"));
        nom.setText(MenuPrincipaleController.portefeuilleAModifier.getNom());
        wallet  = generalMethods.findUnique("wallet/name/"+MenuPrincipaleController.portefeuilleAModifier.getNom());
        List<ComboLocalisation> localisations = new ArrayList<>();
        JSONArray homes = generalMethods.find("home/user/"+Main.currentClient.getString("identifiant"));
        for (Object h : homes){
            if (h instanceof JSONObject){
               localisations.add(new ComboLocalisation((JSONObject) h));
            }
        }
        localisation.getItems().addAll(localisations);
    }

}
