package controller.controllerInterfaces;

import java.net.URL;
import java.util.ResourceBundle;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class InitPassword implements Initializable {

    @FXML
    private PasswordField mot_de_passe;

    @FXML
    private Label question;

    @FXML
    private Button renitialise;

    @FXML
    private Button verifier;

    @FXML
    private TextField reponse;


    @FXML
    private TextField identifiant;

    @FXML
    void backToLogin(MouseEvent event) {
        Main.stage.close();
        Main.showPages("login.fxml");
    }

    @FXML
    void renitialise(ActionEvent event) {
        this.renitialise.setDisable(true);
        System.out.println("user before" + user.toString());
        this.user.put("password", Main.generateHash(mot_de_passe.getText()));

        this.user = generalMethods.updateObject(this.user, "user");
        //System.out.println("user after" + user.toString());
        backToLogin(null);
    }

    @FXML
    void verifier(ActionEvent event) {
            this.user = generalMethods.findUnique(  "user" +"/identifiant/"+identifiant.getText());

            if(this.user!=null && this.user.has("question_secrete")){
                identifiant.setDisable(true);
                verifier.setDisable(true);
                reponse.setDisable(false);

                this.question.setText(this.user.getString("question_secrete"));
            }else{
                Main.afficherAlert("Aucun utilisateur avec cette identifiant. Verifiez le. Si le Probleme persiste, contacter un administrateur à <Lien ADMIN>");
            }


    }

    JSONObject user = null;
    
    GeneralMethods generalMethods  = new GeneralMethodsImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reponse.setDisable(true);
        verifier.setDisable(true);
        mot_de_passe.setDisable(true);
        renitialise.setDisable(true);

        this.reponse.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean tmp = ! newValue.equalsIgnoreCase(user.getString("reponse_secrete"));
            mot_de_passe.setDisable(tmp);
            renitialise.setDisable(tmp);
        });

        this.identifiant.textProperty().addListener((observable, oldValue, newValue) -> {
            verifier.setDisable(newValue.isBlank());
        });

    }

}
