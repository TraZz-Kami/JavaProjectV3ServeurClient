package coteServeur;

import model.Livre;
import model.Lecteur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
    static String nomDriverJDBC = "com.mysql.cj.jdbc.Driver";
    static String urlDB = "jdbc:mysql://localhost:3306/mabd";
    static String usernameDB = "root";
    static String passwordDB = "";
    static Connection maCo = null;
    static Statement monStatement=null;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(3307);
            System.out.println("Listening on port : 3307");

            //Créer les tables si ce n'est pas déjà fait
            String requeteCreateLivres = "CREATE TABLE IF NOT EXISTS " +
                    "Livres (" +
                    "ID_Livre " + "INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "Titre " + "VARCHAR(100)," +
                    "Auteur " + "VARCHAR(100)" +
                    ");";
            String requeteCreateLecteurs = "CREATE TABLE IF NOT EXISTS " +
                    "Lecteurs (" +
                    "ID_Lecteur " + "INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "Nom " + "VARCHAR(100)," +
                    "Prenom " + "VARCHAR(100)" +
                    ");";
            Class.forName(nomDriverJDBC);
            System.out.println("Chargement du Driver : OK");

            maCo = DriverManager.getConnection(urlDB,usernameDB,passwordDB);
            System.out.println("Connexion élaborée");

            monStatement = maCo.createStatement();
            monStatement.executeUpdate(requeteCreateLivres);
            monStatement.executeUpdate(requeteCreateLecteurs);
            System.out.println("Requete création des tables si elle n'existe pas");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 3307.");
            System.exit(1);
        } catch (SQLException | ClassNotFoundException e) {
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
     }


     // Permet d'ajouter un livre dans la bdd
    public static void addLivreToDB(Livre livre){

        PreparedStatement monStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            maCo = DriverManager.getConnection(urlDB, usernameDB, passwordDB);

            String sql = "INSERT INTO Livres (Titre, Auteur) VALUES (?, ?)";

            monStatement = maCo.prepareStatement(sql);
            monStatement.setString(1, livre.getTitre());
            monStatement.setString(2, livre.getAuteur());
            monStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (monStatement != null) {
                    monStatement.close();
                }
                if (maCo != null) {
                    maCo.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Permet d'ajouter un lecteur dans la bdd
    public static void addLecteurToDB(Lecteur lecteur){

        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            maCo = DriverManager.getConnection(urlDB, usernameDB, passwordDB);

            String sql = "INSERT INTO Lecteurs (Nom, Prenom) VALUES (?, ?)";

            stmt = maCo.prepareStatement(sql);
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
                if (maCo != null) {
                    maCo.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Permet de récupérer les lecteurs présent dans la bdd
    public static List<Lecteur> getLecteursFromDB() {

        ResultSet rs = null;

        List<Lecteur> lecteurs = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            maCo = DriverManager.getConnection(urlDB, usernameDB, passwordDB);

            String sql = "SELECT * FROM Lecteurs";

            monStatement = maCo.createStatement();
            rs = monStatement.executeQuery(sql);

            while (rs.next()) {
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                int id = rs.getInt("ID_Lecteur");

                Lecteur lecteur = new Lecteur(prenom, nom,id);
                lecteurs.add(lecteur);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (monStatement != null) {
                    monStatement.close();
                }
                if (maCo != null) {
                    maCo.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lecteurs;
    }

    // Permet de récupérer les livres dans la bdd
    public static List<Livre> getLivresFromBD() {

        ResultSet rs = null;

        List<Livre> livres = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            maCo = DriverManager.getConnection(urlDB, usernameDB, passwordDB);

            String sql = "SELECT * FROM Livres";

            monStatement = maCo.createStatement();
            rs = monStatement.executeQuery(sql);

            while (rs.next()) {
                String titre = rs.getString("Titre");
                String auteur = rs.getString("Auteur");
                int id = rs.getInt("ID_Livre");


                Livre livre = new Livre(id,titre, auteur);
                livres.add(livre);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (monStatement != null) {
                    monStatement.close();
                }
                if (maCo != null) {
                    maCo.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return livres;
    }
}

