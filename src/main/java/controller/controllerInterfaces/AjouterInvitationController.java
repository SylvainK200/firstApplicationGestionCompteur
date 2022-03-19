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
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public  class AjouterInvitationController  implements Initializable {
    private Logger logger = Logger.getLogger(this.getClass().getName());
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
        if(MenuPrincipaleController.invitationAModifier!=null){
        Main.ajouterInteractionAuClic(ButtonValider);
        String role = "lecture";
        boolean ecriture = roleEcriture.isSelected();
        boolean lecture = roleLecture.isSelected();
        if (ecriture && lecture){
            role=show("ecriture",ecriture);
        }
            else 
            if (lecture){
            role=show("lecture",lecture);
        }
            else 
            if (ecriture){
            role= show ("ecriture",ecriture);
        }
        LocalDateTime actualDate = LocalDateTime.now();
        JSONObject invitation = new JSONObject();
        invitation.put("name",identifiant.getText());
        invitation.put("dateInvitation", DateTimeFormatter.ofPattern("yyyy-mm-dd", Locale.ENGLISH).format(actualDate));
        JSONObject wallet = generalMethods.findUnique("wallet/name/"+ComboboxPortefeuille.getValue());
        JSONObject user = generalMethods.findUnique("user/identifiant/"+identifiant.getText());
        if (user.isEmpty()){
            Main.logOperation(logger,"Cet identifiant n'existe pas dans le systeme","");
            Main.afficherAlert("Cet identifiant n'existe pas dans le systeme");
        }else{
            invitation.put("wallet",wallet);
            invitation.put("user",Main.currentClient);
            invitation.put("droitAcces",role);
            invitation.put("statutInvitation","envoyee");
            System.out.println(invitation);
            JSONObject json = generalMethods.createObject(invitation,"invite");
            if (json.isEmpty()){
                Main.logOperation(logger,
                        "",
                       "Operation d'envoie echouee" );
                Main.afficherAlert("Envoie de l'invitation echoue");
            }else{
                Main.afficherAlert("Creation invitation reussie");
                Main.logOperation(logger,"Operation d'envoie d'invitation reussie","");
                MenuPrincipaleController.invitationEnvoyes.add(new InvitationTable(json,true));
                MenuPrincipaleController.invitationEnvoyees.add(new InvitationTable(json,true));
            }
        }
        }
        else
        {
            Main.logOperation(logger,"Modification de l'invitation","");
            InvitationTable invitation_modif = new InvitationTable( MenuPrincipaleController.invitationAModifier);
            
            Main.ajouterInteractionAuClic(ButtonValider);
            String role = "lecture";
            boolean ecriture = roleEcriture.isSelected();
            boolean lecture = roleLecture.isSelected();
            if (ecriture && lecture){
            role=show("ecriture",ecriture);
        }
            else 
            if (lecture){
            role=show("lecture",lecture);
        }
            else 
            if (ecriture){
            role= show ("ecriture",ecriture);
        }
        LocalDateTime actualDate = LocalDateTime.now();
        JSONObject invitation = new JSONObject();
        invitation.put("name",identifiant.getText());
        invitation.put("dateInvitation", DateTimeFormatter.ofPattern("yyyy-mm-dd", Locale.ENGLISH).format(actualDate));
        JSONObject wallet = generalMethods.findUnique("wallet/name/"+ComboboxPortefeuille.getValue());
        invitation.put("wallet",wallet);
        invitation.put("user",Main.currentClient);
        invitation.put("droitAcces",role);
        invitation.put("statutInvitation","envoyee");
        JSONObject json = generalMethods.updateObject(invitation,"invite");
        if (json.isEmpty()){
                Main.logOperation(logger,"","Mise a jour de l'invitation echouee");
                Main.afficherAlert("Mise a jour de l'invitation echoue");
            }
        else{
            Main.logOperation(logger,"Mise a jour de l'invitation reussie","");
            Main.afficherAlert("Mise a jour invitation reussie");
                MenuPrincipaleController.invitationRecus.remove(MenuPrincipaleController.invitationAModifier);
                MenuPrincipaleController.invitationRecus.add(invitation_modif);
                MenuPrincipaleController.invitationRecues.remove(MenuPrincipaleController.invitationAModifier);
                MenuPrincipaleController.invitationRecues.add(invitation_modif);
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
        Main.logOperation(logger,"Ouverture de la page de modification ou d'ajour de l'invitation","");
        portefeuillesDispo =  generalMethods.find("wallet/visibleWallet/identifiant/"+ Main.currentClient.getString("identifiant"));
        
        for (int i = 0 ; i<portefeuillesDispo.length();i++){
            ComboboxPortefeuille.getItems().add(portefeuillesDispo.getJSONObject(i).getString("name"));
        }
        if (MenuPrincipaleController.invitationAModifier!=null){
            ComboboxPortefeuille.getSelectionModel().select(MenuPrincipaleController.invitationAModifier.getPortefeuille());
            identifiant.setText(MenuPrincipaleController.invitationAModifier.getDestinataire());
            roleEcriture.setSelected(MenuPrincipaleController.invitationAModifier.getAcces().equals("ecriture"));
            roleLecture.setSelected(MenuPrincipaleController.invitationAModifier.getAcces().equals("lecture"));
            identifiant.setDisable(true);
            ComboboxPortefeuille.setDisable(true);
        }
    }
}