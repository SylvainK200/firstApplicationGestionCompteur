package controller.controllerInterfaces;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
// ici quand il appyer sur le button modifier
// dans le menu principale de portefeuille
public class ModifierPortefeuilleController  implements  Initializable{

    @FXML
    private MenuButton ButtonCompte;

    @FXML
    private Label LabelNomClient;

    @FXML
    private MenuItem MenuParametres;

    @FXML
    private MenuItem MenuSeDeconnecter;

    @FXML
    private TextField TextAdresse;

    @FXML
    private TextField TextNom;

    @FXML
    private Button button_annuler;

    @FXML
    private Button button_valider;

    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void valider(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}
