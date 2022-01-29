package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import controller.ModelTabs.ConsommationTable;
import controller.ModelTabs.InvitationTable;
import controller.ModelTabs.PointDeFournitureTable;
import controller.ModelTabs.PortefeuilleTable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Gui.Main.*;
import static Gui.Main.ajouterInteractionAuClic;


public  class MenuPrincipaleController {
    GeneralMethods generalMethods = new GeneralMethodsImpl();

    @FXML
    private Label LabelNomClient;

    @FXML
    private Tab tabAccueil;

    @FXML
    private Tab tabConsommation;

    @FXML
    private Tab tabInvitation;

    @FXML
    private Tab tabPointDeFourniture;

    @FXML
    private Tab tabPortefeuille;
    @FXML
    void seDeconnecter(){
        Main.stage.close();
        Main.showPages("login.fxml");
    }
    @FXML
    void goToParametres(){

    }

    public void initialize(){
        LabelNomClient.setText(currentClient.getString("name"));
        FXMLLoader loader = new FXMLLoader();
        try {
            AnchorPane anch1 = loader.load(getClass().getResource("/interfaces/MenuPointFourniture.fxml"));
            tabAccueil.setContent(anch1);

        }catch (IOException io) {
            System.out.println( " Fichier introuvable");
        }
        loader = new FXMLLoader();
        try{
            AnchorPane anch = loader.load(getClass().getResource("/interfaces/Consommation.fxml"));
            tabConsommation.setContent(anch);
        }catch (IOException io){
            System.out.println(" Fichier consommation introuvable");
        }

        loader = new FXMLLoader();
        try{
            AnchorPane anch = loader.load(getClass().getResource("/interfaces/ModifierPointFourniture.fxml"));
            tabPointDeFourniture.setContent(anch);
        }catch (IOException io){
            System.out.println("Fichier de modification");
        }
    }
    @FXML
    void presenterAccueil(ActionEvent event) {

    }

    @FXML
    void presenterConsommation(ActionEvent event) {

    }

    @FXML
    void presenterInvitation(ActionEvent event) {

    }

    @FXML
    void presenterPointDeFourniture(ActionEvent event) {

    }

    @FXML
    void presenterPortefeuille(ActionEvent event) {

    }
}