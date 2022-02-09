package controller.Methods;

import controller.ModelTabs.MenuPrincipalTable;
import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ImportationExportationImpl implements ImportationExportation{
    GeneralMethods generalMethods = new GeneralMethodsImpl();
    public void exportToCSV(File file, List<MenuPrincipalTable> elts) {
        final String DELIMITER = ";";
        final String SEPARATOR = "\n";

        //En-tête de fichier
        final String HEADER = "EAN;Consommation;cout;compteur;date_affectation;date_cloture;name_wallet";
        try{
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(HEADER);
            fileWriter.append(SEPARATOR);
            Iterator it = elts.iterator();

            while (it.hasNext()){
                MenuPrincipalTable elt =(MenuPrincipalTable) it.next();

                fileWriter.append(elt.getEan_18());
                fileWriter.append(DELIMITER);
                fileWriter.append(""+elt.getConsommation());
                fileWriter.append(DELIMITER);
                fileWriter.append(""+elt.getCout());
                fileWriter.append(DELIMITER);
                fileWriter.append(""+elt.getType_compteur());
                fileWriter.append(DELIMITER);
                fileWriter.append(""+elt.getDate_affectation());
                fileWriter.append(DELIMITER);
                fileWriter.append(""+elt.getDate_cloture());
                fileWriter.append(DELIMITER);
                fileWriter.append(""+elt.getNameWallet());
                fileWriter.append(SEPARATOR);

            }
            fileWriter.close();
        }catch (Exception e ){
            e.printStackTrace();
        }

    }
    public void importerFileCSV(File file,String typeCompteur){
        //JSONObject compteur = findUnique("supplyPoint/ean_18/"+compteur_importer.getValue());
        try {
            JSONObject result = new JSONObject() ;
            Scanner sc = new Scanner(file);
            sc.useDelimiter("\n");
            int i = 0 ;
            int j = 0;
            while (sc.hasNext())
            {

                if (i > 0) {
                    String ligne = sc.next();
                    System.out.println(ligne);
                    String[] elts = ligne.split(";");



                    if (i==1){
                        JSONObject compteur = new JSONObject();
                        compteur.put("ean_18",elts[0]);
                        compteur.put("energy",typeCompteur);
                        result = generalMethods.createObject(compteur,"supplyPoint");
                        i+=1;
                    }
                    String newElement = elts[2].substring(0,5);
                    enregistrer (result,elts[1],Integer.parseInt(newElement));

                }
                else {
                    i++;
                    String ligne = sc.next();
                }

            }
            sc.close();
            JOptionPane.showMessageDialog(null,"Importation terminee");
        }catch (Exception e ) {
            e.printStackTrace();
        }

    }
    public void enregistrer(JSONObject result,String date,long index){

        JSONObject consommationValue = new JSONObject();


        consommationValue.put("value",index);
        try{
            consommationValue.put("date", Date.valueOf(date));
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(" result : " + result.toString());
        consommationValue.put("supplyPoint",result);
        JSONObject result2 = generalMethods.createObject(consommationValue,"consommationValue");

        if (!result2.isEmpty()){
            System.out.println(result2);
        }
    }

}