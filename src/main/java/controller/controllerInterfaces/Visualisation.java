package controller.controllerInterfaces;

import controller.ModelTabs.ConsommationTable;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

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
                    conso.getQuantiteConsommee()
                    )
            );
        });

        linechart.setTitle("Consommation du compteur : " + numeroCompteur);
        linechart.getData().add(series);
    }

}