package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static Gui.Main.currentClient;


public  class MenuPrincipaleController {
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
    private AnchorPane centerStackPane;

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
        Main.stage.close();
        try{
            Main.showPages("ModifierPortefeuille");

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

    public void initialize(){
        consommation.setStyle("-fx-background-color:gray;");
        portefeuille.setStyle("-fx-background-color:gray;");
        pointDeFourniture.setStyle("-fx-background-color:gray;");
        invitations.setStyle("-fx-background-color:gray;");
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