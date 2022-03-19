package controller.controllerInterfaces;

import Gui.Main;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static Gui.Main.*;
import static controller.Methods.GeneralMethodsImpl.API_URL;

public class Login implements Initializable {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @FXML
    private TextField identifiant;

    @FXML
    private PasswordField mot_de_passe;

    public void initialize(URL location, ResourceBundle resources){

        Main.logOperation(logger,"Ouverture de la page loggin","");
    }

    public static JSONArray invitationsClient = new JSONArray();
    @FXML
    void connect(ActionEvent event) {

        String url = API_URL+"user/identifiant/"+identifiant.getText()+"/"+mot_de_passe.getText();
        System.out.println(url);
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                System.out.println("bonne execution");
                JSONObject user = new JSONObject(response.body().string());
                if (!user.isEmpty()) {
                        invitationsClient = generalMethods.find("invite/receive/user/"+user.getString("identifiant")+"/envoyee");
                        Main.stage.close();
                        currentClient = user;
                        Main.logOperation(logger,"Authentification reussie","");
                    if (invitationsClient.length()>0){
                            Main.showPages("invitationVersClient.fxml");
                        }else{
                            Main.showPages("MenuPrincipaleController.fxml");
                        }
                        response.close();

                }
            } catch (JSONException e) {
                System.out.println("reponse vide");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    @FXML
    void creerCompte(MouseEvent event) {
        Main.stage.close();
        Main.showPages("creer_compte.fxml");
    }

    @FXML
    void retrouverCompte(MouseEvent event) {
        Main.stage.close();
        Main.showPages("retrouverCompte.fxml");
    }
}
