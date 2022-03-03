package controller.ComboBoxClasses;

import org.json.JSONObject;

public class ComboboxHome {
    private Long id;

    private String street;

    private Integer number;

    private String city;

    private String postal_code;

    public String toString(){
        return  this.street+ "-" + this.number;
    }

    public ComboboxHome(JSONObject home) {
        street = home.getString("street");
        number = home.getInt("number");
        city = home.getString("city");
        postal_code = home.getString("postal_code");

    }
}
