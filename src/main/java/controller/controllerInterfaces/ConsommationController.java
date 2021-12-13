package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import controller.ModelTabs.ConsommationTable;
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
public  class ConsommationController  implements Initializable {

    @FXML
    private Button ButtonRetour;
    @FXML
    private Button ButtonVisualisation;
    @FXML
    private DatePicker ComboboxDateDebut;
    @FXML
    private DatePicker ComboboxDateFin;
    @FXML
    private ComboBox<String> ComboboxEAN;
    @FXML
    private TableView<ConsommationTable> TableConsommation;
    @FXML
    private Button buttonRechercher;
    @FXML
    private TableColumn<ConsommationTable, String> consommateur;
    @FXML
    private TableColumn<ConsommationTable, String> date_lecture;
    @FXML
    private TableColumn<ConsommationTable, String> fournisseur;
    @FXML
    private TableColumn<ConsommationTable, String> type_compteur;
    @FXML
    private TableColumn<ConsommationTable, String> type_energie;

    GeneralMethods generalMethods = new GeneralMethodsImpl();
    FilteredList<ConsommationTable> consommationTables;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        consommateur.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("consommateur"));
        date_lecture.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("date_lecture"));
        fournisseur.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("fournisseur"));
        type_compteur.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("type_compteur"));
        type_energie.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("type_energie"));
        ObservableList items = FXCollections.observableArrayList();

        JSONArray listOfWallets = generalMethods.find("contractSupplyPoint/wallet/identifiant/"+ Main.currentClient.getString("identifiant"));

        for (Object j : listOfWallets){
            if (j instanceof JSONObject){
                items.add(new ConsommationTable((JSONObject) j));
                ComboboxEAN.getItems().add(((JSONObject)j).getJSONObject("supplyPoint").getString("ean_18"));
            }
        }
        consommationTables = new FilteredList<>(items, p->true);
        ComboboxEAN.valueProperty().addListener((ObservableValue<?extends String> observable,
                                                 String oldValue,
                                                 String newValue)->{

            consommationTables.setPredicate(consommationTable -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                if(consommationTable.getEan().toLowerCase().contains(newValue.toLowerCase())) return true;
                return false;
            });
            TableConsommation.getItems().clear();
            TableConsommation.getItems().addAll(consommationTables);
        });
        TableConsommation.getItems().clear();
        TableConsommation.getItems().addAll(consommationTables);

    }
    @FXML
    void rechercher(ActionEvent event) {
        Main.ajouterInteractionAuClic(buttonRechercher);
    }
    @FXML
    void visualiser(ActionEvent event) {

    }




}