package controller.Methods;

import org.json.JSONArray;
import org.json.JSONObject;

public interface GeneralMethods {

    public  JSONObject createObject(JSONObject object, String url);
    public JSONObject updateObject(JSONObject object,String url);
    public  JSONObject deleteObject (String url);
    public JSONObject findUnique(String url);
    public  JSONArray find(String url);
}
