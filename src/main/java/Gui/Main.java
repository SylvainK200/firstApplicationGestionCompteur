package Gui;

import com.fasterxml.jackson.core.JsonProcessingException;
import controller.Methods.GeneralMethods;
import controller.Methods.GeneralMethodsImpl;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    public static JSONObject currentClient ;
    public static Stage  stage = new Stage();
    public static Stage newStage = new Stage();
    public static String firstpage = "login.fxml";
    public static GeneralMethods generalMethods = new GeneralMethodsImpl();
    public static Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void logOperation(Logger logger,String operationWarning, String operationSevere ){
        if(operationWarning.equals("")){
                logger.log(Level.SEVERE,operationSevere);
            }
            else if (operationSevere == ""){
                logger.log(Level.WARNING,operationWarning);
            }else if (!operationSevere.equals("") && !operationWarning.equals("")){
                logger.log(Level.SEVERE,operationSevere);
                logger.log(Level.WARNING,operationWarning);
            }
    }
    public static void afficherAlert (String contentText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,contentText, ButtonType.OK);
        alert.showAndWait();;
    }
    public static void startForTests(Stage primaryStage) throws Exception{
        stage=primaryStage;
        showPages("login.fxml");
       
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        showPages(firstpage);
       logOperation(LOGGER,"operation warning","SEVERE Warnings");
    }
    public static void main(String[] args) throws JsonProcessingException {

        launch(args);
    }
    public static boolean getPermission(String walletName, String username){
        JSONObject permission = generalMethods.findUnique("wallet/permission/"+walletName+"/"+username);
        if(permission.getString("permission").equals("lecture")){
            return false;
        }
        else{
            return true;
        }
    }

    public static void ajouterInteractionAuClic(Button b){
        TranslateTransition translate = new TranslateTransition();
        translate.setByX(5);
        translate.setDuration(Duration.millis(200));
        translate.setNode(b);
    }
    public static void ouvrirNouvellePage(String page,String title){
        Parent newParent = loadPane(page);
        newStage = new Stage();
        newStage.setTitle(title);
        System.out.println("well loaded");
        if (Objects.isNull(newParent)){
            System.out.println("loadPane a un souci");
        }else{
            newStage.setScene(new Scene(newParent));
            newStage.initOwner(stage);
            newStage.showAndWait();

        }
           }
    public static void showPages (String page ){

        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/interfaces/" + page));
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e )
        {
            e.printStackTrace();
        }

    }
    public static Parent loadPane(String page){
        Parent root = null ;
        try{
            root = FXMLLoader.load(Main.class.getResource("/interfaces/"+page+".fxml"));
        }catch (Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,"Erreur de chargement de la page");
            e.printStackTrace();
        }
        return root;
    }



    public static ArrayList<JSONObject> extractConsommations(JSONArray consommationValue, long idSupplyPoint)
    {
        ArrayList<JSONObject> consommations = new ArrayList<>();
        for (JSONObject obj : consommations){
            if (obj.getJSONObject("supplyPoint").getLong("id")==idSupplyPoint){
                consommations.add(obj);
            }
        }

        return consommations;
    }
}
