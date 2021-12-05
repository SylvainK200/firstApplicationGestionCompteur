package controller.Methods;


import controller.ModelTabs.MenuPrincipalTable;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

public interface ImportationExportation {
    public void importerFileCSV(File file, String typeCompteur);
    public  void enregistrer(JSONObject result, String date, long index);
    public  void exportToCSV(File file, List<MenuPrincipalTable> elts);
}
