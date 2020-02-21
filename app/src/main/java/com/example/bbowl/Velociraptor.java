package com.example.bbowl;

public class Velociraptor extends Jeton{

    int taille = 2;
    String orientation="H";

    public Velociraptor(Couleur couleur, int position) {
        super(couleur, position);
    }

    public Velociraptor(Couleur couleur) {
        super(couleur);
    }
}
