package Model;

public class Livre {

    private int id;

    private String titre;

    private String nomAuteur;

    private String prenomAuteur;

    public Livre(String titre, String nomAuteur, String prenomAuteur) {
        this.titre = titre;
        this.nomAuteur = nomAuteur;
        this.prenomAuteur = prenomAuteur;
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

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getPrenomAuteur() {
        return prenomAuteur;
    }

    public void setPrenomAuteur(String prenomAuteur) {
        this.prenomAuteur = prenomAuteur;
    }
}
