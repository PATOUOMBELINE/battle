package com.example.bbowl;

public class Spinosaure extends Jeton{

    int taille = 4;
    String orientation="H";

    public Spinosaure(Couleur couleur, int position) {
        super(couleur, position);
    }

    public Spinosaure(Couleur couleur) {
        super(couleur);
    }
}
