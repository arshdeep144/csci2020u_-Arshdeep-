package sample;
import java.util.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;

public class Main extends Application{

    Stage window;
    private DatePicker checkInDatePicker;
    private TableView table = new TableView();
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //test
        Label username = new Label("username:");
        GridPane.setConstraints(username, 0,0);

        TextField nameInput = new TextField("");
        nameInput.setPromptText("username");
        GridPane.setConstraints(nameInput, 1, 0);

        Label passlabel = new Label("password");
        GridPane.setConstraints(passlabel , 0 ,1);

        TextField passField = new TextField();
        passField.setPromptText("password");
        GridPane.setConstraints(passField , 1 ,1);

        Label fullName = new Label("Full Name:");
        GridPane.setConstraints(fullName, 0,2);

        TextField fullNameInput = new TextField("");
        fullNameInput.setPromptText("Monkey D. Luffy");
        GridPane.setConstraints(fullNameInput, 1, 2);

        Label eMail = new Label("E-Mail:");
        GridPane.setConstraints(eMail, 0,3);

        TextField eMailInput = new TextField("");
        eMailInput.setPromptText("GUMGUM@FRUIT.COM");
        GridPane.setConstraints(eMailInput, 1, 3);

        Label phone = new Label("Phone Number:");
        GridPane.setConstraints(phone, 0,4);

        TextField phoneInput = new TextField("");
        phoneInput.setPromptText("123-456-7890");
        GridPane.setConstraints(phoneInput, 1, 4);

        Label date = new Label("Date of Birth:");
        GridPane.setConstraints(date, 0,5);
        checkInDatePicker = new DatePicker();
        Label checkInlabel = new Label("Check-In Date:");
        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        GridPane.setConstraints(checkInDatePicker, 1, 5);
        checkInDatePicker.setPromptText("MM/DD/YYYY");

        Button register = new Button("register");

        register.setOnAction(e -> System.out.println(nameInput.getText() + "\n" + passField.getText()  + "\n" + fullNameInput.getText()
                + "\n" + eMailInput.getText() + "\n" + phoneInput.getText()));
        GridPane.setConstraints(register , 0, 6	);




        grid.getChildren().addAll(username,nameInput,passlabel,passField,register,fullName,fullNameInput,eMail,eMailInput,
                phone,phoneInput,checkInDatePicker,date);

        Scene scene = new Scene(grid, 500,300);
        window.setScene(scene);
        window.show();

    }
}