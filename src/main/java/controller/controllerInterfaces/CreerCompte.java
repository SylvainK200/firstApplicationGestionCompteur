package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import okhttp3.*;
import org.json.JSONObject;

import javax.swing.*;

import static controller.Methods.GeneralMethodsImpl.API_URL;


public class CreerCompte {
    @FXML
    private TextField nom;

    @FXML
    private TextField number;

    @FXML
    private TextField identifiant;

    @FXML
    private TextField adresse_mail;

    @FXML
    private PasswordField mot_de_passe;

    @FXML
    private PasswordField confirmation_mot_de_passe;

    @FXML
    private TextField question_secrete;

    @FXML
    private TextField reponse_question_secrete;

    @FXML
    private Button creer_compte;
    private GeneralMethods generalMethods = new GeneralMethodsImpl();
    public void initialize(){

    }
    @FXML
    private Button retour;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    JSONObject fabriquerJson(){
        JSONObject json = new JSONObject();
        json.put("address_mail", adresse_mail.getText());
        json.put("password", mot_de_passe.getText())
                .put("question_secrete",question_secrete.getText())
                .put("reponse_secrete",reponse_question_secrete.getText())
                .put("identifiant",identifiant.getText())
                .put("name",nom.getText())
                .put("number",number.getText());
        return json;
    }

    @FXML
    void creerCompte(ActionEvent event) {
        if (confirmation_mot_de_passe.getText().equals(mot_de_passe.getText()))
        {
            JSONObject jsonObject= this.fabriquerJson();
            JSONObject user =generalMethods.createObject(jsonObject,"user");
            Main.stage.close();
            Main.showPages("login.fxml");
         }
        else{
            JOptionPane.showMessageDialog(null,"le mot de passe et sa confirmation ne sont pas identiques");
        }
    }

    @FXML
    void retourDeLaCreation(ActionEvent event) {
        Main.stage.close();
        Main.showPages("login.fxml");
    }

}
