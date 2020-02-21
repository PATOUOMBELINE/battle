package com.example.bbowl;

public class Grille {

    private Joueur joueur;
    private static final int NB_LIGNES = Config.NB_LIGNES;
    private static final int NB_COLONNES = Config.NB_COLONNES;
    private Jeton[][] plateauJetons;

    public Grille() {
        plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];

    }

    public Grille(Joueur joueur, Jeton[][] plateauJetons) {
        this.joueur = joueur;
        this.plateauJetons = plateauJetons;
    }

    public Grille(Jeton[][] plateauJetons) {
        this.plateauJetons = plateauJetons;
    }

    public Grille(Joueur joueur) {
        this.joueur = joueur;
    }

    public Jeton getJeton(Position position) {
        return plateauJetons[position.getLigne()][position.getColonne()];
    }




    public Jeton[][] getPlateauJetons() {
        return plateauJetons;
    }

    public void setPlateauJetons(Jeton[][] plateauJetons) {
        this.plateauJetons = plateauJetons;
    }

    @Override
    public String toString() {
        String chaine = "\n";

        for (int i = 0; i < plateauJetons.length; i++) {

            chaine = chaine + "-----------------------------";
            chaine = chaine + "\n";

            for (int j = 0; j < plateauJetons[0].length; j++) {
                if (plateauJetons[i][j] == null) {
                    chaine = chaine + "|   ";
                } else {
                    if (plateauJetons[i][j].getCouleur() == Couleur.GRIS) {
                        chaine = chaine + "| J ";
                    }else {
                        chaine = chaine + "| R ";
                    }
                }
            }
            chaine = chaine + "|";
            chaine = chaine + "\n";

        }

        return chaine;
    }

}
