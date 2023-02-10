package coteClient;

import Model.Auteur;
import Model.Livre;
import coteServeur.Serveur;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
        String prenomAuteur;
        String nomAuteur;

        System.out.println();
        System.out.println("----- Ajouter un livre -----");
        System.out.print("Le titre: ");
        titre = sc.nextLine();
        System.out.print("Le prenom de l'auteur: ");
        prenomAuteur = sc.nextLine();
        System.out.print("Le nom de l'auteur: ");
        nomAuteur = sc.nextLine();

        Livre livre = new Livre(titre, prenomAuteur, nomAuteur);
        Serveur.addLivreToDB(livre);
    }

    protected static void showLivres(){
        // add code to receive the list of books from the server side
    }

    protected static void addAuteur(){
        String prenomAuteur;
        String nomAuteur;

        System.out.println();
        System.out.println("----- Ajouter un auteur -----");
        System.out.print("Le prenom de l'auteur: ");
        prenomAuteur = sc.nextLine();
        System.out.print("Le nom de l'auteur: ");
        nomAuteur = sc.nextLine();

        Auteur auteur = new Auteur(prenomAuteur, nomAuteur);
        // add code to send the object auteur to the server side
    }

    protected static void showAuteurs(){
        // add code to receive the list of authors from the server side
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



