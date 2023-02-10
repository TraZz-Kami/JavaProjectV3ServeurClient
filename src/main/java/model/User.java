package model;

import java.util.List;

public class User {

    private int id;

    private String pseudo;

    private String password;

    private List<Integer> listLivre;

    public User(String pseudo, String password){
        this.pseudo = pseudo;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getListLivre() {
        return listLivre;
    }

    public void setListLivre(List<Integer> listLivre) {
        this.listLivre = listLivre;
    }
}
