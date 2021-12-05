package controller.controllerInterfaces;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
public  class ConsommationController  implements Initializable {
    @FXML
    private ComboBox ComboboxEAN;

    @FXML
    private DatePicker ComboboxDateDebut;

    @FXML
    private DatePicker ComboboxDateFin;

    @FXML
    private Label LabelNomClient;

    @FXML
    private MenuButton ButtonCompte;

    @FXML
    private MenuItem MenuParametres;

    @FXML
    private MenuItem MenuSeDeconnecter;

    @FXML
    private TableView TableConsommation;

    @FXML
    private Button ButtonRetour;

    @FXML
    private Button ButtonOK;

    @FXML
    private Button ButtonVisualisation;
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    @FXML
    void rechercherBetweenDates(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {

    }

    @FXML
    void visualiser(ActionEvent event) {

    }

}