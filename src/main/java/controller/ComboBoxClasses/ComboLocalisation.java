package controller.ComboBoxClasses;

import org.json.JSONObject;

public class ComboLocalisation {
    public String street;
    public String city;
    public Integer number;
    public Integer postal_code;
    public  Long id;
    public ComboLocalisation(JSONObject home){
        street = home.getString("street");
        number = home.getInt("number");
        city = home.getString("city");
        postal_code = home.getInt("postal_code");
        id = home.getLong("id");

    }

    public String toString(){
        return city+ "-"+street+"-"+number;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getPostal_code() {
        return postal_code;
    }

    public Long getId() {
        return id;
    }
}
