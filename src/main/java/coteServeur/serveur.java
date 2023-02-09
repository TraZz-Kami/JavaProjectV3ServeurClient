package coteServeur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class serveur {
     private static final int PORT = 3306;
     private Socket socketClient = null;

     public static void main(String[] args) {
         ServerSocket serverSocket = null;
         Socket socket = null;
         DataOutputStream dos = null;
         DataInputStream dis = null;
         String nomDriverJDBC = "com.mysql.cj.jdbc.Driver";
         String urlDB = "jdbc:mysql://localhost:3306/mabd";
         Connection maCo = null;
         Statement monStatement=null;
         String requeteCreateLivres = "CREATE TABLE IF NOT EXISTS " +
                 "Livres (" +
                 "ID_Livre " + "INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                 "Titre " + "VARCHAR(100)," +
                 "Auteur " + "VARCHAR(100)," +
                 "DateEdition " + "DATE" +
                 ");";
         String requeteCreateLecteurs = "CREATE TABLE IF NOT EXISTS " +
                 "Lecteurs (" +
                 "ID_Lecteur " + "INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                 "Nom " + "VARCHAR(100)," +
                 "Prenom " + "VARCHAR(100)," +
                 "Livre_Lu " + "VARCHAR(500)" +
                 ");";
         try {
             serverSocket = new ServerSocket(PORT);
             System.out.println("Server is listening on port " + PORT);

             Class.forName(nomDriverJDBC);
             System.out.println("Chargement du Driver : OK");

             maCo = DriverManager.getConnection(urlDB,"root","root");
             System.out.println("Connexion élaborée");

             monStatement = maCo.createStatement();
             monStatement.executeUpdate(requeteCreateLivres);
             monStatement.executeUpdate(requeteCreateLecteurs);
             System.out.println("Requete sans select");

             socket = serverSocket.accept();
             System.out.println("Client connected to server.");

             dos = new DataOutputStream(socket.getOutputStream());
             dis = new DataInputStream(socket.getInputStream());

             while (true) {
                 String request = dis.readUTF();
                 if (request.equals("Insert data for Livres table")) {
                     String nom_livre = dis.readUTF();
                     String auteur = dis.readUTF();
                     System.out.println("je suis en insert livres");

                        // Insert data into Livres table
                        // ...
                      sendDataToClient("Data inserted into Livres table successfully", dos);
                 } else if (request.equals("Insert data for Lecteurs table")) {
                     String nom_lecteur = dis.readUTF();
                     String prenom_lecteur = dis.readUTF();

                        // Insert data into Lecteurs table
                        // ...
                      sendDataToClient("Data inserted into Lecteurs table successfully", dos);
                 } else {
                     sendDataToClient("Invalid request", dos);
                 }
             }
         } catch (IOException | ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         } finally {
             try {
                 if (dos != null) {
                     dos.close();
                 }
                 if (dis != null) {
                     dis.close();
                 }
                 if (socket != null) {
                     socket.close();
                 }
                 if (serverSocket != null) {
                     serverSocket.close();
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }

     private String receiveDataFromClient() {
         String dataReceived = null;
         try {
             DataInputStream dis = new DataInputStream(socketClient.getInputStream());
             dataReceived = dis.readUTF();
             System.out.println("Data received from client: " + dataReceived);
         } catch (IOException e) {
             System.out.println("Error receiving data from client");
             e.printStackTrace();
             System.exit(-3);
         }
         return dataReceived;
     }

     private static void sendDataToClient(String dataToSend, DataOutputStream dos) {
         try {
             dos.writeUTF(dataToSend);
             dos.flush();
             System.out.println("Data sent to client: " + dataToSend);
         } catch (IOException e) {
             System.out.println("Error sending data to client");
             e.printStackTrace();
             System.exit(-3);
         }
     }
}
