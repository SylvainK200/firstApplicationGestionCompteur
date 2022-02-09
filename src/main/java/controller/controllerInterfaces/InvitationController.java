package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import controller.ModelTabs.InvitationTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class InvitationController {

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
    public static ObservableList<InvitationTable> invitationEnvoyees = FXCollections.observableArrayList();
    public static ObservableList<InvitationTable> invitationRecues = FXCollections.observableArrayList();
    public GeneralMethods generalMethods = new GeneralMethodsImpl();

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

    public void initialize(){
        initialiserTableInvitationEnvoyee();
        initialiserTableInvitationRecu();
    }

}
