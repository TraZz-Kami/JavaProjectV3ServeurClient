package coteClient;

import Model.Livre;
import Model.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private static final int PORT = 3306;
    private static final String HOST = "localhost";
    private DataOutputStream dos;
    private DataInputStream dis;


    public void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket(HOST, PORT);
            System.out.println("Client connected to server.");

            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

            // Send request to server
            sendDataToServer("Insert data for Livres table", dos);
            sendDataToServer("nom_livre", dos);
            sendDataToServer("auteur", dos);

            // Read response from server
            String response = receiveDataFromServer(dis);
            System.out.println("Server says: " + response);

            // Send request to server
            sendDataToServer("Insert data for Lecteurs table", dos);
            sendDataToServer("nom_lecteur", dos);
            sendDataToServer("prenom_lecteur", dos);

            // Read response from server
            response = receiveDataFromServer(dis);
            System.out.println("Server says: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLivre(Livre livre){
        sendDataToServer("id", dos);
        sendDataToServer(livre.getTitre(), dos);
        sendDataToServer(livre.getNomAuteur(), dos);
        sendDataToServer(livre.getPrenomAuteur(), dos);
    }

    public void loginClient(User user){
        sendDataToServer("id", dos);
        sendDataToServer(user.getPseudo(), dos);
        sendDataToServer(user.getPassword(), dos);
    }

    public void signInClient(User user){
        sendDataToServer("id", dos);
        sendDataToServer(user.getPseudo(), dos);
        sendDataToServer(user.getPassword(), dos);
    }

    public static List<Livre> showClientLivre(){
        List<Livre> dataReceived = new ArrayList<>();;
        dataReceived.add(new Livre("test","test","test"));
        return dataReceived;
    }


    private static void sendDataToServer(String dataToSend, DataOutputStream dos) {
        try {
            dos.writeUTF(dataToSend);
            dos.flush();
            System.out.println("Data sent to server: " + dataToSend);
        } catch (IOException e) {
            System.out.println("Error sending data to server");
            e.printStackTrace();
            System.exit(-3);
        }
    }

    private static String receiveDataFromServer(DataInputStream dis) {
        String dataReceived = null;
        try {
            dataReceived = dis.readUTF();
            System.out.println("Data received from server: " + dataReceived);
        } catch (IOException e) {
            System.out.println("Error receiving data from server");
            e.printStackTrace();
            System.exit(-3);
        }
        return dataReceived;
    }
}


