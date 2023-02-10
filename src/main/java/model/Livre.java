package model;

// Cette classe définit un livre avec un ID, un titre et un auteur.
public class Livre {

    // Les attributs
    private int id;
    private String titre;
    private String auteur;


    ///Les Constructeurs
    // Constructeur sans ID du livre
    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    // Constructeur avec ID du livre
    public Livre(int idLivre, String titre, String auteur) {
        this.id = idLivre;
        this.titre = titre;
        this.auteur = auteur;
    }

    /// Les getters et les setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    // Affichage d'un Livre
    @Override
    public String toString() {
        return "Livre{ " +
                "id=" + id +
                ", titre='" + titre  +
                ", auteur='" + auteur + " " +
                '}';
    }
}

