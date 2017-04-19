package sample;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.*;
import javafx.fxml.FXML;

public class Controller {
    @FXML private TreeView<String> projectTreeView;
    @FXML private TextArea edit;
    public void initialize() {
    }
    public void onOpenFolder(){
        DirectoryChooser chooser = new DirectoryChooser();
        File defaultDirectory = new File("C://");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog()
        InputStream in = new InputStream() {
            @Override
            public int read() throws IOException {

                return 0;
            }
        };
    }
    public void onSaveFile(){

    }
    public void onExit(){
        EventHandler ev = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){

            }
        }

    }
}
