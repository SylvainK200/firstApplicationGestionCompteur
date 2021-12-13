package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import controller.ModelTabs.PortefeuilleTable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

import static Gui.Main.*;

public  class MenuPrincipalePortefeuilleController  implements Initializable {
    public static  String nomPortefeuilleAModifier;
    @FXML
    private Button   ButtonAjouter;

    @FXML
    private Button ButtonModifier;

    @FXML
    private   Button ButtonSupprimer;

    @FXML
    private TextField TextPorteuille;

    @FXML
    private TableView<PortefeuilleTable> TablesAffichagesPortefeuille;

    @FXML
    private TableColumn<PortefeuilleTable, String> adresse;

    @FXML
    private TableColumn<PortefeuilleTable, String> nom;

    @FXML
    private TableColumn<PortefeuilleTable, String> nombre_compte;
    private FilteredList<PortefeuilleTable> portefeuilleTables;
    GeneralMethods generalMethods = new GeneralMethodsImpl();
    @Override
    public void initialize(URL url, ResourceBundle rb){

        nom.setCellValueFactory(new PropertyValueFactory<PortefeuilleTable,String>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<PortefeuilleTable,String>("addresse"));
        nombre_compte.setCellValueFactory(new PropertyValueFactory<PortefeuilleTable,String>("nombreCompteur"));

        ObservableList items = FXCollections.observableArrayList();
        JSONArray listOfWallets = generalMethods.find("wallet/identifiant/"+ currentClient.getString("identifiant"));

        for (Object j : listOfWallets){
            if (j instanceof JSONObject){
                items.add(new PortefeuilleTable((JSONObject) j));
            }
        }
        portefeuilleTables = new FilteredList<>(items,p->true);
        TextPorteuille.textProperty().addListener((ObservableValue<?extends String> observable,String oldValue,String newValue)->{

            portefeuilleTables.setPredicate(portefeuilleTable->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                if(portefeuilleTable.getNom().toLowerCase().contains(newValue.toLowerCase())||
                        portefeuilleTable.getAdresse().toLowerCase().contains(newValue.toLowerCase())
                        ) return true;

                return false;
            });
            TablesAffichagesPortefeuille.getItems().clear();
            TablesAffichagesPortefeuille.getItems().addAll(portefeuilleTables);
        });
        TablesAffichagesPortefeuille.getItems().clear();
        TablesAffichagesPortefeuille.getItems().addAll(portefeuilleTables);

    }

    @FXML
    void ajouterPOrtefeuille(ActionEvent event) {
        ajouterInteractionAuClic(ButtonAjouter);
        ouvrirNouvellePage("AjouterPointFourniture");
    }

    @FXML
    void modifierPortefeuille(ActionEvent event) {
        ajouterInteractionAuClic(ButtonModifier);
        // si aucun element du tableau n'a ete selectionne
        if(TablesAffichagesPortefeuille.getSelectionModel().getSelectedItem() == null){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Absence de selection element");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez selectionne aucun element dans le tableau ");

            alert1.showAndWait();
        }
        // sinon on peut maintenant ouvrir la page de modification de portefeuille

        else {
            nomPortefeuilleAModifier = TablesAffichagesPortefeuille.getSelectionModel().getSelectedItem().nom;
            ouvrirNouvellePage("ModifierPortefeuille");
        }
    }

    @FXML
    void supprimerPortefeuille(ActionEvent event) {
        ajouterInteractionAuClic(ButtonSupprimer);
        // A revoir si c'est possible de supprimer un portefeuille
    }

}