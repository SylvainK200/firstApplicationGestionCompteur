package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import controller.ModelTabs.InvitationTable;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public  class AjouterInvitationController  implements Initializable {
    @FXML
    private Button ButtonAnnuler;

    @FXML
    private Button ButtonRetour;

    @FXML
    private Button ButtonValider;


    @FXML
    private ComboBox<String> ComboboxPortefeuille;

    @FXML
    private TextField identifiant;

    @FXML
    private CheckBox roleEcriture;

    @FXML
    private CheckBox roleLecture;

    private Stage dialogStage;
    private InvitationTable invitationTableElement;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public InvitationTable getInvitationTableElement() {
        return invitationTableElement;
    }

    public void setInvitationTableElement(InvitationTable invitationTableElement) {
        this.invitationTableElement = invitationTableElement;
    }

    public JSONArray portefeuillesDispo;
    @FXML
    void ajouterInvitation(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonValider);
        String role ;
        boolean ecriture = roleEcriture.isSelected();
        boolean lecture = roleLecture.isSelected();
        role = show("lecture",lecture);
        if (ecriture && lecture){
            role+=show("-ecriture",ecriture);
        }
        role = show("ecriture",ecriture);
        LocalDateTime actualDate = LocalDateTime.now();
        JSONObject invitation = new JSONObject();
        invitation.put("name",identifiant.getText());
        invitation.put("dateInvitation", DateTimeFormatter.ofPattern("yyyy-mm-dd", Locale.ENGLISH).format(actualDate));
        JSONObject wallet = generalMethods.findUnique("wallet/name/"+ComboboxPortefeuille.getValue());
        JSONObject user = generalMethods.findUnique("user/identifiant/"+identifiant.getText());
        if (user.isEmpty()){
            Main.afficherAlert("Cet identifiant n'existe pas dans le systeme");
        }else{
            invitation.put("wallet",wallet);
            invitation.put("user",Main.currentClient);
            invitation.put("droitAcces",role);
            invitation.put("statutInvitation","envoyee");
            System.out.println(invitation);
            JSONObject json = generalMethods.createObject(invitation,"invite");
            if (json.isEmpty()){
                Main.afficherAlert("Envoie de l'invitation echoue");
            }else{
                Main.afficherAlert("Creation invitation reussie");

                MenuPrincipaleController.invitationEnvoyes.add(new InvitationTable(json,true));
                MenuPrincipaleController.invitationEnvoyees.add(new InvitationTable(json,true));
            }
        }

    }

    String show(String s , boolean b){
        if (b) return s ;
        return "";
    }
    @FXML
    void annuler(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonAnnuler);
        roleEcriture.setSelected(false);
        roleLecture.setSelected(false);
        identifiant.setText("");
    }

    @FXML
    void retour(ActionEvent event) {
        Main.ajouterInteractionAuClic(ButtonRetour);
        Main.newStage.close();
    }
    GeneralMethods generalMethods = new GeneralMethodsImpl();
    @Override
    public void initialize(URL url, ResourceBundle rb){
        portefeuillesDispo = MenuPrincipaleController.listWalletUsable;
       // JSONArray wallets = generalMethods.find("wallet/identifiant/"+ Main.currentClient.getString("identifiant"));
        for (int i = 0 ; i<portefeuillesDispo.length();i++){
            ComboboxPortefeuille.getItems().add(portefeuillesDispo.getJSONObject(i).getString("name"));
        }
        if (invitationTableElement!=null){
            ComboboxPortefeuille.getSelectionModel().select(invitationTableElement.getPortefeuille());
            identifiant.setText(invitationTableElement.getDestinataire());
        }
    }
}