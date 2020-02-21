package com.example.bbowl;

public class Diplodocus extends Jeton {

    int taille = 5;
    String orientation="H";

    public Diplodocus(Couleur couleur, int position) {
        super(couleur, position);
    }

    public Diplodocus(Couleur couleur) {
        super(couleur);
    }
}
