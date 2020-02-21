package com.example.bbowl;

public class Trex extends Jeton {

    int taille = 3;
    String orientation="V";

    public Trex(Couleur couleur, int position) {
        super(couleur, position);
    }

    public Trex(Couleur couleur) {
        super(couleur);
    }
}
