package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    TableView<StudentRecord> table;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Lab 05 Solutions");
        DataSource data = new DataSource();

        //SID column
        TableColumn<StudentRecord, String> sidColumn = new TableColumn<>("SID");
        sidColumn.setMinWidth(100);
        sidColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        //Assignment column
        TableColumn<StudentRecord, String> assColumn = new TableColumn<>("Assignments");
        assColumn.setMinWidth(100);
        assColumn.setCellValueFactory(new PropertyValueFactory<>("assignments"));

        //Midterm column
        TableColumn<StudentRecord, String> midColumn = new TableColumn<>("Midterm");
        midColumn.setMinWidth(100);
        midColumn.setCellValueFactory(new PropertyValueFactory<>("midterm"));

        //Final Exam column
        TableColumn<StudentRecord, String> examColumn = new TableColumn<>("Final Exam");
        examColumn.setMinWidth(100);
        examColumn.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        //Final Mark column
        TableColumn<StudentRecord, String> markColumn = new TableColumn<>("Final Mark");
        markColumn.setMinWidth(100);
        markColumn.setCellValueFactory(new PropertyValueFactory<>("finalMark"));

        //Letter Grade column
        TableColumn<StudentRecord, String> gradeColumn = new TableColumn<>("Letter Grade");
        gradeColumn.setMinWidth(100);
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        table = new TableView<>();
        table.setItems(data.getAllMarks());
        table.getColumns().addAll(sidColumn, assColumn, midColumn, examColumn, markColumn, gradeColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }
}