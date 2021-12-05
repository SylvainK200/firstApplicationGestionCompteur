package controller.controllerInterfaces;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public  class AjouterInvitationController  implements Initializable {
    @FXML
    private Button ButtonAnnuler;

    @FXML
    private MenuButton ButtonCompte;

    @FXML
    private Button ButtonRetour;

    @FXML
    private Button ButtonValider;

    @FXML
    private ComboBox<?> ComboboxEAN;

    @FXML
    private ComboBox<?> ComboboxPortefeuille;

    @FXML
    private Label LabelNomClient;

    @FXML
    private MenuItem MenuParametres;

    @FXML
    private MenuItem MenuSeDeconnecter;

    @FXML
    void ajouterInvitation(ActionEvent event) {

    }

    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}