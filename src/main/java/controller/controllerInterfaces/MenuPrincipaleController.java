package controller.controllerInterfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;


// normalement ici quand l'utilisateur se connecter affiche directement
// ce menu principale et si y a qulqu'un envoyer une invitation
// directement doit être afficher un message
// avec message de confirmation si oui ou non  il accepte l'invitation
//
public  class MenuPrincipaleController  implements Initializable {
    @FXML
    private Label LabelNomClient;

    @FXML
    private Menu acceuil;

    @FXML
    private StackPane centerStackPane;

    @FXML
    private Menu consommation;

    @FXML
    private Menu invitation;

    @FXML
    private Menu pointFourniture;

    @FXML
    private Menu portefeuille;

    @FXML
    void allerAcceuil(ActionEvent event) {

    }

    @FXML
    void allerConsommation(ActionEvent event) {

    }

    @FXML
    void allerInvitation(ActionEvent event) {

    }

    @FXML
    void allerPointFourniture(ActionEvent event) {

    }

    @FXML
    void allerPortefeuille(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try{
            Parent fxml  = FXMLLoader.load(getClass().getResource("interfaces/MenuPrincipalPortefeuille.fxml"));
            centerStackPane.getChildren().removeAll();
            centerStackPane.getChildren().addAll(fxml);
        }catch (Exception e) {

        }
    }
}