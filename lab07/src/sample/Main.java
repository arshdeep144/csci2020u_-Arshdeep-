package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 07");
        String csvFile = "C:\\Users\\Arsh\\Downloads/weatherwarnings-2015.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int[] count = new int[4];
        for (int i = 0; i < 3; i++){
            count[i] = 0;
        }
        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {

            // use comma as separator
            String[] country = line.split(cvsSplitBy);
            if (country[5].equals("FLASH FLOOD")){
                count[0] += 1;
            }
            else if(country[5].equals("SEVERE THUNDERSTORM")){
                count[1] += 1;
            }
            else if(country[5].equals("SPECIAL MARINE")){
                count[2] += 1;
            }
            else if(country[5].equals("TORNADO")){
                count[3] += 1;
            }

        }


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("FLASH FLOOD", count[0]),
                        new PieChart.Data("SEVERE THUNDERSTORM", count[1]),
                        new PieChart.Data("SPECIAL MARINE", count[2]),
                        new PieChart.Data("TORNADO", count[3]));
        final PieChart chart = new PieChart(pieChartData);
        chart.setLegendSide(Side.LEFT);
        chart.setLabelsVisible(false);
        root.getChildren().add(chart);
        primaryStage.setScene(new Scene(root, 900, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
