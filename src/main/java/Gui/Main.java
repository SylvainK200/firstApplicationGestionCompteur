package Gui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import controller.Methods.GeneralMethods;
import controller.ModelTabs.HistoriqueTable;
import controller.ModelTabs.UserTest;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    public static JSONObject currentClient ;
    public static JSONObject currentprovider;
    public static Stage  stage = new Stage();
    public static Stage newStage;
    public static void main(String[] args) throws JsonProcessingException {

        UserTest userTest = new UserTest("kouemo","sylvain");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(userTest);
        System.out.println(json);
        JSONArray array = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","kouemal");
        array.put(json);
        array.put(jsonObject);
        System.out.println(array);
        launch(args);
    }

    public static void ajouterInteractionAuClic(Button b){
        TranslateTransition translate = new TranslateTransition();
        translate.setByX(5);
        translate.setDuration(Duration.millis(200));
        translate.setNode(b);
    }
    public static void ouvrirNouvellePage(String page){
        Parent newParent = loadPane(page);
        newStage.setScene(new Scene(newParent));
        newStage.initOwner(stage);
        newStage.showAndWait();
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
