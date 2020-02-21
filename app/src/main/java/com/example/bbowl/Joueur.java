package com.example.bbowl;

public class Joueur {

    private Couleur nom;

    public Joueur(Couleur nom) {
        this.nom = nom;
    }

    public Couleur getNom() {
        return nom;
    }

    public Joueur() {
    }

    public void setNom(Couleur nom) {
        this.nom = nom;
    }
}
