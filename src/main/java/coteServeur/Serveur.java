package coteServeur;

import Model.Livre;
import Model.Lecteur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(3307);
            System.out.println("Listening on port : 3307");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 3307.");
            System.exit(1);
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

    public static void addLecteurToDB(Lecteur lecteur){
        String dbURL = "jdbc:mysql://localhost:3306/mabd";
        String username = "root";
        String password = "";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "INSERT INTO lecteurs (nom, prenom) VALUES (?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, lecteur.getNomLecteur());
            stmt.setString(2, lecteur.getPrenomLecteur());
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

    public static List<Lecteur> getLecteursFromDB() {
        String dbURL = "jdbc:mysql://localhost:3306/mabd";
        String username = "root";
        String password = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Lecteur> lecteurs = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "SELECT * FROM lecteurs";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");

                Lecteur lecteur = new Lecteur(nom, prenom);
                lecteurs.add(lecteur);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return lecteurs;
    }

}
