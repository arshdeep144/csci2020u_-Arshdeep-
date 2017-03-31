package sample;

import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;


/**
 *Author: Arshdeep Benipal, 100591622
 *Date: March 30 2017
 */
public class ClientConnection extends Thread {
    private Socket socket;
    private BufferedReader fin;
    private PrintWriter fout;
    private BorderPane layout;
    private Stage primaryStage;
    private ObservableList<String> observServList, observClieList;
    private int port, cNum;
    private String host, clientStorageRoot;

    public ClientConnection(int port, String host, Stage stage, int cNum) {
        this.port = port;
        this.host = host;
        this.primaryStage = stage;
        this.cNum = cNum;
        this.clientStorageRoot = "Client";
    }
    // Creates the GUI for the program
    @Override
    public void run() {
        primaryStage.setTitle("Client: "+cNum);

        ListView<String> list = new ListView<String>();
        list.setEditable(true);

        ListView<String> serverList = new ListView<String>();
        serverList.setEditable(true);

        GridPane editArea = new GridPane();

        Button uploadButton = new Button("Upload");
        uploadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (list.getEditingIndex() != -1) {
                    uploadFileCmd(observClieList.get(list.getEditingIndex()));
                    System.out.println("Done Uploading");
                }
                updateList(list, serverList);
            }
        });
        editArea.add(uploadButton, 0, 0);

        Button downloadButton = new Button("Download");
        downloadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (serverList.getEditingIndex() != -1) {
                    downloadFileCmd(observServList.get(serverList.getEditingIndex()));
                    System.out.println("Done Downloading");
                }
                updateList(list, serverList);
            }
        });
        editArea.add(downloadButton, 1, 0);

        TextField cl = new TextField("Client");
        editArea.add(cl,0,1);

        TextField ser = new TextField("Server");
        editArea.add(ser,3,1);

        updateList(list, serverList);
        SplitPane fileView = new SplitPane(list, serverList);
        fileView.setDividerPositions(0.50);

        layout = new BorderPane();
        layout.setTop(editArea);
        layout.setCenter(fileView);
    }
    //sends DIR command, receives list of files in server storage
    public synchronized ObservableList<String> sendDIRCmd() {
        try {
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("DIR");
            out.flush();
            String response;
            response = in.readLine();
            String[] responseParts = response.split(" ");
            ObservableList<String> tempList = FXCollections.observableArrayList();
            for (int i = 0; i < responseParts.length; i++) {
                tempList.add(responseParts[i]);
            }
            out.close();
            in.close();
            socket.close();
            return tempList;
        } catch (IOException e) {}
        return null;
    }
    //sends UPLOAD command
    public void uploadFileCmd(String fileName) {
        try {
            socket = new Socket(host, port);
            fin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            fout = new PrintWriter(socket.getOutputStream());
            fout.println("UPLOAD " + "/" + fileName);
            fout.flush();

            String toSend = "", line;
            File file = new File(clientStorageRoot, fileName);
            BufferedReader fin = new BufferedReader(new FileReader(file));
            while ((line = fin.readLine()) != null) {
                toSend += line;
                toSend += "\n";
            }
            fout.print(toSend);
            fout.flush();
            fout.close();
            fin.close();
            socket.close();
        } catch (IOException e) {}
    }
    //sends DOWNLOAD command
    public  void downloadFileCmd(String fileName) {
        try {
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("DOWNLOAD " + "/" + fileName);
            out.flush();
            String response;
            File newFile = new File("Client", fileName);
            if (!newFile.exists()) {
                newFile.createNewFile();
            } else {
                newFile.delete();
                newFile.createNewFile();
            }
            PrintWriter fout = new PrintWriter(newFile);
            while ((response = in.readLine()) != null) {
                fout.println(response);
            }
            fout.close();
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {}
    }
    // Lists files on local side returns the observable list
    public ObservableList<String> listFiles() {
        File clientStorage = new File("Client");
        if (!clientStorage.isDirectory()) {
            clientStorage.mkdir();
        }
        ObservableList<String> tempList = FXCollections.observableArrayList();
        File fileList[] = clientStorage.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            tempList.add(fileList[i].getName());
        }
        return tempList;
    }

    // Updates observable list and calls listFiles and sendDIRCmd
    public void updateList(ListView<String> clientList, ListView<String> serverList) {
        observClieList = listFiles();
        observServList = sendDIRCmd();
        clientList.setItems(observClieList);
        serverList.setItems(observServList);
        System.out.println("Done Updating");
    }
     // Returns layout value used for scene
    public BorderPane getLayout() {
        return this.layout;
    }
}