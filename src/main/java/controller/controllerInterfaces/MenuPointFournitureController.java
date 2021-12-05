package controller.controllerInterfaces;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

// pour la supprission normalment doit selection le point de
// fourniture dans le tableau et il appuyer sur suppression
// il affiche un message de confirmation oui ou non


// même chose pour la modification il selectione le point de fourniture
// dans le tableau apres il appuyer sur modifier il afficher
// une interface graphique pour modifier les donnees .

public  class MenuPointFournitureController  implements Initializable {
    @FXML
    private Button ButtonAjouterPointFourniture;

    @FXML
    private MenuButton ButtonCompte;

    @FXML
    private Label LabelNomClient;

    @FXML
    private MenuItem MenuParametres;

    @FXML
    private MenuItem MenuSeDeconnecter;

    @FXML
    private TextField TextEAN;
    @FXML
    private Button buttonModifierPointFourniture;

    @FXML
    private Button buttonSupprimerPointFourniture;

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    @FXML
    void ajoutPointFourniture(ActionEvent event) {

    }

    @FXML
    void modifierPointFourniture(ActionEvent event) {

    }

    @FXML
    void supprimerPointFourniture(ActionEvent event) {

    }
}