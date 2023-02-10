package coteClient;
import java.util.Scanner;

public class Menu {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int userChoice;

        if (!Client.connectClient()) {
            System.out.println("Failed to connect to the server. Exiting.");
            return;
        } else {
            System.out.println("Connected successfully !");
        }

        do {
            System.out.println("----- Menu -----");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Afficher les livres");
            System.out.println("3. Ajouter un auteur");
            System.out.println("4. Afficher les auteurs");
            System.out.println("5. Quitter");
            System.out.print("Votre choix: ");
            userChoice = sc.nextInt();
            sc.nextLine();

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