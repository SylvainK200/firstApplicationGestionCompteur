package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

import static Gui.Main.currentClient;


// normalement ici quand l'utilisateur se connecter affiche directement
// ce menu principale et si y a qulqu'un envoyer une invitation
// directement doit être afficher un message
// avec message de confirmation si oui ou non  il accepte l'invitation
//
public  class MenuPrincipaleController  implements Initializable {
    GeneralMethods generalMethods = new GeneralMethodsImpl();
    @FXML
    private MenuItem deconnecter;
    @FXML
    private MenuItem parametres;
    @FXML
    private Label LabelNomClient;

    @FXML
    private Button acceuil;

    @FXML
    private StackPane centerStackPane;

    @FXML
    private Button consommation;

    @FXML
    private Button invitations;

    @FXML
    private Button pointDeFourniture;

    @FXML
    private Button portefeuille;

    @FXML
    void goToAcceuil(ActionEvent event) {
        acceuil.setStyle("-fx-background-color:white;");
        try{
            Parent fxml  = Main.loadPane("MenuPrincipalePortefeuille");
            centerStackPane.getChildren().removeAll();
            centerStackPane.getChildren().addAll(fxml);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToConsommation(ActionEvent event) {
        consommation.setStyle("-fx-background-color:white;");
        try{
            Parent fxml  = Main.loadPane("Consommation");
            centerStackPane.getChildren().removeAll();
            centerStackPane.getChildren().addAll(fxml);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToInvitation(ActionEvent event) {
        invitations.setStyle("-fx-background-color:white;");
        try{
            Parent fxml  = Main.loadPane("Invitation");
            centerStackPane.getChildren().removeAll();
            centerStackPane.getChildren().addAll(fxml);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToPointDeFourniture(ActionEvent event) {
        pointDeFourniture.setStyle("-fx-background-color:white;");
        try{
            Parent fxml  = Main.loadPane("MenuPointDeFourniture");
            centerStackPane.getChildren().removeAll();
            centerStackPane.getChildren().addAll(fxml);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToPortefeuille(ActionEvent event) {
        portefeuille.setStyle("-fx-background-color:white;");
        try{
            Parent fxml  = Main.loadPane("ModifierPortefeuille");
            centerStackPane.getChildren().removeAll();
            centerStackPane.getChildren().addAll(fxml);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void seDeconnecter(){
        Main.stage.close();
        Main.showPages("login.fxml");
    }
    @FXML
    void goToParametres(){

    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        LabelNomClient.setText(currentClient.getString("name"));
        acceuil.setStyle("-fx-background-color:white;");
        try{
            Parent fxml  = Main.loadPane("MenuPrincipalePortefeuille");
            centerStackPane.getChildren().removeAll();
            centerStackPane.getChildren().addAll(fxml);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}