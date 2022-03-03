package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import controller.ModelTabs.PointDeFournitureTable;
import controller.ModelTabs.PointDeFournitureTable;
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
import java.util.Locale;
import java.util.ResourceBundle;

import static Gui.Main.currentClient;
import static Gui.Main.ouvrirNouvellePage;


public  class MenuPointFournitureController  implements Initializable {

    @FXML
    private Button ButtonAjouterPointFourniture;

    @FXML
    private TableColumn<PointDeFournitureTable, String> administrateur;

    @FXML
    private Button buttonModifierPointFourniture;

    @FXML
    private Button buttonSupprimerPointFourniture;

    @FXML
    private TableColumn<PointDeFournitureTable, String> ean;

    @FXML
    private TableView<PointDeFournitureTable> menuPointFourniture;

    @FXML
    private TableColumn<PointDeFournitureTable, String> nom_portefeuille;

    @FXML
    private TableColumn<PointDeFournitureTable, String> numero_compteur;

    @FXML
    private TableColumn<PointDeFournitureTable, String> ouvert;

    @FXML
    private TextField rechercher;

    @FXML
    private TableColumn<PointDeFournitureTable, String> type_energy;
    public static String pointFournitureAModifier;
    @FXML
    void ajoutPointFourniture(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonAjouterPointFourniture);
        Main.ouvrirNouvellePage("AjouterPointFourniture", "Ajouter Point de fourniture");

    }

    @FXML
    void modifierPointFourniture(ActionEvent event) {
        Main.ajouterInteractionAuClic(buttonModifierPointFourniture);
        if(menuPointFourniture.getSelectionModel().getSelectedItem() == null){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Absence de selection element");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez selectionne aucun element dans le tableau ");

            alert1.showAndWait();
        }
        // sinon on peut maintenant ouvrir la page de modification de point de fourniture

        else {
            pointFournitureAModifier = menuPointFourniture.getSelectionModel().getSelectedItem().ean;
            ouvrirNouvellePage("ModifierPointFourniture", "Modifier Point de fourniture");
        }
    }

    @FXML
    void supprimerPointFourniture(ActionEvent event) {
        Main.ajouterInteractionAuClic(buttonSupprimerPointFourniture);
    }
    FilteredList<PointDeFournitureTable> pointFournitureTables;
    GeneralMethods generalMethods = new GeneralMethodsImpl();
    @Override
    public void initialize(URL url, ResourceBundle rb){
        ean.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("ean"));
        ouvert.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("ouvert"));
        numero_compteur.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("numero_compteur"));
        nom_portefeuille.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("nom_portefeuille"));
        type_energy.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("type_energy"));
        administrateur.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("administrateur"));
        ObservableList items = FXCollections.observableArrayList();

        JSONArray listOfWallets = generalMethods.find("contractSupplyPoint/wallet/identifiant/"+ currentClient.getString("identifiant"));

        /*for (Object j : listOfWallets){
            if (j instanceof JSONObject){
                items.add(new PointDeFournitureTable((JSONObject) j));
            }
        }*/
        pointFournitureTables = new FilteredList<>(items, p->true);
        rechercher.textProperty().addListener((ObservableValue<?extends String> observable, String oldValue, String newValue)->{

            pointFournitureTables.setPredicate(pointFournitureTable->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                if(pointFournitureTable.getNom_portefeuille().toLowerCase().contains(newValue.toLowerCase())||
                        pointFournitureTable.getAdministrateur().toLowerCase().contains(newValue.toLowerCase())||
                        pointFournitureTable.getEan().toLowerCase().contains(newValue.toLowerCase())||
                        pointFournitureTable.getType_energy().toLowerCase().contains(newValue.toLowerCase())
                ) return true;

                return false;
            });
            menuPointFourniture.getItems().clear();
            menuPointFourniture.getItems().addAll(pointFournitureTables);
        });
        menuPointFourniture.getItems().clear();
        menuPointFourniture.getItems().addAll(pointFournitureTables);

    }

}