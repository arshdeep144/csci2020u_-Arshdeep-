package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *Author: Arshdeep Benipal, 100591622
 *Date: March 30 2017
 */
public class Main extends Application {
    static int port = 80;
    static String host = "127.0.0.1";

    @Override
    public void start(Stage primaryStage) throws Exception {
        int nomOClients = 1;

        System.out.println("ClientConnectionServer listening on port " + port);
        ClientConnectionServer server = new ClientConnectionServer(port);
        server.start();

        Stage[] stage = new Stage[nomOClients];
        ClientConnection client[] = new ClientConnection[nomOClients];
        Scene scene[] = new Scene[nomOClients];

        //creates a server for each client and then allows each client to access
        //the same server in which they can share via upload or download
        for (int i = 0; i < nomOClients; i++) {
            stage[i] = new Stage();
            client[i] = new ClientConnection(port, host, stage[i], i + 1);
            client[i].start();
            client[i].join();
            scene[i] = new Scene(client[i].getLayout(), 600, 600);
            stage[i].setScene(scene[i]);
            stage[i].show();
            stage[i].setOnCloseRequest(e -> {
                boolean close = true;
                for (int j = 0; j < nomOClients; j++) {
                    if (client[j].isAlive()) {
                        close = false;
                    }
                }
                if (close) {
                    server.quit();
                }
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}