package Model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Livre {

    private int id;

    private String titre;

    private String auteur;


    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    public Livre(int idLivre, String titre, String auteur) {
        this.id = idLivre;
        this.titre = titre;
        this.auteur = auteur;
    }

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

    @Override
    public String toString() {
        return "Livre{ " +
                "id=" + id +
                ", titre='" + titre  +
                ", auteur='" + auteur + " " +
                '}';
    }
}
