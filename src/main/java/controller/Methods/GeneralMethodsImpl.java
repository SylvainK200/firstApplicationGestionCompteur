package controller.Methods;

import javafx.scene.control.Alert;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;

import static Gui.Main.JSON;

/**
 * Implementation de l'interface GeneralMethods qui contient des implementations des methodes GeneralMethods
 */
public class GeneralMethodsImpl implements GeneralMethods{
    public  static String API_URL = "https://energy-management-be.herokuapp.com/energy-management/";
    //public  static String API_URL = "http://localhost:8085/energy-management/";

    @Override
    public JSONObject createObject(JSONObject contract, String url) {

        System.out.println("CREATE : Contact de la route "+url);
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

        System.out.println("UPDATE : Contact de la route "+url);
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

        System.out.println("DELETE : Contact de la route "+url);
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
        System.out.println("FINDUNIQUE : Contact de la route "+url);
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
            System.out.println("resultat "+res);
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
        System.out.println("FIND : Contact de la route "+url);
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
            System.out.println(res);
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

    /*@Override
    public JSONArray findManyWithBody(JSONObject body, String url){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody formBody = RequestBody.create(JSON, body.toString());

        Request request = new Request.Builder()
                .url(API_URL+url)
                .method("GET",formBody)
                .build();
        JSONArray result = null;
        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            System.out.println(res);
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
    public JSONObject findWithBody(JSONObject body, String url){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody formBody = RequestBody.create(JSON, body.toString());

        Request request = new Request.Builder()
                .url(API_URL+url)
                .method("GET",formBody)
                .build();
        JSONObject result = null;
        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            System.out.println(res);
            if (res !="")
            {
                result= new JSONObject(res);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
*/
}
