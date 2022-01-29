package controller.controllerInterfaces;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public  class ModifierInvitationController  implements Initializable {
    @FXML
    private ComboBox ComboboxPortefeuille;

    @FXML
    private CheckBox CheckBoxLecture;

    @FXML
    private CheckBox CheckBoxEcriture;

    @FXML
    private Label LabelNomClient;

    @FXML
    private MenuButton ButtonCompte;

    @FXML
    private MenuItem MenuParametres;

    @FXML
    private MenuItem MenuSeDeconnecter;

    @FXML
    private Button ButtonValider;

    @FXML
    private Button ButtonAnnuler;

    @FXML
    private Button ButtonRetour;
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    @FXML
    void refuser(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {

    }

    @FXML
    void valider(ActionEvent event) {

    }
}