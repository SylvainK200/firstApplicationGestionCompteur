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
import okhttp3.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Gui.Encryption.encrypt;

/**
 * Classe principale Main
 */
public class Main extends Application {

    public static JSONObject currentClient ;
    public static Stage  stage = new Stage();
    public static Stage newStage = new Stage();
    public static String firstpage = "login.fxml";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static GeneralMethods generalMethods = new GeneralMethodsImpl();
    public static Logger LOGGER = Logger.getLogger(Main.class.getName());
    private final static String keyCrypt = "randomString1234@";
    public static String generateHash(String password){
        String encryptedPassword = password;
        try{
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = Encryption.createSecretKey(keyCrypt.toCharArray(), salt, iterationCount, keyLength);

        String originalPassword = password;
        encryptedPassword = encrypt(originalPassword, key);
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    return encryptedPassword;
    }

    /**
     *
     * @param logger il s'agit du logger du controlleur qui voudra afficher des logs
     * @param operationWarning message warning
     * @param operationSevere message severe
     */


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

    /**
     *
     * @param contentText contenu du message d'alert
     */
    public static void afficherAlert (String contentText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,contentText, ButtonType.OK);
        alert.showAndWait();;
    }

    /**
     * Methodes pour lancer la premiere page pour les tests
     * @param primaryStage Stage pour le lancement des tests
     * @throws Exception
     */
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

    /**
     *
     * @param walletName nom du portefeuille
     * @param username nom identifiant l'utilisateur
     * @return permet d'obtenir la permission sur un portefeuille
     */
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

    /**
     * Permet d'ouvir une nouvelle page dont le stage principage est le proprietaire
     * @param page lien vers le fichier fxml
     * @param title titre du stage
     */
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

    /**
     * permet d'afficher une nouvelle page
     * @param page
     */
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
