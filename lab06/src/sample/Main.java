package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane root = new FlowPane();
        primaryStage.setTitle("Lab06");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Housing");
        series1.getData().add(new XYChart.Data("2009", avgHousingPricesByYear[0]));
        series1.getData().add(new XYChart.Data("2010", avgHousingPricesByYear[1]));
        series1.getData().add(new XYChart.Data("2011", avgHousingPricesByYear[2]));
        series1.getData().add(new XYChart.Data("2012", avgHousingPricesByYear[3]));
        series1.getData().add(new XYChart.Data("2013", avgHousingPricesByYear[4]));
        series1.getData().add(new XYChart.Data("2014", avgHousingPricesByYear[5]));
        series1.getData().add(new XYChart.Data("2015", avgHousingPricesByYear[6]));
        series1.getData().add(new XYChart.Data("2016", avgHousingPricesByYear[7]));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Commercial");
        series2.getData().add(new XYChart.Data("2009", avgCommercialPricesByYear[0]));
        series2.getData().add(new XYChart.Data("2010", avgCommercialPricesByYear[1]));
        series2.getData().add(new XYChart.Data("2011", avgCommercialPricesByYear[2]));
        series2.getData().add(new XYChart.Data("2012", avgCommercialPricesByYear[3]));
        series2.getData().add(new XYChart.Data("2013", avgCommercialPricesByYear[4]));
        series2.getData().add(new XYChart.Data("2014", avgCommercialPricesByYear[5]));
        series2.getData().add(new XYChart.Data("2015", avgCommercialPricesByYear[6]));
        series2.getData().add(new XYChart.Data("2016", avgCommercialPricesByYear[7]));


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(ageGroups[0], purchasesByAgeGroup[0]),
                        new PieChart.Data(ageGroups[1], purchasesByAgeGroup[1]),
                        new PieChart.Data(ageGroups[2], purchasesByAgeGroup[2]),
                        new PieChart.Data(ageGroups[3], purchasesByAgeGroup[3]),
                        new PieChart.Data(ageGroups[4], purchasesByAgeGroup[4]),
                        new PieChart.Data(ageGroups[5], purchasesByAgeGroup[5]));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Purchase");
        chart.setLegendVisible(false);
        bc.setLegendVisible(false);
        System.out.println(pieColours[3].toString());
        applyCustomColourSequence(pieChartData);
        root.getChildren().addAll(bc, chart);
        bc.getData().addAll(series1, series2);
        primaryStage.setScene(new Scene(root, 1000 , 600));
        primaryStage.show();
    }
    private void applyCustomColourSequence(ObservableList<PieChart.Data> pieChartData) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColours[i] + ";");
            i++;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static double[] avgHousingPricesByYear = {
            247381.0, 264171.4, 287715.3, 294736.1,
            308431.4, 322635.9, 340253.0, 363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3, 1219479.5, 1246354.2, 1295364.8,
            1335932.6, 1472362.0, 1583521.9, 1613246.3
    };
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static String[] pieColours = {
            "aqua", "gold", "darkorange", "darksalmon", "lawngreen", "plum"
    };
};