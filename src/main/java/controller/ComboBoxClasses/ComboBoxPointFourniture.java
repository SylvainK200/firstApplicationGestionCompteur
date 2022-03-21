package controller.ComboBoxClasses;


import org.json.JSONObject;

/**
 * Il s'agit du combobox qui permettra de traduire une JSONObject PointFourniture en objet pour le combobox
 */
public class ComboBoxPointFourniture {
    public String ean;
    public String name;
    public String energy;
    public String provider_name;

    public ComboBoxPointFourniture (JSONObject pointFourniture){
        ean = pointFourniture.getString("ean_18");
        name = pointFourniture.getString("name");
        energy = pointFourniture.getString("energy");
        provider_name = pointFourniture.getJSONObject("provider").getString("company_name");
    }

    public String toString(){
        return this.ean;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }
}
