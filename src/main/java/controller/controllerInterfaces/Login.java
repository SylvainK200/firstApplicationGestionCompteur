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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.util.Objects;

import static Gui.Main.*;
import static controller.Methods.GeneralMethodsImpl.API_URL;

public class Login {

    @FXML
    private TextField identifiant;

    @FXML
    private PasswordField mot_de_passe;

   /* @FXML
    private Button connect_button;
    @FXML
    private Text mot_de_passe_perdu;

    @FXML
    private Text creer_compte;*/
    public void initialize(){
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
