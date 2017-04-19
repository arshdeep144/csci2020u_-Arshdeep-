package dataviz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import java.io.*;
import java.net.*;

public class Main extends Application {
    private static String[] barColours = {
        "red", "blue", "orange", "green", "yellow"
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("DataViz v1.0");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final  BarChart<String,Number> bc = new BarChart<String, Number>(xAxis,yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("1 bed flats");
        series1.getData().add(new XYChart.Data());
        XYChart.Series series2 = new XYChart.Series();
        series1.setName("2 bed flats");
        XYChart.Series series3 = new XYChart.Series();
        series1.setName("2 bed houses");
        XYChart.Series series4 = new XYChart.Series();
        series1.setName("3 bed houses");
        XYChart.Series series5 = new XYChart.Series();
        series1.setName("4 bed houses");
        primaryStage.setScene(new Scene(bc, 300, 275));
        primaryStage.show();
    }

    private void drawLegend() {
    }

    public static void main(String[] args) {
        launch(args);
    }
}
