package controller.Methods;

import Gui.Main;
import javafx.scene.control.Alert;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static Gui.Main.JSON;

/**
 * Implementation de l'interface GeneralMethods qui contient des implementations des methodes GeneralMethods
 */
public class GeneralMethodsImpl implements GeneralMethods{
    public  static String API_URL = "https://energy-management-be.herokuapp.com/energy-management/";
    //public  static String API_URL = "http://localhost:8085/energy-management/";

    @Override
    public JSONObject createObject(JSONObject contract, String url) {

        JSONObject resp = new JSONObject();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody formBody = RequestBody.create(JSON, contract.toString());
        Request request = new Request.Builder()
                .url(API_URL + url)
                .post(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                return new JSONObject(response.body().string());
            }
            response.close();
        }
        catch (Exception e){
            e.printStackTrace();}
        return resp;
    }

    public JSONObject updateObject(JSONObject contract,String url) {

        JSONObject resp = new JSONObject();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody formBody = RequestBody.create(JSON, contract.toString());
        Request request = new Request.Builder()
                .url(API_URL + url)
                .put(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){

                String title = "Mise a jour";
                String label = "Operation de Mise a jour reussie";
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setContentText(label);
                alert.show();
                return new JSONObject(response.body().string());
            }
            response.close();
        }
        catch (Exception e){
            e.printStackTrace();}
        return resp;
    }

    public JSONObject deleteObject (String url) {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(API_URL+url)
                .method("DELETE", null)
                .build();

        try {
            Response response = client.newCall(request).execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new JSONObject();

    }

    public JSONObject findUnique(String url){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(API_URL+url)
                .method("GET", null)
                .build();
        JSONObject result = new JSONObject();
        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            if (response.isSuccessful() && res!="")
            {
                result= new JSONObject(res);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public JSONArray find(String url){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(API_URL+url)
                .method("GET", null)
                .build();
        JSONArray result = null;
        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            if (res !="")
            {
                result= new JSONArray(res);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JSONObject signin(String identifiant, String password) {
        password = password.replace("/", "-");
        String url = "" ;
        url = API_URL + "user/identifiant/"+identifiant+"/"+password;
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject user = new JSONObject(response.body().string());
            response.close();
            return user;
        } catch (JSONException e) {
            Main.afficherAlert("Vos identifiants semblent incorrets! Verifiez les et reessayer.");
            this.log(this.getClass().getName(), "GeneralMethodsImpl.java -> login()");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void log(String classname,String logs){
        Logger logger = Logger.getLogger(classname);
        logOperation(logger, logs, "");
    }

    public void logOperation(Logger logger, String operationWarning, String operationSevere) {
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
}
