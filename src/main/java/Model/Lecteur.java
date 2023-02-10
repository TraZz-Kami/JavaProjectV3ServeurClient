package Model;

public class Lecteur {
    private int id;
    private String prenomLecteur;
    private String nomLecteur;

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

    public Lecteur(String prenomAuteur, String nomAuteur) {

    }
}
