package model;

// Cette classe définit un lecteur avec un ID, un prenom et un nom.
public class Lecteur {

    // Les attributs
    private int id;
    private String prenomLecteur;
    private String nomLecteur;

    ///Les Constructeurs
    // Constructeur sans ID du livre
    public Lecteur(String prenomLecteur, String nomLecteur) {
        this.prenomLecteur= prenomLecteur;
        this.nomLecteur = nomLecteur;
    }

    // Constructeur avec ID du livre
    public Lecteur(String prenomLecteur, String nomLecteur, int idLecteur) {
        this.id=idLecteur;
        this.prenomLecteur= prenomLecteur;
        this.nomLecteur = nomLecteur;
    }

    /// Les getters et les setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenomLecteur() {
        return prenomLecteur;
    }

    public void setPrenomLecteur(String prenomLecteur) {
        this.prenomLecteur = prenomLecteur;
    }

    public String getNomLecteur() {
        return nomLecteur;
    }

    public void setNomLecteur(String nomLecteur) {
        this.nomLecteur = nomLecteur;
    }


    // Affichage d'un Lecteur
    @Override
    public String toString() {
        return "Lecteur { " +
                "id=" + id +
                ", prenomLecteur='" + prenomLecteur  +
                ", nomLecteur='" + nomLecteur + " " +
                '}';
    }
}
