package controller.controllerInterfaces;

import Gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import javax.swing.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static controller.Methods.GeneralMethodsImpl.API_URL;
import static controller.controllerInterfaces.CreerCompte.JSON;

/**
 * Classe controlleur pour la page de reinitialisation des mots de passe
 */
public class RetrouverCompte implements Initializable {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @FXML
    private TextField identifiant;
    @FXML
    private TextArea reponse_secrete;

    @FXML
    private PasswordField new_password;

    @FXML
    private PasswordField confirm_password;

    @FXML
    private Button valider;
    private JSONObject currentUser;

    @FXML
    void quitterPage(ActionEvent event) {
        Main.stage.close();
        Main.showPages("login.fxml");
    }

    /**
     * permet de mettre a jour le mot de passe de l'utilisateur
     * @param event
     */
    @FXML
    void validerMotDePasse(ActionEvent event) {
        currentUser.remove("password");
        if (new_password.getText().equals(confirm_password.getText()))
        {
            String password = Main.generateHash(new_password.getText());
            currentUser.put("password",password);
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            RequestBody formBody = RequestBody.create(JSON, currentUser.toString());
            Request request = new Request.Builder()
                    .url(API_URL + "/provider")
                    .put(formBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()){
                    Main.logOperation(logger,"Reinitialisation du mot de passe reussie","");
                    Main.stage.close();
                    Main.showPages("login.fxml");

                }
                response.close();
            }
            catch (Exception e){
                Main.logOperation(logger,"","Erreur de reinitialisation du mot de passe");
            }

        }else
        {
            Main.logOperation(logger,"","Votre mot de passe et sa confirmation sont differents");
         Main.afficherAlert("Votre mot de passe et sa confirmation sont differents");
        }

    }

    /**
     * permet de verifier si le client a entre la bonne reponse a la question pour la verification de l'identite
     * @param event
     */

    @FXML
    void verifierQuestion(ActionEvent event) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(API_URL+"/provider/identifiant/"+identifiant.getText())
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();

            JSONObject user =new JSONObject(response.body().string());
            currentUser= user;
            if (user.get("reponse_secrete").equals(reponse_secrete.getText()))
            {
                Main.logOperation(logger,"verification de la question et sa reponse reussie ","");
                new_password.setDisable(false);
                confirm_password.setDisable(false);
                valider.setDisable(false);
            }
            response.close();

        }catch (Exception e ){
            Main.logOperation(logger,"","Echec de verification de question et son mot de passe");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            Main.logOperation(logger,"Ouverture de la page de reinitialisation du mot de passe ","");
            new_password.setDisable(true);
            confirm_password.setDisable(true);
            valider.setDisable(true);

    }
}
