package coteClient;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import Model.Livre;
import Model.User;
import coteClient.Client;

public class Menu {

    static void login(){
        String pseudo;
        String password;
        Client client = new Client();

        System.out.println();
        System.out.println("----- Connexion -----");
        System.out.println("Pas encore inscrit, crée vous un compte: Tapez 1");
        if(Objects.equals(entreeUtilisateur(), "1")){
            signIn();
        }
        System.out.println("Votre pseudo: ");
        pseudo = entreeUtilisateur();
        System.out.println("Votre mots de passe: ");
        password = entreeUtilisateur();

        User user = new User(pseudo, password);
        client.loginClient(user);
    }

    static void signIn(){
        String pseudo;
        String password;
        Client client = new Client();

        System.out.println();
        System.out.println("----- Inscription -----");
        System.out.println("Votre pseudo: ");
        pseudo = entreeUtilisateur();
        System.out.println("Votre mots de passe: ");
        password = entreeUtilisateur();


        User user = new User(pseudo, password);
        client.main(null);
        client.signInClient(user);
    }

    static void logOut(){
        System.out.println();
        System.out.println("Vous vous êtes bien déconnecté");
    }

    static void addLivre(){
        String titre;
        String prenomAuteur;
        String nomAuteur;
        Client client = new Client();

        System.out.println();
        System.out.println("----- Ajouter un livre -----");
        System.out.println("Le titre: ");
        titre = entreeUtilisateur();
        System.out.println("Le prenom de l'auteur: ");
        prenomAuteur = entreeUtilisateur();
        System.out.println("Le nom de l'auteur: ");
        nomAuteur = entreeUtilisateur();

        Livre livre = new Livre(titre, prenomAuteur, nomAuteur);
        client.addLivre(livre);
    }

    static void showLivre(){
        System.out.println();
        System.out.println("----- Vos livres -----");
        List<Livre> listLivre = Client.showClientLivre();
        for(Livre livre : listLivre) {
            System.out.println("|id: " + livre.getId() + " |titre: " + livre.getTitre() + " |nom de l'auteur: " + livre.getNomAuteur() + " |prenom de l'auteur : " + livre.getPrenomAuteur() + "|");
        }
    }

    static void showAuteur(){
        System.out.println();
        System.out.println("----- Vos auteurs -----");
        System.out.println("Page en construction");
    }

    static void listMenu(){
        System.out.println();
        System.out.println("Selectionner le numero de se que vous voulez faire ?");
        System.out.println("1- login");
        System.out.println("2- sign in");
        System.out.println("3- Se déconnecter");
        System.out.println("4- Ajouter un livre");
        System.out.println("5- Afficher les livres");
        System.out.println("6- Afficher les auteurs");
        System.out.println("q or Q - Se déconnecter et quitter");
    }

    static int reponseMenu(String entreUtilisateur){
        String reponse;
        do {
            System.out.println();
            reponse = entreUtilisateur;
        }while (Integer.parseInt(reponse) < 1 || Integer.parseInt(reponse) > 6 || !isInt(reponse));
        return Integer.parseInt(reponse);
    }

    static void choiseMenu(int choise){
        switch (choise) {
            case 1 -> login();
            case 2 -> signIn();
            case 3 -> logOut();
            case 4 -> addLivre();
            case 5 -> showLivre();
            case 6 -> showAuteur();
        }
    }

    public static void main(String[] args) {
        String reponse;
        do{
            listMenu();
            reponse = entreeUtilisateur();
            choiseMenu(reponseMenu(reponse));
        }while (!Objects.equals(reponse, "Q") || !Objects.equals(reponse, "q"));
    }

    public static String entreeUtilisateur(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}