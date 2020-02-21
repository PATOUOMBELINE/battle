package com.example.bbowl;

public class Jeton {

    private Couleur couleur;
    private int position;


    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Jeton(int position) {
        this.position = position;
    }

    public Jeton(Couleur couleur) {
        this.couleur = couleur;


    }

    public Jeton(Couleur couleur, int position) {
        this.couleur = couleur;
        this.position = position;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        return "Jeton{" +
                "couleur=" + couleur +
                '}';
    }
}
