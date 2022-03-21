package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import okhttp3.*;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Controlleur de la page de creation de compte
 */
public class CreerCompte implements Initializable {
    private Logger logger = Logger.getLogger(this.getClass().getName());
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
    @FXML
    private Button retour;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    /**
     *
     * @return renvoie le JSON issu de la recuperation de toutes les donnees de la page
     */
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

    /**
     * permet de creer un compte apres remplissage du formulaire de la page
     * @param event
     */
    @FXML
    void creerCompte(ActionEvent event) {
        if (confirmation_mot_de_passe.getText().equals(mot_de_passe.getText()))
        {
            JSONObject jsonObject= this.fabriquerJson();
            generalMethods.createObject(jsonObject,"user");
            Main.logOperation(logger,"Creation de compte reussie","");
            Main.stage.close();
            Main.showPages("login.fxml");
         }
        else{
            Main.logOperation(logger,"","le mot de passe et sa confirmation ne sont pas identiques");
            Main.afficherAlert("le mot de passe et sa confirmation ne sont pas identiques");
        }
    }

    /**
     * permet de rediriger vers la page de login
     * @param event
     */
    @FXML
    void retourDeLaCreation(ActionEvent event) {
        Main.stage.close();
        Main.showPages("login.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.logOperation(logger,"Ouverture de la page Creation de compte","");
    }
}
