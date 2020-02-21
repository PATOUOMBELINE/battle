package com.example.bbowl;

public class Flotte {

    private int nbrDiplodocus ;
    private int nbrSpinosaure;
    private int nbrCarnotaure;
    private int nbrTrex;
    private int nbrVelociraptor;

    public Flotte(int nbrDiplodocus, int nbrSpinosaure, int nbrCarnotaure, int nbrTrex, int nbrVelociraptor) {
        this.nbrDiplodocus = nbrDiplodocus;
        this.nbrSpinosaure = nbrSpinosaure;
        this.nbrCarnotaure = nbrCarnotaure;
        this.nbrTrex = nbrTrex;
        this.nbrVelociraptor = nbrVelociraptor;

    }

    public int getNbrDiplodocus() {
        return nbrDiplodocus;
    }

    public void setNbrDiplodocus(int nbrDiplodocus) {
        this.nbrDiplodocus = nbrDiplodocus;
    }

    public int getNbrSpinosaure() {
        return nbrSpinosaure;
    }

    public void setNbrSpinosaure(int nbrSpinosaure) {
        this.nbrSpinosaure = nbrSpinosaure;
    }

    public int getNbrCarnotaure() {
        return nbrCarnotaure;
    }

    public void setNbrCarnotaure(int nbrCarnotaure) {
        this.nbrCarnotaure = nbrCarnotaure;
    }

    public int getNbrTrex() {
        return nbrTrex;
    }

    public void setNbrTrex(int nbrTrex) {
        this.nbrTrex = nbrTrex;
    }

    public int getNbrVelociraptor() {
        return nbrVelociraptor;
    }

    public void setNbrVelociraptor(int nbrVelociraptor) {
        this.nbrVelociraptor = nbrVelociraptor;
    }

    @Override
    public String toString() {
        return "Nombre de dinosaures:{" +
                "Diplodocus=" + nbrDiplodocus +
                ", Spinosaure=" + nbrSpinosaure +
                ", Carnotaure=" + nbrCarnotaure +
                ", Trex=" + nbrTrex +
                ", Velociraptor=" + nbrVelociraptor +
                '}';
    }
}
