package Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main extends Application {

    public static JSONObject currentClient ;
    public static JSONObject currentprovider;
    public static JSONObject current_supply_point;
    public static Stage  stage = new Stage();
    public static void main(String[] args){
        launch(args);
    }
    public static void showPages (String page ){

        try {
            Parent root = FXMLLoader.load(Main.class.getResource("" + page));
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e )
        {
            e.printStackTrace();
        }

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
