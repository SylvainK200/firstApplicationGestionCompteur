package controller.controllerInterfaces;

import Gui.Main;
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
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.util.Objects;

import static Gui.Main.currentClient;
import static Gui.Main.currentprovider;
import static controller.Methods.GeneralMethodsImpl.API_URL;

public class Login {

    @FXML
    private TextField identifiant;

    @FXML
    private PasswordField mot_de_passe;

    @FXML
    private Button connect_button;
    @FXML
    private ComboBox<String> type_utilisateur;
    @FXML
    private Text mot_de_passe_perdu;

    @FXML
    private Text creer_compte;
    public void initialize(){
        type_utilisateur.getItems().addAll("Consommateur","Fournisseur");
    }
    @FXML
    void connect(ActionEvent event) {
        if (Objects.nonNull(type_utilisateur.getValue()) && type_utilisateur.getValue()!="") {
           String url = "" ;
           if (type_utilisateur.getValue().equals("Consommateur")){
               url = API_URL+"/user/identifiant/"+identifiant.getText()+"/"+mot_de_passe.getText();
           }
           else {
               url = API_URL + "/provider/identifiant/"+identifiant.getText()+"/"+mot_de_passe.getText();
           }
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .build();
            try {
                Response response = client.newCall(request).execute();

                JSONObject user = new JSONObject(response.body().string());
                if (!user.isEmpty()) {
                    Main.stage.close();
                    if (this.type_utilisateur.getValue().equals("Fournisseur"))
                    {
                        currentprovider = user;
                        System.out.println("current provider :" + currentprovider.toString());
                        Main.showPages("MenuPrincipale.fxml");
                        response.close();
                    }else {
                        currentClient = user;
                        System.out.println("current provider :" + currentClient.toString());
                        Main.showPages("client/MenuPrincipaleConsommateur.fxml");
                        response.close();
                    }

                }
            } catch (JSONException e) {
                System.out.println("reponse vide");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Veuillez choisir le type d'utilisateur \n que vous etes");
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
