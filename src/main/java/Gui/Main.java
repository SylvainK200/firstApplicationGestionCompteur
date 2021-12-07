package Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    public static JSONObject currentClient ;
    public static JSONObject currentprovider;
    public static Stage  stage = new Stage();
    public static void main(String[] args){
        launch(args);
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
            e.printStackTrace();
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,"Erreur de chargement de la page");
        }
        return root;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        showPages("login.fxml");
    }

    public static void startForTests(Stage primaryStage) throws Exception{
        stage=primaryStage;
        showPages("login.fxml");
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
