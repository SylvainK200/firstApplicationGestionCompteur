package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import controller.ModelTabs.InvitationClientTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;


public class InvitationClientController {

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
    public void initialize(){
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
    }

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
    }

}
