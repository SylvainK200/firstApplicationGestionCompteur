package controller.controllerInterfaces;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
// pour la supprion  il selectione le point de portefeuille
// dans le tableau apres il appuyer sur suupprimer il afficher
// une message de confirmation oui ou non
// pour supprimer le portefeuille .

// même chose pour la modification il selectione le portefeuille
// dans le tableau apres il appuyer sur modifier il afficher
// une interface graphique pour modifier les donnees .


public  class MenuPrincipalePortefeuilleController  implements Initializable {
    @FXML
    private Button   ButtonAjouter;

    @FXML
    private Button ButtonModifier;

    @FXML
    private   Button ButtonSupprimer;

    @FXML
    private  Label LabelNomClient;

    @FXML
    private TextField TextPorteuille;

    @FXML
    private MenuButton MenuCompte;

    @FXML
    private MenuButton MenuParametres;

    @FXML
    private MenuItem  MenuSeDeconnecter;

    @FXML
    private TableView<?> TablesAffichagesPortefeuille;

    @FXML
    private TableColumn<?, ?> adresse;

    @FXML
    private TableColumn<?, ?> nom;

    @FXML
    private TableColumn<?, ?> nombre_compte;

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    public void Message()
    {
        
    }

    @FXML
    void ajouterPOrtefeuille(ActionEvent event) {

    }

    @FXML
    void modifierPortefeuille(ActionEvent event) {

    }

    @FXML
    void supprimerPortefeuille(ActionEvent event) {

    }

}