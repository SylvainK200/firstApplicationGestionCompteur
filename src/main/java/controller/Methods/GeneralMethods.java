package controller.Methods;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Interface de methodes pour l'acces aux donnees via l'API
 */
public interface GeneralMethods {

    public  JSONObject createObject(JSONObject object, String url);
    public JSONObject updateObject(JSONObject object,String url);
    public  JSONObject deleteObject (String url);
    public JSONObject findUnique(String url);
    public  JSONArray find(String url);

    public JSONObject signin(String identifiant, String password);
    public void log(String classname,String logs);
}
