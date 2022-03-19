package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import controller.ModelTabs.InvitationClientTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class InvitationClientController implements Initializable {

    @FXML
    private TableColumn<InvitationClientTable, String> dateInvitation;

    @FXML
    private TableColumn<InvitationClientTable, String> droitAcces;

    @FXML
    private TableColumn<InvitationClientTable, String> invitant;

    @FXML
    private TableColumn<InvitationClientTable, String> maison;

    @FXML
    private TableColumn<InvitationClientTable, String> portefeuille;

    @FXML
    private TableColumn<InvitationClientTable, String>select;

    @FXML
    private TableView<InvitationClientTable> tableInvitation;

    ObservableList<InvitationClientTable> invitations = FXCollections.observableArrayList();
    GeneralMethods generalMethods = new GeneralMethodsImpl();

    @FXML
    void allerMenuPrincipal(ActionEvent event) {
        Main.stage.close();
        Main.showPages("MenuPrincipaleController.fxml");
    }

    @FXML
    void refuser(ActionEvent event) {

        invitations.forEach(
                invitation->{
                    if (invitation.select.isSelected()){
                        JSONObject json = new JSONObject();
                        json.put("invite",Main.currentClient.getString("identifiant"));
                        json.put("invitant",invitation.getInvitant());
                        json.put("portefeuille",invitation.getPortefeuille());


                        generalMethods.updateObject(json,"invite/user/update/false");
                        }
                }
        );
        Main.logOperation(logger,"Refus des invitations ","");
    }
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Main.logOperation(logger,"Ouverture de la page des invitation envoyees de l'utilisateur","");

        dateInvitation.setCellValueFactory(new PropertyValueFactory<InvitationClientTable,String>("dateInvitation"));
        portefeuille.setCellValueFactory(new PropertyValueFactory<InvitationClientTable,String>("portefeuille"));
        select.setCellValueFactory(new PropertyValueFactory<InvitationClientTable,String>("select"));
        maison.setCellValueFactory(new PropertyValueFactory<InvitationClientTable, String>("maison"));
        invitant.setCellValueFactory(new PropertyValueFactory<InvitationClientTable,String>("invitant"));
        droitAcces.setCellValueFactory(new PropertyValueFactory<InvitationClientTable,String>("droitAcces"));


        JSONArray invitationArray = generalMethods.find("invite/receive/user/"+ Main.currentClient.getString("identifiant")+"/envoyee");

        invitationArray.forEach(inv->{
            JSONObject invitation = (JSONObject)inv;
            String invitant = invitation.getJSONObject("user").getString("identifiant");
            String dateInvitation = invitation.getString("dateInvitation");
            String droitAcces = invitation.getString("droitAcces");
            String portefeuille = invitation.getJSONObject("wallet").getString("name");
            String maison = invitation.getJSONObject("wallet").getJSONObject("home").getString("street") + invitation.getJSONObject("wallet").getJSONObject("home").getString("city");
            invitations.add(new InvitationClientTable(dateInvitation,droitAcces,invitant,maison,portefeuille));

        });
        tableInvitation.getItems().addAll(invitations);
    }

    @FXML
    void accepter(ActionEvent event) {

        invitations.forEach(
                invitation->{
                    if (invitation.select.isSelected()){
                        System.out.println("Selected");
                        JSONObject json = new JSONObject();
                        json.put("invite",Main.currentClient.getString("identifiant"));
                        json.put("invitant",invitation.getInvitant());
                        json.put("portefeuille",invitation.getPortefeuille());
                        generalMethods.updateObject(json,"invite/user/update/true");
                    }
                }
        );

        Main.logOperation(logger,"Acceptation des invitations ","");
    }
}
