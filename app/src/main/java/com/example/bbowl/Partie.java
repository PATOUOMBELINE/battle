package com.example.bbowl;

public class Partie {

    Grille [] grilles;
    Grille grille;
    Joueur[] joueurs;
    Joueur joueurCourant;
    Grille grilleJoueur, grilleAdverse;
    boolean partieFinie;
    boolean parAbandon;
    Joueur gagnant;

    public Partie() {

        grilles= new Grille[2];

        grilleJoueur= new Grille();
        grilleAdverse = new Grille();
        joueurs=new Joueur[2];
        joueurs[0]= new Joueur(Couleur.VERT);
        joueurs[1]= new Joueur(Couleur.GRIS);

       // grilles[0]= new Grille(joueurs[0]);
        //grilles[1]= new Grille(joueurs[1]);

        joueurCourant = new Joueur();
        joueurCourant = joueurs[0];//joueurs[(int)(Math.random()*2)%2];
        parAbandon = false;
        gagnant = null;

    }

    public Grille getGrilleAdverse() {
        return grilleAdverse;
    }

    public void setGrilleAdverse(Grille grilleAdverse) {
        this.grilleAdverse = grilleAdverse;
    }

    public Grille getGrilleJoueur() {
        return grilleJoueur;
    }

    public void setGrilleJoueur(Grille grilleCourante) {
        this.grilleJoueur = grilleJoueur;
    }

    public Grille[] getGrilles() {
        return this.grilles;
    }

    public void setGrilles(Grille[] grilles) {
        this.grilles = grilles;
    }

    public Grille getGrille() {
        return this.grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    public boolean isPartieFinie() {
        return partieFinie;
    }

    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }

    public boolean isParAbandon() {
        return parAbandon;
    }

    public void setParAbandon(boolean parAbandon) {
        this.parAbandon = parAbandon;
    }

    public Joueur getGagnant() {
        return gagnant;
    }

    public void setGagnant(Joueur gagnant) {
        this.gagnant = gagnant;
    }
}
