package coteServeur;

import Model.Livre;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Serveur {
    static String nomDriverJDBC = "com.mysql.cj.jdbc.Driver";
    static String urlDB = "jdbc:mysql://localhost:3306/mabd";
    static Connection maCo = null;
    static Statement monStatement=null;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(3307);
            System.out.println("Listening on port : 3307");

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
            Class.forName(nomDriverJDBC);
            System.out.println("Chargement du Driver : OK");

            maCo = DriverManager.getConnection(urlDB,"root","");
            System.out.println("Connexion élaborée");

            monStatement = maCo.createStatement();
            monStatement.executeUpdate(requeteCreateLivres);
            monStatement.executeUpdate(requeteCreateLecteurs);
            System.out.println("Requete sans select");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 3307.");
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Socket clientSocket = null;
        try {
            System.out.println("Waiting for client to connect...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        System.out.println("Client connected.");
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received message: " + inputLine);
            outputLine = "ACK: " + inputLine;
            out.println(outputLine);
            if (inputLine.equals("Bye."))
                break;
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
     }

     /*private String receiveDataFromClient() {
         String dataReceived = null;
         try {
            // DataInputStream dis = new DataInputStream(socketClient.getInputStream());
             dataReceived = //dis.readUTF();
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
     }*/

    public static void addLivreToDB(Livre livre){
        String dbURL = "jdbc:mysql://localhost:3306/mabd";
        String username = "root";
        String password = "";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "INSERT INTO livres (titre, Auteur) VALUES (?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getNomAuteur() + " " + livre.getPrenomAuteur());
            stmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
