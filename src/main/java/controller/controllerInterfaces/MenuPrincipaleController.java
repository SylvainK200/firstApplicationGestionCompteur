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
    void seDeconnecter(){
        Main.stage.close();
        Main.showPages("login.fxml");
    }
    @FXML
    void goToParametres(){

    }

    public void initialize(){
        LabelNomClient.setText(currentClient.getString("name"));
        initializePortefeuille();
        initializeInvitations();
        initializePointFourniture();
        initializeConsommations();
        initializeModifierPointFourniture();
    }

    //controller pour les consommations


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

    FilteredList<ConsommationTable> consommationTables;


    public void initializeConsommations(){
        consommateur.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("consommateur"));
        date_lecture.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("date_lecture"));
        fournisseur.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("fournisseur"));
        type_compteur.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("type_compteur"));
        type_energie.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("type_energie"));
        ObservableList items = FXCollections.observableArrayList();

        JSONArray listOfWallets = new JSONArray();
        listOfWallets = generalMethods.find("contractSupplyPoint/wallet/identifiant/"+ Main.currentClient.getString("identifiant"));

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






    // controller pour les points de fourniture
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
        Main.ouvrirNouvellePage("AjouterPointFourniture");

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
            ouvrirNouvellePage("ModifierPointFourniture");
        }
    }

    @FXML
    void supprimerPointFourniture(ActionEvent event) {
        Main.ajouterInteractionAuClic(buttonSupprimerPointFourniture);
    }
    FilteredList<PointDeFournitureTable> pointFournitureTables;


    public void initializePointFourniture(){
        ean.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("ean"));
        ouvert.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("ouvert"));
        numero_compteur.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("numero_compteur"));
        nom_portefeuille.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("nom_portefeuille"));
        type_energy.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("type_energy"));
        administrateur.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("administrateur"));
        ObservableList items = FXCollections.observableArrayList();

        JSONArray listOfWallets  = new JSONArray();

        listOfWallets = generalMethods.find("contractSupplyPoint/wallet/identifiant/"+ currentClient.getString("identifiant"));

        for (Object j : listOfWallets){
            if (j instanceof JSONObject){
                items.add(new PointDeFournitureTable((JSONObject) j));
            }
        }
        pointFournitureTables = new FilteredList<>(items, p->true);
        rechercher.textProperty().addListener((ObservableValue<?extends String> observable, String oldValue, String newValue)->{

            pointFournitureTables.setPredicate(pointFournitureTable->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                if(pointFournitureTable.getNom_portefeuille().toLowerCase().contains(newValue.toLowerCase())||
                        pointFournitureTable.getAdministrateur().toLowerCase().contains(newValue.toLowerCase())||
                        pointFournitureTable.getEan().toLowerCase().contains(newValue.toLowerCase())||
                        pointFournitureTable.getType_energie().toLowerCase().contains(newValue.toLowerCase())
                ) return true;

                return false;
            });
            menuPointFourniture.getItems().clear();
            menuPointFourniture.getItems().addAll(pointFournitureTables);
        });
        menuPointFourniture.getItems().clear();
        menuPointFourniture.getItems().addAll(pointFournitureTables);

    }
















    // controlleur du portefeuille
    public static  String nomPortefeuilleAModifier;
    @FXML
    private Button   ButtonAjouterPortefeuille;

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
    public void initializePortefeuille(){

        nom.setCellValueFactory(new PropertyValueFactory<PortefeuilleTable,String>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<PortefeuilleTable,String>("addresse"));
        nombre_compte.setCellValueFactory(new PropertyValueFactory<PortefeuilleTable,String>("nombreCompteur"));

        ObservableList items = FXCollections.observableArrayList();
        JSONArray listOfWallets = new JSONArray();
        try {
            listOfWallets = generalMethods.find("wallet/identifiant/"+ currentClient.getString("identifiant"));
        }
        catch (Exception e ) {
            System.out.println("erreur d'execution de la methode");
        }


        for (Object j : listOfWallets){
            if (j instanceof JSONObject){
                items.add(new PortefeuilleTable((JSONObject) j));
            }
        }
        portefeuilleTables = new FilteredList<>(items,p->true);
        TextPorteuille.textProperty().addListener((ObservableValue<?extends String> observable, String oldValue, String newValue)->{

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
        ajouterInteractionAuClic(ButtonAjouterPortefeuille);
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





// Modifier Point de fourniture
@FXML
private TextField TextTypeEnergie;

    @FXML
    private TextField TextEAN;

    @FXML
    private ComboBox ComboboxFournisseur;

    @FXML
    private ComboBox ComboboxTypeContrat;

    @FXML
    private Button ButtonAnnuler;

    @FXML
    private MenuButton ButtonCompte;

    @FXML
    private MenuItem MenuParametres;

    @FXML
    private MenuItem MenuSeDeconnecter;

    @FXML
    private Button ButtonAjouter;

    @FXML
    private Button ButtonRetour;

    public void initializeModifierPointFourniture(){

    }
    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonRetour);
        Main.newStage.close();
    }

    @FXML
    void valider(ActionEvent event) {

    }


















    // invitation anchorpane controller
    @FXML
    private Button ButtonAjouterInvitation;

    @FXML
    private Button ButtonModificationnvitation;

    @FXML
    private Button ButtonSupprimerInvitation;

    @FXML
    private Button ButtonSupprimerInvitationRecu;

    @FXML
    private TableView<InvitationTable> TableInvitation;
    @FXML
    private TableView<InvitationTable> tableInvitationRecu;

    @FXML
    private TableColumn<InvitationTable, String> acces;

    @FXML
    private TableColumn<InvitationTable, String> date_envoie;

    @FXML
    private TableColumn<InvitationTable, String> destinataire;

    @FXML
    private TableColumn<InvitationTable, String> portefeuille_invitation;

    @FXML
    private TableColumn<InvitationTable, String> statut;


    @FXML
    private TableColumn<InvitationTable, String> acces_recu;

    @FXML
    private TableColumn<InvitationTable, String> date_envoie_recu;

    @FXML
    private TableColumn<InvitationTable, String> destinataire_recu;

    @FXML
    private TableColumn<InvitationTable, String> portefeuille_recu;

    @FXML
    private TableColumn<InvitationTable, String> statut_recu;
    public static List<InvitationTable> invitationEnvoyees = new ArrayList();
    public static List<InvitationTable> invitationRecues = new ArrayList<>();


    void initialiserTableInvitationEnvoyee(){
        acces.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("acces"));
        date_envoie.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("date_envoie"));
        destinataire.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("destinataire"));
        portefeuille_invitation.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("portefeuille"));
        statut.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("statut"));

        JSONArray invitations = generalMethods.find("invite/user/statut/"+Main.currentClient.getString("identifiant")+"/"+"envoyee");
        for (int i =0;i<invitations.length();i++){
            // formation de chaque ligne du tableau a remplir
            JSONObject client = invitations.getJSONObject(i);
            if (!client.isEmpty()){
                invitationEnvoyees.add(new InvitationTable(client,true));

            }
        }
        TableInvitation.getItems().addAll(invitationEnvoyees);

    }

    void initialiserTableInvitationRecu(){
        acces_recu.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("acces"));
        date_envoie_recu.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("date_envoie"));
        destinataire_recu.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("destinataire"));
        portefeuille_recu.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("portefeuille"));
        statut_recu.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("statut"));

        JSONArray invitations = generalMethods.find("invite/user/statut/" + Main.currentClient.getString("identifiant")+"/"+"recu");
        for (int i =0;i<invitations.length();i++){
            JSONObject invitation = invitations.getJSONObject(i);
            if (!invitation.isEmpty()){
                invitationRecues.add(new InvitationTable(invitation,false));
            }
            tableInvitationRecu.getItems().addAll(invitationRecues);
        }
    }
    @FXML
    void ajouterInvitation(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonAjouterInvitation);
        Main.ouvrirNouvellePage("AjouterInvitation");

    }

    @FXML
    void modifierInvitation(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonModificationnvitation);
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("/interfaces/AjouterInvitation.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier invitation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AjouterInvitationController controller = loader.getController();
            controller.setInvitationTableElement(tableInvitationRecu.getSelectionModel().getSelectedItem());
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        }catch (Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,"Modification des invitations");
        }


        Main.ouvrirNouvellePage("ModifierInvitation");
    }

    @FXML
    void supprimerInvitation(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonSupprimerInvitation);
        InvitationTable inv = TableInvitation.getSelectionModel().getSelectedItem();
        long id = inv.getId();
        generalMethods.deleteObject("invite/"+id);
    }

    @FXML
    void supprimerInvitation_recu(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonSupprimerInvitationRecu);
        InvitationTable inv = tableInvitationRecu.getSelectionModel().getSelectedItem();
        long id = inv.getId();
        this.generalMethods.deleteObject("invite/"+id);

    }

    public void initializeInvitations(){
        initialiserTableInvitationEnvoyee();
        initialiserTableInvitationRecu();
    }

}