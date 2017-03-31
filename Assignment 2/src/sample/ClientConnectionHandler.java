package sample;

import java.io.*;
import java.net.Socket;

/**
 *Author: Arshdeep Benipal, 100591622
 *Date: March 30 2017
 */
public class ClientConnectionHandler implements Runnable {
    public static String ROOT = "Server";
    private Socket socket;
    private PrintWriter fout;

    public ClientConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            OutputStream os = socket.getOutputStream();
            fout = new PrintWriter(os);
            String request = null;
            while (request == null) {
                request = in.readLine();
            }
            String[] requestParts = request.split(" ");

            String command = requestParts[0];
            if (command.equalsIgnoreCase("DIR")) {
                cmdDIR();
            }else if (command.equalsIgnoreCase("UPLOAD")){
                cmdUPLOAD(requestParts[1]);
            }else if (command.equalsIgnoreCase("DOWNLOAD")){
                cmdDOWNLOAD(requestParts[1]);
            } else {
                System.out.println ("CMD not found.");
            }
            socket.close();
        }catch (FileNotFoundException e){
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //sends list of files in a string
    private void cmdDIR(){
        String toSend = "";
        File baseDir = new File(ROOT);
        File fileList[] = baseDir.listFiles();
        for (int i = 0; i< fileList.length; i++){
            toSend += fileList[i].getName();
            if (i != (fileList.length - 1)){
                toSend += " ";
            }
        }
        fout.print(toSend);
        fout.flush();
    }
    //sends server item selected by client
    private void cmdUPLOAD(String fileName) throws IOException{
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String response;
            File tempFile = new File("ServerStorage", fileName);
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            } else {
                tempFile.delete();
                tempFile.createNewFile();
            }
            PrintWriter fout = new PrintWriter(tempFile);
            while ((response = in.readLine()) != null) {
                fout.println(response);
            }
            fout.close();
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    //sends client selected item in server
    private void cmdDOWNLOAD(String fileName) throws IOException{
        String toSend = "", line = "";
        File file = new File(ROOT, fileName);
        BufferedReader in = new BufferedReader(new FileReader(file));
        while ((line = in.readLine()) != null){
            toSend += line;
            toSend += "\n";
        }
        fout.print(toSend);
        fout.flush();
    }
}