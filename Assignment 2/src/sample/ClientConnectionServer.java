package sample;

import java.io.IOException;
import java.net.*;
import java.util.Vector;

/**
 *Author: Arshdeep Benipal, 100591622
 *Date: March 30 2017
 */
public class ClientConnectionServer extends Thread{
    private ServerSocket serverSocket;

    public ClientConnectionServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    //Starts up the server
    public void handleRequests() throws IOException {
        try {
            int i = 0;
            Vector<Thread> handlerThread = new Vector<Thread>();
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                handlerThread.add(i, new Thread(new ClientConnectionHandler(socket)));
                handlerThread.get(i).start();
                i++;
            }
        } catch (IOException e) {}
    }
    // Helps let the thread close
    public void quit() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Socket Closed");
        }
    }

    @Override
    public void run() {
        try {
            this.handleRequests();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}