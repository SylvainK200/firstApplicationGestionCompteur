package controller.controllerInterfaces;

import Gui.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public  class InvitationController  implements Initializable {

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
        Main.ajouterInteractionAuClic(ButtonAjouterInvitation);

    }

    @FXML
    void modifierInvitation(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonModificationnvitation);

    }

    @FXML
    void supprimerInvitation(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonSupprimerInvitation);

    }

}