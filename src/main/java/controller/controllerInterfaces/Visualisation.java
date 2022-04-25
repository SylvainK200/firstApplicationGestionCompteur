package controller.controllerInterfaces;

import controller.ModelTabs.ConsommationTable;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

/**
 * Controlleur de la page de visualisation des consommations pour un compteur
 */
public class Visualisation {

    @FXML
    private LineChart linechart;

    public static String numeroCompteur = "112233445566778899";
    public static ArrayList<ConsommationTable> consommations = new ArrayList<>();
    public void initialize(){
        XYChart.Series series = new XYChart.Series();
        series.setName("consommations");

        consommations.forEach(conso->{
             series.getData().add(new XYChart.Data(
                    conso.getDate_lecture(),
                    Double.parseDouble(conso.getQuantiteConsommee())
                    )
            );
        });
        linechart.setTitle("Consommation du compteur d'EAN: " + consommations.get(0).getNumero_compteur());
        linechart.getData().add(series);
    }

}