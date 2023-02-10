package coteClient;

import model.Lecteur;
import model.Livre;
import coteServeur.Serveur;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static Scanner sc = new Scanner(System.in);
    private static Socket socket;
    private static DataInputStream dis;
    private static DataOutputStream dos;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 3307);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static boolean connectClient() {
        try {
            socket = new Socket("localhost", 3307);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected static void addLivre(){
        String titre;
        String auteur;

        System.out.println();
        System.out.println("----- Ajouter un livre -----");
        System.out.print("Le titre: ");
        titre = sc.nextLine();
        System.out.print("Le prenom de l'auteur: ");
        String prenomAuteur = sc.nextLine();
        System.out.print("Le nom de l'auteur: ");
        String nomAuteur = sc.nextLine();
        auteur = nomAuteur + " " + prenomAuteur;


        Livre livre = new Livre(titre, auteur);
        Serveur.addLivreToDB(livre);
    }

    protected static void showLivres(){
        // add code to receive the list of books from the server side
        System.out.println();
        System.out.println("----- Afficher un livre -----");
        List<Livre> livreList = Serveur.getLivresFromBD();
        if(livreList.isEmpty()){
            System.out.println("Il n'y a pas de lecteur d'enregistrer");
        }else {
            for (Livre livre : livreList) {
                System.out.println(livre);
            }
        }
    }

    protected static void addLecteur(){
        String prenomAuteur;
        String nomAuteur;

        System.out.println();
        System.out.println("----- Ajouter un auteur -----");
        System.out.print("Le prenom de l'auteur: ");
        prenomAuteur = sc.nextLine();
        System.out.print("Le nom de l'auteur: ");
        nomAuteur = sc.nextLine();

        Lecteur lecteur = new Lecteur(prenomAuteur, nomAuteur);
       Serveur.addLecteurToDB(lecteur);
    }

    protected static void showLecteurs(){
        System.out.println();
        System.out.println("----- Afficher un lecteur -----");
        List<Lecteur> listLecteur = Serveur.getLecteursFromDB();
        if(listLecteur.isEmpty()){
            System.out.println("Il n'y a pas de lecteur d'enregistrer");
        }else {
            for (Lecteur lecteur : listLecteur) {
                System.out.println(lecteur);
            }
        }
    }

    protected static void disconnectClient() {
        try {
            dis.close();
            dos.close();
            socket.close();
            System.out.println("Disconnected from the server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



