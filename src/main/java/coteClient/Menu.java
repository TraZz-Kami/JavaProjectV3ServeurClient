package coteClient;
import coteServeur.Serveur;
import thread.threadServeur;

import java.io.IOException;
import java.util.Scanner;


public class Menu {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //Crée un thread pour avoir le serveur en fond
        threadServeur threadServeur = new threadServeur();
        threadServeur.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int userChoice;
        // Permet de vérifier si le client se connecte correctement ou non
        if (!Client.connectClient()) {
            System.out.println("Failed to connect to the server. Exiting.");
            return;
        } else {
            System.out.println("Connected successfully !");
        }

        // Affiche les différentes options
        do {
            System.out.println("----- Menu -----");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Afficher les livres");
            System.out.println("3. Ajouter un lecteur");
            System.out.println("4. Afficher les lecteurs");
            System.out.println("5. Quitter");
            System.out.print("Votre choix: ");
            userChoice = sc.nextInt();
            sc.nextLine();
            // Switch case des options
            switch (userChoice) {
                case 1 -> Client.addLivre();
                case 2 -> Client.showLivres();
                case 3 -> Client.addLecteur();
                case 4 -> Client.showLecteurs();
                case 5 -> Client.disconnectClient();
                default -> System.out.println("Choix non valide");
            }
        } while (userChoice != 5);
    }


}