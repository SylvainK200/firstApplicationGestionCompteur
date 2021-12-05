package controller.controllerInterfaces;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public  class InvitationController  implements Initializable {

    @FXML
    private Label LabelNomClient;

    @FXML
    private MenuButton ButtonCompte;

    @FXML
    private MenuItem MenuParametres;

    @FXML
    private MenuItem MenuSeDeconnecter;

    @FXML
    private Button ButtonAjouterInvitation;

    @FXML
    private Button ButtonSupprimerInvitation;

    @FXML
    private Button ButtonModificationnvitation;

    @FXML
    private Button ButtonSupprimerInvitationRecu;
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    @FXML
    void ajouterInvitation(ActionEvent event) {

    }

    @FXML
    void modifierInvitation(ActionEvent event) {

    }

    @FXML
    void supprimerInvitation(ActionEvent event) {

    }

}