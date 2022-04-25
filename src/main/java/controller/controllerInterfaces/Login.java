package controller.controllerInterfaces;

import Gui.Main;
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

/**
 * Page de login controlleur
 */
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

    /**
     * Cette methode permet de gerer l'authentification et aller vers la page invitations recues d'un client ou bien la page de login
     * @param event
     */
    @FXML
    void connect(ActionEvent event) {
        String motDePasse = Main.generateHash(mot_de_passe.getText());
        String url = API_URL+"user/identifiant/"+identifiant.getText()+"/"+motDePasse;

       if (identifiant.getText().isBlank() || mot_de_passe.getText().isBlank()){
           Main.afficherAlert("Veuillez remplir les champs d'authentification");
           return ;
       }
       try {
                JSONObject user = generalMethods.signin(identifiant.getText(), motDePasse);
                if(user!= null && !user.isEmpty()){
                    System.out.println(user);
                        invitationsClient = generalMethods.find("invite/receive/user/"+user.getString("identifiant")+"/envoyee");
                        Main.stage.close();
                        currentClient = user;
                        Main.logOperation(logger,"Authentification reussie","");
                        try{
                            if (invitationsClient.length()>0){
                                Main.showPages("invitationVersClient.fxml");
                            }else{
                                Main.showPages("MenuPrincipaleController.fxml");
                            }
                        }catch (Exception e){
                            Main.afficherAlert("Une erreur a ete produite ");
                        }

                }

            } catch (JSONException e) {
                e.printStackTrace();
                Main.afficherAlert("Votre mot de passe ou votre nom identifiant est incorrect");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    /**
     * permet d'ouvrir la page de creation de compte
     * @param event
     */
    @FXML
    void creerCompte(MouseEvent event) {
        Main.stage.close();
        Main.showPages("creer_compte.fxml");
    }

    /**
     * permet de reinitialiser le mot de passe donc de reinitialiser les informations d'authentification
     * @param event
     */
    @FXML
    void retrouverCompte(MouseEvent event) {
        Main.stage.close();
        Main.showPages("recover_password.fxml");
    }
}
