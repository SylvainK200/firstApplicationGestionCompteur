package controller.controllerInterfaces;

import Gui.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

// ici quand il appyer sur le button modifier
// dans le menu principale de point de fournitures
public  class ModifierPointFournitureController  implements Initializable {
    @FXML
    private TextField TextTypeEnergie;

    @FXML
    private TextField TextEAN;

    @FXML
    private ComboBox ComboboxFournisseur;

    @FXML
    private ComboBox ComboboxTypeContrat;

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
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonRetour);
        Main.newStage.close();
    }

    @FXML
    void valider(ActionEvent event) {

    }
}