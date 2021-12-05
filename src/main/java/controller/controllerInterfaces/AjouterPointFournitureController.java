package controller.controllerInterfaces;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public  class AjouterPointFournitureController  implements Initializable {
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
    void ajouter(ActionEvent event) {

    }

    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {

    }
}