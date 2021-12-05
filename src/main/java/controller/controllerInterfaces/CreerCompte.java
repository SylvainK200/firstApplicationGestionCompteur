package controller.controllerInterfaces;

import Gui.FacilitatorProviderLinkClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import okhttp3.*;
import org.json.JSONObject;

import javax.swing.*;

import static Gui.Controllers.Methods.GeneralMethodsImpl.API_URL;

public class CreerCompte {
    @FXML
    private TextField street;

    @FXML
    private TextField city;

    @FXML
    private TextField postal_code;
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
    @FXML
    private ComboBox<String> type_utilisateur;
    public void initialize(){
        type_utilisateur.getItems().addAll("Consommateur","Fournisseur");
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
                .put("street",street.getText())
                .put("number",number.getText())
                .put("city",city.getText())
                .put("postal_code",postal_code.getText());
        return json;
    }

    @FXML
    void creerCompte(ActionEvent event) {
        if (confirmation_mot_de_passe.getText().equals(mot_de_passe.getText()))
        { OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
            JSONObject jsonObject= this.fabriquerJson();
            if (this.type_utilisateur.getValue() !=null){

                String url = "/user";
                if (this.type_utilisateur.getValue() == "Fournisseur"){
                    jsonObject.remove("name");
                    jsonObject.put("company_name",nom.getText());
                    url  = "/provider";
                }
                RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());
                Request request = new Request.Builder()
                        .url(API_URL +url)
                        .post(formBody)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()){
                        System.out.println("Enregistrement termine");
                        FacilitatorProviderLinkClient.stage.close();
                        FacilitatorProviderLinkClient.showPages("login.fxml");

                    }
                    response.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }else {
                JOptionPane.showMessageDialog(null,"Choisissez le type d'utilisateur \n que vous etes");
            }

         }
        else{
            JOptionPane.showMessageDialog(null,"le mot de passe et sa confirmation ne sont pas identiques");
        }
    }

    @FXML
    void retourDeLaCreation(ActionEvent event) {
        FacilitatorProviderLinkClient.stage.close();
        FacilitatorProviderLinkClient.showPages("login.fxml");
    }

}
