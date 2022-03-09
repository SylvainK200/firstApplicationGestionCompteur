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
        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("March", 15));
        series.getData().add(new XYChart.Data("April", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("June", 36));
        series.getData().add(new XYChart.Data("July", 22));
        series.getData().add(new XYChart.Data("Aug", 45));
        series.getData().add(new XYChart.Data("Sep", 43));
        series.getData().add(new XYChart.Data("Oct", 17));
        series.getData().add(new XYChart.Data("Nov", 29));
        series.getData().add(new XYChart.Data("Dec", 25));

        linechart.setTitle("Consommation du compteur : " + numeroCompteur);
        linechart.getData().add(series);
    }

}