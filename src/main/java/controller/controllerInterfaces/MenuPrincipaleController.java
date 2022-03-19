package controller.controllerInterfaces;

import Gui.Main;
import controller.ComboBoxClasses.ComboBoxPointFourniture;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import controller.ModelTabs.ConsommationTable;
import controller.ModelTabs.InvitationTable;
import controller.ModelTabs.PointDeFournitureTable;
import controller.ModelTabs.PortefeuilleTable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Gui.Main.*;
import static Gui.Main.ajouterInteractionAuClic;


public  class MenuPrincipaleController implements Initializable {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    GeneralMethods generalMethods = new GeneralMethodsImpl();

    @FXML
    private Label LabelNomClient;

    public static JSONArray invitationsValides ;


    @FXML
    void deconnecter(ActionEvent event){
        Main.stage.close();
        Main.showPages("login.fxml");
    }




    //controller pour les consommations


    @FXML
    private ComboBox<String> ComboboxEAN;
    @FXML
    private TableView<ConsommationTable> TableConsommation;
    @FXML
    private Button buttonRechercher;
    @FXML
    private TableColumn<ConsommationTable, String> numero_compteur2;
    @FXML
    private TableColumn<ConsommationTable,String> quantiteConsommee;
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
        date_lecture.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("date_lecture"));
        fournisseur.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("fournisseur"));
        type_compteur.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("type_compteur"));
        type_energie.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("type_energie"));
        numero_compteur2.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("numero_compteur"));
        quantiteConsommee.setCellValueFactory(new PropertyValueFactory<ConsommationTable,String>("quantiteConsommee"));
        ObservableList items = FXCollections.observableArrayList();

        JSONArray listOfWallets = new JSONArray();
        JSONArray listOfWallets1 = new JSONArray();
        listOfWallets1 = generalMethods.find("historicalValue/historiqueClient/"+ Main.currentClient.getString("identifiant"));
        listOfWallets1.forEach(wal->{
            listOfWallets.put(wal);
        });

        items.clear();
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
        ArrayList<ConsommationTable> consommations = new ArrayList<>();
        consommationTables.forEach(conso->
        {
            consommations.add(conso);
        });
        Visualisation.consommations = consommations;
        Visualisation.numeroCompteur = ComboboxEAN.getValue();

        Main.showPages("visualization.fxml");
    }






    // controller pour les points de fourniture
    @FXML
    private TableColumn<PointDeFournitureTable, String> administrateur;

    @FXML
    private Button buttonSupprimerPointFourniture;

    @FXML
    private TableColumn<PointDeFournitureTable, String> ean;

    @FXML
    private TableView<PointDeFournitureTable> menuPointFourniture;

    @FXML
    private TableColumn<PointDeFournitureTable, String> nom_portefeuille;


    @FXML
    private TextField rechercher;

    @FXML
    private TableColumn<PointDeFournitureTable, String> type_energy;
    @FXML
    void supprimerPointFourniture(ActionEvent event) {

        boolean permissionToModify;
        if(menuPointFourniture.getSelectionModel().getSelectedItem()!=null){
            PointDeFournitureTable pointDeFournitureTable = menuPointFourniture.getSelectionModel().getSelectedItem();
            permissionToModify = Main.getPermission(pointDeFournitureTable.getNom_portefeuille(),currentClient.getString("identifiant"));
            if(permissionToModify){
                Main.ajouterInteractionAuClic(buttonSupprimerPointFourniture);
                JSONObject pointFournitureWallet = new JSONObject();
                pointFournitureWallet.put("ean",pointDeFournitureTable.getEan());
                pointFournitureWallet.put("walletname",pointDeFournitureTable.getNom_portefeuille());
                JSONObject wallet = generalMethods.updateObject(pointFournitureWallet,"wallet/removePointFourniture");
                Main.logOperation(logger,"Suppression reussie d'un point de fourniture pour un portefeuille","");
                Main.afficherAlert("Suppression reussie d'un point de fourniture pour un portefeuille");
                if(wallet != null) {
                    menuPointFourniture.getItems().remove(pointDeFournitureTable);
                }
            }else{
                Main.logOperation(logger,"","Suppression echouee car non acces ");
                Main.afficherAlert("Vous n'avez pas des acces en ecriture sur ce portefeuille");
            }
        }else{
            Main.afficherAlert("Veuillez choisir un element");
        }
    }
    FilteredList<PointDeFournitureTable> pointFournitureTables;


    public void initializePointFourniture(){
        ean.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("ean"));
        nom_portefeuille.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("nom_portefeuille"));
        type_energy.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("type_energy"));
        administrateur.setCellValueFactory(new PropertyValueFactory<PointDeFournitureTable,String>("administrateur"));
        ObservableList items = FXCollections.observableArrayList();

        JSONArray listOfWallets  = new JSONArray();
        JSONArray listOfWallets1 ;
        listOfWallets1 = generalMethods.find("wallet/visibleWallet/identifiant/"+ currentClient.getString("identifiant"));
        listOfWallets1.forEach(w->{
            listOfWallets.put((JSONObject)w);
        });
        invitationsValides.forEach(inv->{
            JSONObject wallet  = ((JSONObject) inv).getJSONObject("wallet");
            if (wallet.getBoolean("visibleByClient")){
            listOfWallets.put(wallet);
            }
        });
        listOfWallets.forEach(wallet->{
            if(((JSONObject)wallet).getJSONArray("pointFournitures").length()>0){
                JSONArray pointFournitures = ((JSONObject)wallet).getJSONArray("pointFournitures");
                pointFournitures.forEach(point ->{
                    System.out.println("point : "+point + "\n"+ " wallet : " +wallet);
                    items.add(new PointDeFournitureTable((JSONObject) point , (JSONObject) wallet));
                });
            }
        });
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
















    // controlleur du portefeuille
    public  static PortefeuilleTable portefeuilleAModifier;
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

    @FXML
    private ComboBox<ComboBoxPointFourniture> ean_point_fourniture;


    @FXML
    private TextField type_energy1;
    @FXML
    private  TextField fournisseur_text;

    @FXML
    void ajouterPointFourniture(ActionEvent event) {
        JSONObject o = new JSONObject();
        String walletName = TablesAffichagesPortefeuille.getSelectionModel().getSelectedItem().nom;
        String username = currentClient.getString("identifiant");
        o.put("ean",ean_point_fourniture.getValue().getEan());
        o.put("walletname",walletName);
        o.put("username",username);

        if(Main.getPermission(walletName, username)){
            Main.logOperation(logger,"Ajout d'un point de fourniture a un portefeuille","");
            generalMethods.updateObject(o,"wallet/addPointFourniture");
            initializePointFourniture();
        }else{
            Main.logOperation(logger,"","Ajout echoue car acces en lecture");
            Main.afficherAlert("Vous n'avez pas le droit d'ecriture sur ce portefeuille");
        }

    }
    private FilteredList<PortefeuilleTable> portefeuilleTables;
    public static List<PortefeuilleTable> listPortefeuille = new ArrayList<>();
    public JSONArray listOfWallets;
    public static ObservableList<PortefeuilleTable> portefeuilles = FXCollections.observableArrayList(listPortefeuille);
    public void initializePortefeuille(){



        nom.setCellValueFactory(new PropertyValueFactory<PortefeuilleTable,String>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<PortefeuilleTable,String>("adresse"));
        nombre_compte.setCellValueFactory(new PropertyValueFactory<PortefeuilleTable,String>("nombreCompteur"));

        // recherche des differents points de fournisseurs et initialisation du combobox
        JSONArray pointsFournitures = generalMethods.find("pointFourniture");
        pointsFournitures.forEach(pointsFourniture ->{
            ean_point_fourniture.getItems().add(new ComboBoxPointFourniture((JSONObject) pointsFourniture));
        });


        // Ajout d'un listener sur le combobox ean
        ean_point_fourniture.valueProperty().addListener(
                (ObservableValue<?extends ComboBoxPointFourniture> observable,
                 ComboBoxPointFourniture oldValue,
                 ComboBoxPointFourniture newValue)->{
                    type_energy1.setStyle("-fx-background-color:white;");
                    type_energy1.setText(newValue.getEnergy());
                    fournisseur_text.setStyle("-fx-background-color:white;");
                    fournisseur_text.setText(newValue.getProvider_name() );
                }
        );


        listOfWallets  = new JSONArray();
        JSONArray listOfWallets1 ;
        listOfWallets1 = generalMethods.find("wallet/visibleWallet/identifiant/"+ currentClient.getString("identifiant"));
        listOfWallets1.forEach(w->{
            listOfWallets.put((JSONObject)w);
        });
        invitationsValides.forEach(inv->{
            JSONObject wallet  = ((JSONObject) inv).getJSONObject("wallet");
            if (wallet.getBoolean("visibleByClient")){
                listOfWallets.put(wallet);
            }
        });
        portefeuilles.clear();
        for (Object j : listOfWallets){
            if (j instanceof JSONObject){
                portefeuilles.add(new PortefeuilleTable((JSONObject) j));
            }
        }
        portefeuilles.addListener(
                new ListChangeListener<PortefeuilleTable>() {
                    @Override
                    public void onChanged(Change<? extends PortefeuilleTable> c) {
                        System.out.println("onchanged of portefeuilles");
                        TablesAffichagesPortefeuille.getItems().clear();
                        TablesAffichagesPortefeuille.getItems().addAll(portefeuilles);
                    }
                }
        );


        portefeuilleTables = new FilteredList<>(portefeuilles,p->true);
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
        ouvrirNouvellePage("CreationPortefeuille","Creation de Portefeuille");
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
            portefeuilleAModifier = TablesAffichagesPortefeuille.getSelectionModel().getSelectedItem();
            if(getPermission(portefeuilleAModifier.nom,currentClient.getString("identifiant"))){
                ouvrirNouvellePage("ModifierPortefeuille","Modifier Portefeuille");
            }
            else{
                Main.logOperation(logger,"","Vous n'avez pas acces a ce portefeuille en ecriture");
                Main.afficherAlert("Vous n'avez pas acces a ce portefeuille en ecriture");

            }

        }


    }

    @FXML
    void supprimerPortefeuille(ActionEvent event) {
        ajouterInteractionAuClic(ButtonSupprimer);
        if(TablesAffichagesPortefeuille.getSelectionModel().getSelectedItem() == null){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Absence de selection element");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez selectionne aucun element dans le tableau pour la suppression ");
            alert1.showAndWait();
        }
        // sinon on peut maintenant ouvrir la page de modification de portefeuille

        else {
                PortefeuilleTable port = TablesAffichagesPortefeuille.getSelectionModel().getSelectedItem();
                String name = port.nom;
                JSONObject wallet = new JSONObject();
                if(getPermission(port.nom,currentClient.getString("identifiant"))){
                    wallet = generalMethods.findUnique("wallet/name/"+name);
                    JSONObject json = new JSONObject();
                    json.put("nom",wallet.getString("name"));
                    generalMethods.updateObject(json,"wallet/deleteWallet");
                    TablesAffichagesPortefeuille.getItems().remove(port);
                }
                else{
                    Main.logOperation(logger,"suppression impossible acces a ce portefeuile en lecture sur le portefeuille","");
                    Main.afficherAlert("Vous n'avez pas acces a ce portefeuile en ecriture");
                }

        }


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

    public static List<InvitationTable> invitationEnvoyes = new ArrayList();
    public static List<InvitationTable> invitationRecus = new ArrayList<>();

    public static ObservableList<InvitationTable> invitationEnvoyees = FXCollections.observableArrayList(invitationEnvoyes);
    public static ObservableList<InvitationTable> invitationRecues = FXCollections.observableArrayList(invitationRecus);
    public static  JSONArray listWalletUsable = new JSONArray();

    void initialiserTableInvitationEnvoyee(){
        acces.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("acces"));
        date_envoie.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("date_envoie"));
        destinataire.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("destinataire"));
        portefeuille_invitation.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("portefeuille"));
        statut.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("statut"));

        JSONArray invitations = generalMethods.find("invite/user/statut/"+Main.currentClient.getString("identifiant")+"/"+"envoyee");
        invitationEnvoyes.clear();
        for (int i =0;i<invitations.length();i++){
            // formation de chaque ligne du tableau a remplir
            JSONObject client = invitations.getJSONObject(i);
            if (!client.isEmpty()){
                invitationEnvoyes.add(new InvitationTable(client,true));
            }
        }
        TableInvitation.getItems().addAll(invitationEnvoyes);

        invitationEnvoyees.addListener(
                new ListChangeListener<InvitationTable>() {
                    @Override
                    public void onChanged(Change<? extends InvitationTable> c) {
                        System.out.println("Ajout des invitations  : ");
                       TableInvitation.getItems().removeAll(TableInvitation.getItems());
                       TableInvitation.getItems().addAll(invitationEnvoyes);
                    }
                }
        );

        invitationRecues.addListener(
                new ListChangeListener<InvitationTable>() {
                    @Override
                    public void onChanged(Change<? extends InvitationTable> c) {
                        System.out.println("Mise a jour des invitations ");
                        tableInvitationRecu.getItems().removeAll(tableInvitationRecu.getItems());
                        tableInvitationRecu.getItems().addAll(invitationRecus);
                    }
                }
        );
    }

    void initialiserTableInvitationRecu(){
        acces_recu.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("acces"));
        date_envoie_recu.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("date_envoie"));
        destinataire_recu.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("destinataire"));
        portefeuille_recu.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("portefeuille"));
        statut_recu.setCellValueFactory(new PropertyValueFactory<InvitationTable,String>("statut"));
        invitationRecues.clear();
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
        listWalletUsable = listOfWallets;
        Main.ajouterInteractionAuClic(ButtonAjouterInvitation);
        Main.ouvrirNouvellePage("AjouterInvitation","Ajouter Invitation");

    }
    public static InvitationTable invitationAModifier;
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
            invitationAModifier = tableInvitationRecu.getSelectionModel().getSelectedItem();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        }catch (Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,"Modification des invitations");
        }


        Main.ouvrirNouvellePage("ModifierInvitation","Modifier Invitation");
    }

    @FXML
    void supprimerInvitation(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonSupprimerInvitation);
        InvitationTable inv = TableInvitation.getSelectionModel().getSelectedItem();
        long id = inv.getId();
        generalMethods.deleteObject("invite/"+id);
        TableInvitation.getItems().remove(inv);
        Main.logOperation(logger,"Suppression Invitation","");
    }

    @FXML
    void supprimerInvitation_recu(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonSupprimerInvitationRecu);
        InvitationTable inv = tableInvitationRecu.getSelectionModel().getSelectedItem();
        long id = inv.getId();
        this.generalMethods.deleteObject("invite/"+id);
        tableInvitationRecu.getItems().remove(inv);
        Main.logOperation(logger,"Suppression Invitation recue","");

    }

    public void initializeInvitations(){
        initialiserTableInvitationEnvoyee();
        initialiserTableInvitationRecu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            invitationsValides = generalMethods.find("invite/receive/user/"+currentClient.getString("identifiant")+"/recu");
            LabelNomClient.setText(currentClient.getString("name"));
            initializePortefeuille();
            initializeInvitations();
            initializePointFourniture();
            initializeConsommations();

    }
}