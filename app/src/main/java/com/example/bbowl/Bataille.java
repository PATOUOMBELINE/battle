package com.example.bbowl;

import android.util.Log;

public class Bataille {

    Flotte flotteOrdi, flotteJoueur;
    Partie bataille;
    int cpt,cptordi, diplo, carno,trex,spino, velo, nbdiplo, nbcarno,nbtrex,nbspino, nbvelo;


    public int getCpt() {
        return cpt;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }

    public int getDiplo() {
        return diplo;
    }

    public void setDiplo(int diplo) {
        this.diplo = diplo;
    }

    public int getCarno() {
        return carno;
    }

    public void setCarno(int carno) {
        this.carno = carno;
    }

    public int getTrex() {
        return trex;
    }

    public void setTrex(int trex) {
        this.trex = trex;
    }

    public int getSpino() {
        return spino;
    }

    public void setSpino(int spino) {
        this.spino = spino;
    }

    public int getVelo() {
        return velo;
    }

    public void setVelo(int velo) {
        this.velo = velo;
    }

    public Flotte getFlotteOrdi() {
        return flotteOrdi;
    }

    public Flotte getFlotteJoueur() {
        return flotteJoueur;
    }

    public void setFlotteJoueur(Flotte flotteJoueur) {
        this.flotteJoueur = flotteJoueur;
    }

    public void setFlotteOrdi(Flotte flotte) {
        this.flotteOrdi = flotte;
    }

    public Bataille() {

        this.bataille= new Partie();
        this.cpt=0;
        this.cptordi=0;
        this.flotteOrdi = new Flotte(1,1,1,1,1);
        this.flotteJoueur = new Flotte(1,1,1,1,1);
    }

    public Bataille(Partie bataille) {
        this.bataille = bataille;
    }

    public Partie getBataille() {
        return bataille;
    }

    public void setBataille(Partie bataille) {
        this.bataille = bataille;
    }

    /***
     *
     * @return
     */
    public boolean gameIsOver(){

        return bataille.isPartieFinie();
    }

    /***
     *
     * @param p
     */
    public Flotte jouer (Position p){



            Jeton jeton = new Jeton((bataille.getJoueurCourant().getNom()));
            Log.i("joueur",""+bataille.getJoueurCourant().getNom());
           // Log.i("jetons",""+bataille.getGrilleAdverse().getJeton(p).getCouleur());

            // verification apres insertion du jeton
            if(bataille.getGrilleAdverse().getJeton(p).getCouleur()==Couleur.DIPLO){

                bataille.getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.TOUCH);
                cpt++;
                diplo++;
                if(diplo==5){

                    flotteOrdi.setNbrDiplodocus(0);
                }


            }else if(bataille.getGrilleAdverse().getJeton(p).getCouleur()==Couleur.SPINO) {
                bataille.getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.TOUCH);
                cpt++;
                spino++;
                if(spino ==4){flotteOrdi.setNbrSpinosaure(0);}


            }else if(bataille.getGrilleAdverse().getJeton(p).getCouleur()==Couleur.CARNO) {
                bataille.getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.TOUCH);
                cpt++;
                carno++;
                if(carno ==3){flotteOrdi.setNbrCarnotaure(0);}

            }else if(bataille.getGrilleAdverse().getJeton(p).getCouleur()==Couleur.TREX) {
                bataille.getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.TOUCH);
                cpt++;
                trex++;
                if(trex ==3){flotteOrdi.setNbrTrex(0);}

            }
            else if(bataille.getGrilleAdverse().getJeton(p).getCouleur()==Couleur.VELO) {
                bataille.getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.TOUCH);
                cpt++;
                velo++;
                if(velo ==2){flotteOrdi.setNbrVelociraptor(0);}

            }else if(bataille.getGrilleAdverse().getJeton(p).getCouleur()==Couleur.BLANC){

                bataille.getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.PLOUF);
                 jeton.setCouleur(Couleur.PLOUF);
                 Log.i("plouf", "plouf");
            }



            // on permmute le joueur
            /*if(bataille.getJoueurs()[0].equals(bataille.getJoueurCourant())){
                bataille.setJoueurCourant(bataille.getJoueurs()[1]);
                bataille.setGrilleJoueur(bataille.getGrilles()[1]);

            }else {
                bataille.setJoueurCourant(bataille.getJoueurs()[0]);
                bataille.setGrilleJoueur(bataille.getGrilles()[0]);
            }*/
        return flotteOrdi;

    }

    public Flotte jouerOrdi (Position p){

            Jeton jeton = new Jeton((bataille.getJoueurCourant().getNom()));
            Log.i("joueur",""+bataille.getJoueurCourant().getNom());
            // Log.i("jetons",""+bataille.getGrilleAdverse().getJeton(p).getCouleur());

            // verification apres insertion du jeton
            if(bataille.getGrilleJoueur().getJeton(p).getCouleur()==Couleur.DIPLO){

                bataille.getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.TOUCH);
                cptordi++;
                nbdiplo++;
                if(nbdiplo==5){

                    flotteJoueur.setNbrDiplodocus(0);
                }


            }else if(bataille.getGrilleJoueur().getJeton(p).getCouleur()==Couleur.SPINO) {
                bataille.getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.TOUCH);
                cptordi++;
                nbspino++;
                if(nbspino ==4){flotteJoueur.setNbrSpinosaure(0);}


            }else if(bataille.getGrilleJoueur().getJeton(p).getCouleur()==Couleur.CARNO) {
                bataille.getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.TOUCH);
                cptordi++;
                nbcarno++;
                if(nbcarno ==3){flotteJoueur.setNbrCarnotaure(0);}

            }else if(bataille.getGrilleJoueur().getJeton(p).getCouleur()==Couleur.TREX) {
                bataille.getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.TOUCH);
                cptordi++;
                nbtrex++;
                if(nbtrex ==3){flotteJoueur.setNbrTrex(0);}

            }
            else if(bataille.getGrilleJoueur().getJeton(p).getCouleur()==Couleur.VELO) {
                bataille.getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.TOUCH);
                cptordi++;
                nbvelo++;
                if(nbvelo ==2){flotteJoueur.setNbrVelociraptor(0);}

            }else if(bataille.getGrilleJoueur().getJeton(p).getCouleur()==Couleur.BLANC){

                bataille.getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne()].setCouleur(Couleur.PLOUF);
                jeton.setCouleur(Couleur.PLOUF);
                Log.i("plouf", "plouf");
            }



            // on permmute le joueur
           /* if(bataille.getJoueurs()[0].equals(bataille.getJoueurCourant())){
                bataille.setJoueurCourant(bataille.getJoueurs()[1]);
                bataille.setGrilleJoueur(bataille.getGrilles()[1]);

            }else {
                bataille.setJoueurCourant(bataille.getJoueurs()[0]);
                bataille.setGrilleJoueur(bataille.getGrilles()[0]);
            }*/
        return flotteJoueur;

    }



    /***
     *
     */
    public void abandonner(){
        // on definit le gagnant qui n est pas le joureur courant
        if(bataille.getJoueurs()[0].equals(bataille.getJoueurCourant())){
            bataille.setGagnant(bataille.getJoueurs()[1]);
        }else {
            bataille.setGagnant(bataille.getJoueurs()[0]);

        }
        bataille.setParAbandon(true);
        bataille.setPartieFinie(true);

    }

}
