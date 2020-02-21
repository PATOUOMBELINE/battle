package com.example.bbowl;



import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bataille.R;


public class MainActivity extends AppCompatActivity {

    TextView lbAfficher, textInfo, txtGagnant;// txtDiplo, txtCarno,txtSpino,txtTrex,txtVelo;
    Bataille bataille;
    GridView gridView, gridVAdverse, gridVAdDino,gridViewDino;
    CharSequence orientation;
    int plateau[], plateauAdverse[],

// info ordi
    ligne[] = {
            R.drawable.jeton_diplodo,
            R.drawable.jeton_spino,
            R.drawable.jeton_carno,
            R.drawable.jeton_trex,
            R.drawable.jeton_velo,
            },
/// info joueur
    colonne[] = {
            R.drawable.jeton_diplodo,
            R.drawable.jeton_spino,
            R.drawable.jeton_carno,
            R.drawable.jeton_trex,
            R.drawable.jeton_velo,
            };

    int cpt, touche,touchOrdi,tour;
    View view;
    Button btnAbandon, btnRejouer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lbAfficher = findViewById(R.id.lbAfficher);
        gridView = findViewById(R.id.GView);
        gridViewDino = findViewById(R.id.GViewDino);
        gridVAdverse=findViewById(R.id.GViewAdv);
        gridVAdDino=findViewById(R.id.GViewAdvDino);
        textInfo = findViewById(R.id.txt);
        txtGagnant = findViewById(R.id.txtGagnant);

        btnAbandon = findViewById(R.id.btnAbandon);
        btnRejouer = findViewById(R.id.btnNvPlay);


        bataille = new Bataille();
        cpt=0;
        tour=0;

        afficherAdverser();
        grilleAdverse();

        afficherPlateau();
        //afficher les dino de l'ordi
        gridVAdDino.setAdapter(new AdapterGridAdDino(MainActivity.this, ligne));


       textInfo.setText("Placement du Diplodocus 5 cases");
       openMsgOrientation(view);

       /// placemment des dinosaures du joueur
       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cpt++;

                if(cpt==1) {

                    //Log.i("position cpt 1", "" + verifPosition(determinePosition(position), 5, orientation)+"O"+orientation);

                    if(verifPositionJoueur(determinePosition(position), 5, orientation)==false){
                        Toast.makeText(MainActivity.this, "position en dehors du jeu", Toast.LENGTH_SHORT).show();
                        cpt--;
                    }else {

                        initDiplo(determinePosition(position), orientation);
                        //Log.i("compteur","" + cpt +" PosLigne :"+ determinePosition(position).getLigne());
                        afficherPlateau();
                        textInfo.setText("Placement du Spinosaure 4 cases");
                        openMsgOrientation(view);
                    }

                }
                if(cpt==2){

                    if (verifPositionJoueur(determinePosition(position), 4, orientation)!=true){
                        Toast.makeText(MainActivity.this, "position en dehors du jeu", Toast.LENGTH_SHORT).show();
                        cpt--;
                    }else {
                        initSpino(determinePosition(position), orientation);
                        afficherPlateau();
                        textInfo.setText("Placement du Tyranosaure 3 cases");
                        openMsgOrientation(view);
                    }

                }//end
                if(cpt==3) {
                    Log.i("position click cpt 3", "" + position+"cpt"+cpt);
                    if (verifPositionJoueur(determinePosition(position), 3, orientation)!=true){
                        Toast.makeText(MainActivity.this, "position en dehors du jeu", Toast.LENGTH_SHORT).show();
                        cpt--;
                    }else {
                        initTrex(determinePosition(position), orientation);
                        afficherPlateau();
                        textInfo.setText("Placement du Carnotaure 3 cases");
                        openMsgOrientation(view);
                    }

                }
                if(cpt==4) {


                    Log.i("position click cpt 4", "" + position+"cpt"+cpt);
                    if (verifPositionJoueur(determinePosition(position), 3, orientation)!=true){
                        Toast.makeText(MainActivity.this, "position en dehors du jeu", Toast.LENGTH_SHORT).show();
                        cpt--;
                    }else {
                        initCarno(determinePosition(position), orientation);
                        afficherPlateau();
                        textInfo.setText("Placement du Velociraptor 2 cases");
                        openMsgOrientation(view);
                    }
                }
                if(cpt==5){

                    Log.i("position click 5", "" + position+"cpt"+cpt);

                    if (verifPositionJoueur(determinePosition(position), 2, orientation)!=true){
                        Toast.makeText(MainActivity.this, "position en dehors du jeu", Toast.LENGTH_SHORT).show();
                        cpt--;
                    }else {
                        initVelo(determinePosition(position), orientation);
                        textInfo.setText("");
                        afficherPlateau();
                        infoPartie();
                    }
                }

            }//end on item
        });//end set oncliklistener

       gridViewDino.setAdapter(new AdapterGridDino(MainActivity.this, colonne));


        gridVAdverse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               cpt++;
                // verifier qu'on n'a pas deja jouer à cette position

                if(( cpt==6) && (bataille.gameIsOver()!=true)){

                    if(bataille.getBataille().getGrilleAdverse().getJeton(determinePosition(position)).getCouleur()==Couleur.TOUCH||
                            bataille.getBataille().getGrilleAdverse().getJeton(determinePosition(position)).getCouleur()==Couleur.PLOUF){
                        Toast.makeText(MainActivity.this,"Position déjà jouée",Toast.LENGTH_SHORT).show();
                        cpt--;
                    }

                    bataille.jouer(determinePosition(position));
                    afficherAdverser();
                    Position jet=determinePosition((int)Math.round(Math.random() * 100));

                    // vérifier que le jet ordi n'a pas déjà été joué, et rejouer si c'est le cas

                    while(bataille.getBataille().getGrilleJoueur().getJeton(jet).getCouleur()==Couleur.TOUCH ||
                             bataille.getBataille().getGrilleJoueur().getJeton(jet).getCouleur()==Couleur.PLOUF){
                        jet=determinePosition((int)Math.round(Math.random() * 100));
                    }
                    bataille.jouerOrdi(jet);
                    afficherPlateau();
                    infoPartie();
                    cpt--;
                    tour++;
                }

            }
        });

////////////abandon = perdu
        btnAbandon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               bataille.abandonner();
               txtGagnant.setText("Vous aves perdu");
               bataille.gameIsOver();

                }
        });
/////////////Remmetre la partie au début //////
        btnRejouer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ligne[0]= R.drawable.jeton_diplodo;
                    ligne[1]= R.drawable.jeton_spino;
                    ligne[2]= R.drawable.jeton_carno;
                    ligne[3]= R.drawable.jeton_trex;
                    ligne[4]= R.drawable.jeton_velo;
                    gridVAdDino.setAdapter(new AdapterGridAdDino(MainActivity.this, ligne));
//////////////////////////// info joueur
                    colonne[0]= R.drawable.jeton_diplodo;
                    colonne[1]= R.drawable.jeton_spino;
                    colonne[2]= R.drawable.jeton_carno;
                    colonne[3]= R.drawable.jeton_trex;
                    colonne[4]= R.drawable.jeton_velo;
                    gridViewDino.setAdapter(new AdapterGridDino(MainActivity.this, colonne));

                    txtGagnant.setText("");
                    bataille = new Bataille();
                    cpt=0;
                    tour=0;
                    afficherAdverser();
                    grilleAdverse();
                    afficherPlateau();

                    textInfo.setText("Placement du Diplodocus 5 cases");
                    openMsgOrientation(view);

                }
        });

   }



// affichage d'un message pour demander l'orientation
    public CharSequence openMsgOrientation(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Choix de l'orientation du dinosaure");
        alertDialogBuilder.setPositiveButton("Horizontal",

                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(MainActivity.this,"You clicked horizontal",Toast.LENGTH_LONG).show();
                        orientation="Horizontal";
                    }
                });

        alertDialogBuilder.setNegativeButton("Vertical",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"You clicked vertical",Toast.LENGTH_LONG).show();
                orientation= "Vertical";
                //finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
        return orientation;
    }
///





    // affichage du plateau du joueur
    public void afficherPlateau() {
        touchOrdi=0;
        plateau = new int[100];
        int k = 0;
        for (int i = 0; i < Config.NB_LIGNES; i++) {
            for (int j = 0; j < Config.NB_COLONNES; j++) {
                Jeton jetonj = new Jeton(k);

                if (bataille.getBataille().getGrilleJoueur().getPlateauJetons()[i][j] == null) {

                    bataille.getBataille().getGrilleJoueur().getPlateauJetons()[i][j] = jetonj;
                    jetonj.setPosition(k);
                    jetonj.setCouleur(Couleur.BLANC);
                    plateau[k] = R.drawable.jeton_blanc;
                    k++;
                }else if (bataille.getBataille().getGrilleJoueur().getPlateauJetons()[i][j].getCouleur()==Couleur.BLANC) {

                    plateau[k] = R.drawable.jeton_blanc;
                    k++;
                }else if (bataille.getBataille().getGrilleJoueur().getPlateauJetons()[i][j].getCouleur()== Couleur.DIPLO ) {
                    plateau[k] = R.drawable.jeton_diplodo;  // prevoir jeton diplo
                    k++;

                }else if(bataille.getBataille().getGrilleJoueur().getPlateauJetons()[i][j].getCouleur()==Couleur.CARNO) {
                    plateau[k] = R.drawable.jeton_carno;// prevoir jeton diplo
                    k++;
                }else if(bataille.getBataille().getGrilleJoueur().getPlateauJetons()[i][j].getCouleur()==Couleur.SPINO) {
                    plateau[k] = R.drawable.jeton_spino;// prevoir jeton diplo
                    k++;
                }else if(bataille.getBataille().getGrilleJoueur().getPlateauJetons()[i][j].getCouleur()==Couleur.TREX) {
                plateau[k] = R.drawable.jeton_trex;// prevoir jeton diplo
                k++;
                }else if(bataille.getBataille().getGrilleJoueur().getPlateauJetons()[i][j].getCouleur()==Couleur.VELO) {
                plateau[k] = R.drawable.jeton_velo;// prevoir jeton diplo
                k++;

                }else if(bataille.getBataille().getGrilleJoueur().getPlateauJetons()[i][j].getCouleur()==Couleur.TOUCH) {
                    plateau[k] = R.drawable.jeton_touch;
                    touchOrdi++;

                    k++;

                }else if(bataille.getBataille().getGrilleJoueur().getPlateauJetons()[i][j].getCouleur()==Couleur.PLOUF) {
                    plateau[k] = R.drawable.jeton_plouf;
                    k++;



                }
        }}
        gridView.setAdapter(new AdapterGrid(MainActivity.this, plateau));


    }
// permet de visualider la grille adverse
    public void afficherAdverser() {
        plateauAdverse = new int[100];
        touche = 0;
        int k = 0;
        for (int i = 0; i < Config.NB_LIGNES; i++) {
            for (int j = 0; j < Config.NB_COLONNES; j++) {
                Jeton jeton = new Jeton(k);

                if (bataille.getBataille().getGrilleAdverse().getPlateauJetons()[i][j] == null) {

                    bataille.getBataille().getGrilleAdverse().getPlateauJetons()[i][j]=jeton;
                    jeton.setPosition(k);
                    jeton.setCouleur(Couleur.BLANC);
                    plateauAdverse[k] = R.drawable.jeton_blanc;
                    k++;

                }else if (bataille.getBataille().getGrilleAdverse().getPlateauJetons()[i][j].getCouleur()==Couleur.BLANC) {

                    plateauAdverse[k] = R.drawable.jeton_gris;
                    k++;

                }else if(bataille.getBataille().getGrilleAdverse().getPlateauJetons()[i][j].getCouleur()==Couleur.DIPLO||bataille.getBataille().getGrilleAdverse().getPlateauJetons()[i][j].getCouleur()==Couleur.SPINO || bataille.getBataille().getGrilleAdverse().getPlateauJetons()[i][j].getCouleur()==Couleur.CARNO || bataille.getBataille().getGrilleAdverse().getPlateauJetons()[i][j].getCouleur()==Couleur.TREX || bataille.getBataille().getGrilleAdverse().getPlateauJetons()[i][j].getCouleur()==Couleur.VELO) {
                    plateauAdverse[k] = R.drawable.jeton_gris;///mettre en blanc
                    k++;


                 }
                else if(bataille.getBataille().getGrilleAdverse().getPlateauJetons()[i][j].getCouleur()==Couleur.TOUCH) {
                    plateauAdverse[k] = R.drawable.jeton_touch;
                    touche++;

                    k++;

                }else if(bataille.getBataille().getGrilleAdverse().getPlateauJetons()[i][j].getCouleur()==Couleur.PLOUF) {
                        plateauAdverse[k] = R.drawable.jeton_plouf;
                        k++;

                }

            }}

       // textInfo.setText("Nombre de dinosaure touché: "+ touche);
        //
        gridVAdverse.setAdapter(new AdapterGridAdverse(MainActivity.this, plateauAdverse));
    }
    public void infoPartie() {
        //Toast.makeText(MainActivity.this, "C'est à vous de jouer: " , Toast.LENGTH_SHORT).show();
        //textInfo.setText("C'est à vous de jouer, cliquer dans la grille adverse");
        //Log.i("nbr diplo",""+ bataille.getFlotteOrdi().getNbrDiplodocus());
       // Log.i("nbr spino",""+ bataille.getFlotteOrdi().getNbrSpinosaure());
        //Log.i("nbr carno",""+ bataille.getFlotteOrdi().getNbrCarnotaure());
        //Log.i("nbr trex",""+ bataille.getFlotteOrdi().getNbrTrex());
        //Log.i("nbr velo",""+ bataille.getFlotteOrdi().getNbrVelociraptor());

        if(bataille.getFlotteJoueur().getNbrDiplodocus()==0){
            colonne[0]=R.drawable.jeton_diplodo_mort;
            gridViewDino.setAdapter(new AdapterGridDino(MainActivity.this, colonne));
            //txtDiplo.setText("Votre diplodocus est mort");
        }if(bataille.getFlotteJoueur().getNbrSpinosaure()==0){
            colonne[1]=R.drawable.jeton_spino_mort;
            gridViewDino.setAdapter(new AdapterGridDino(MainActivity.this, colonne));
        }if(bataille.getFlotteJoueur().getNbrCarnotaure()==0){
            colonne[2]=R.drawable.jeton_carno_mort;
            gridViewDino.setAdapter(new AdapterGridDino(MainActivity.this, colonne));

        }if(bataille.getFlotteJoueur().getNbrTrex()==0){
            colonne[3]=R.drawable.jeton_trex_mort;
            gridViewDino.setAdapter(new AdapterGridDino(MainActivity.this, colonne));

        }if(bataille.getFlotteJoueur().getNbrVelociraptor()==0){
            colonne[4]=R.drawable.jeton_velo_mort;
            gridViewDino.setAdapter(new AdapterGridDino(MainActivity.this, colonne));
        }
        // textInfo.setText("Votre adversaire a :"+ bataille.getFlotteOrdi()); // info sur le nombre de dino de l'ordi

        if (bataille.getDiplo()==5 && bataille.getSpino() ==4 && bataille.getCarno()==3 && bataille.getTrex() ==3 && bataille.getVelo() ==2) {
            txtGagnant.setText("Vous avez gagné en : "+tour+" coups! félicitation");
            bataille.gameIsOver();
        }

        if (bataille.getFlotteOrdi().getNbrDiplodocus()==0 ){
            Log.i("Diplo mort",""+ bataille.getFlotteOrdi().getNbrDiplodocus());
            ligne[0]=R.drawable.jeton_diplodo_mort;
            gridVAdDino.setAdapter(new AdapterGridAdDino(MainActivity.this, ligne));
        }
        if ( bataille.getFlotteOrdi().getNbrSpinosaure()==0 ){
            ligne[1]=R.drawable.jeton_spino_mort;
            gridVAdDino.setAdapter(new AdapterGridAdDino(MainActivity.this, ligne));

        }
        if ( bataille.getFlotteOrdi().getNbrCarnotaure()==0 ){
            ligne[2]=R.drawable.jeton_carno_mort;
            gridVAdDino.setAdapter(new AdapterGridAdDino(MainActivity.this, ligne));
        }
        if ( bataille.getFlotteOrdi().getNbrTrex()==0){
            ligne[3]=R.drawable.jeton_trex_mort;
            gridVAdDino.setAdapter(new AdapterGridAdDino(MainActivity.this, ligne));

        }
        if ( bataille.getFlotteOrdi().getNbrVelociraptor()==0){
            ligne[4]=R.drawable.jeton_velo_mort;
            gridVAdDino.setAdapter(new AdapterGridAdDino(MainActivity.this, ligne));

        }

        if(bataille.getFlotteJoueur().getNbrDiplodocus()==0&&bataille.getFlotteJoueur().getNbrSpinosaure()==0&&bataille.getFlotteJoueur().getNbrCarnotaure()==0&& bataille.getFlotteJoueur().getNbrTrex()==0 && bataille.getFlotteJoueur().getNbrVelociraptor()==0){
            txtGagnant.setText("Vous aves perdu");
            bataille.gameIsOver();
        }

    }

    ////au clic définition de la position et placement du dinosaure
    public void initDiplo(Position p,CharSequence orientation) {


        if(orientation.equals("Vertical")) {

            for (int i = 0; i < 5; i++) {
                Diplodocus diplo = new Diplodocus(Couleur.DIPLO);
                bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne()+ i][p.getColonne()] = diplo;
            }
        }else if(orientation.equals("Horizontal")) {

            for (int i = 0; i < 5; i++) {
                Diplodocus diplo = new Diplodocus(Couleur.DIPLO);
                bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne() + i] = diplo;
            }
        }

    }

    public void initSpino(Position p,CharSequence orientation) {

        if(orientation.equals("Vertical")) {
            for (int i = 0; i < 4; i++) {
                Spinosaure spino = new Spinosaure(Couleur.SPINO);
                bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne() + i][p.getColonne()] = spino;
            }
        }else if(orientation.equals("Horizontal")) {

            for (int i = 0; i < 4; i++) {
                Spinosaure spino = new Spinosaure(Couleur.SPINO);
                bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne()+i] = spino;
            }

        }

    }

    public void initTrex(Position p,CharSequence orientation) {

        if (orientation.equals("Vertical")) {

            for (int i = 0; i < 3; i++) {
                Trex trex = new Trex(Couleur.TREX);
                bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne() + i][p.getColonne()] = trex;
            }
        } else if (orientation.equals("Horizontal")){
            for (int i = 0; i < 3; i++) {
                Trex trex = new Trex(Couleur.TREX);
                bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne() + i] = trex;
            }
        }
    }

    public void initCarno(Position p, CharSequence orientation) {
        if (orientation.equals("Vertical")) {

            for (int i = 0; i < 3; i++) {
                Trex carno = new Trex(Couleur.CARNO);
                bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne() + i][p.getColonne()] = carno;
            }
        } else if (orientation.equals("Horizontal")){

            for (int i = 0; i < 3; i++) {
                Trex carno = new Trex(Couleur.CARNO);
                bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne() + i] = carno;
            }
        }

    }

    public void initVelo(Position p, CharSequence orientation) {


        if (orientation.equals("Vertical")) {
            for (int i = 0; i < 2; i++) {
                Velociraptor velo = new Velociraptor(Couleur.VELO);
                bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne() + i][p.getColonne()] = velo;
            }
        } else if (orientation.equals("Horizontal")){
            for (int i = 0; i < 2; i++) {
                Velociraptor velo = new Velociraptor(Couleur.VELO);
                bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne()+ i] = velo;
            }
        }
    }

    // placement des dino adverses
    public void initDiploAdverse(Position p,CharSequence orientation) {


        if(orientation.equals("Vertical")) {
            for (int i = 0; i < 5; i++) {
                Diplodocus diplo = new Diplodocus(Couleur.DIPLO);
                bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne() + i][p.getColonne()] = diplo;

            }

        }else if(orientation.equals("Horizontal")) {
            for (int i = 0; i < 5; i++) {
                Diplodocus diplo = new Diplodocus(Couleur.DIPLO);
                bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne()+ i] = diplo;

            }
        }
    }

    public void initSpinoAd(Position p,CharSequence orientation) {

        if(orientation.equals("Vertical")) {

            for (int i = 0; i < 4; i++) {
                Spinosaure spino = new Spinosaure(Couleur.SPINO);
                bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne()+i][p.getColonne()] = spino;
            }
        }else if(orientation.equals("Horizontal")) {

            for (int i = 0; i < 4; i++) {
                Spinosaure spino = new Spinosaure(Couleur.SPINO);
                bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne()+i] = spino;
            }

        }
    }

    public void initTrexAd(Position p,CharSequence orientation) {

        if (orientation.equals("Vertical")) {

            for (int i = 0; i < 3; i++) {
                Trex trex = new Trex(Couleur.TREX);
                bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne() + i][p.getColonne()] = trex;
            }
        } else if (orientation.equals("Horizontal")){
            for (int i = 0; i < 3; i++) {
                Trex trex = new Trex(Couleur.TREX);
                bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne() + i] = trex;
            }
        }

    }

    public void initCarnoAd(Position p, CharSequence orientation) {

        if (orientation.equals("Vertical")) {

            for (int i = 0; i < 3; i++) {
                Trex carno = new Trex(Couleur.CARNO);
                bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne() + i][p.getColonne()] = carno;
            }
        } else if (orientation.equals("Horizontal")){
            for (int i = 0; i < 3; i++) {
                Trex carno = new Trex(Couleur.CARNO);
                bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne() + i] = carno;
            }
        }

    }

    public void initVeloAd(Position p, CharSequence orientation) {

        if (orientation.equals("Vertical")) {
            for (int i = 0; i < 2; i++) {
                Velociraptor velo = new Velociraptor(Couleur.VELO);
                bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne() + i][p.getColonne()] = velo;
            }

        }else{
            for (int i = 0; i < 2; i++) {
                Velociraptor velo = new Velociraptor(Couleur.VELO);
                bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne()+ i] = velo;
            }
        }

    }

    // permet de définir le numero de la ligne et de la colonne
    public Position determinePosition(int position) {
        int ligne ;
        int colonne;
        Position p = new Position();

        if (position >= 0 && position < 10) {
            ligne = 0;
            p.setLigne(ligne);
            if (position == 0) {
                colonne = 0;
                p.setColonne(colonne);
                return p;
            } else if (position == 1) {
                colonne = 1;
                p.setColonne(colonne);
                return p;
            } else if (position == 2) {
                colonne = 2;
                p.setColonne(colonne);
                return p;
            } else if (position == 3) {
                colonne = 3;
                p.setColonne(colonne);
                return p;
            } else if (position == 4) {
                colonne = 4;
                p.setColonne(colonne);
                return p;
            } else if (position == 5) {
                colonne = 5;
                p.setColonne(colonne);
                return p;
            } else if (position == 6) {
                colonne = 6;
                p.setColonne(colonne);
                return p;
            } else if (position == 7) {
                colonne = 7;
                p.setColonne(colonne);
                return p;
            } else if (position == 8) {
                colonne = 8;
                p.setColonne(colonne);
                return p;
            } else if (position == 9) {
                colonne = 9;
                p.setColonne(colonne);
                return p;
            }//endif

        } else if (position >= 10 && position < 20) {
            ligne = 1;
            p.setLigne(ligne);
            if (position == 10) {
                colonne = 0;
                p.setColonne(colonne);
                return p;
            } else if (position == 11) {
                colonne = 1;
                p.setColonne(colonne);
                return p;
            } else if (position == 12) {
                colonne = 2;
                p.setColonne(colonne);
                return p;
            } else if (position == 13) {
                colonne = 3;
                p.setColonne(colonne);
                return p;
            } else if (position == 14) {
                colonne = 4;
                p.setColonne(colonne);
                return p;
            } else if (position == 15) {
                colonne = 5;
                p.setColonne(colonne);
                return p;
            } else if (position == 16) {
                colonne = 6;
                p.setColonne(colonne);
                return p;
            } else if (position == 17) {
                colonne = 7;
                p.setColonne(colonne);
                return p;
            } else if (position == 18) {
                colonne = 8;
                p.setColonne(colonne);
                return p;
            } else if (position == 19) {
                colonne = 9;
                p.setColonne(colonne);
                return p;
            }//endif

        } else if (position >= 20 && position < 30) {
            ligne = 2;
            p.setLigne(ligne);
            if (position == 20) {
                colonne = 0;
                p.setColonne(colonne);
                return p;
            } else if (position == 21) {
                colonne = 1;
                p.setColonne(colonne);
                return p;
            } else if (position == 22) {
                colonne = 2;
                p.setColonne(colonne);
                return p;
            } else if (position == 23) {
                colonne = 3;
                p.setColonne(colonne);
                return p;
            } else if (position == 24) {
                colonne = 4;
                p.setColonne(colonne);
                return p;
            } else if (position == 25) {
                colonne = 5;
                p.setColonne(colonne);
                return p;
            } else if (position == 26) {
                colonne = 6;
                p.setColonne(colonne);
                return p;

            } else if (position == 27) {
                colonne = 7;
                p.setColonne(colonne);
                return p;
            } else if (position == 28) {
                colonne = 8;
                p.setColonne(colonne);
                return p;
            } else if (position == 29) {
                colonne = 9;
                p.setColonne(colonne);
                return p;
            }//endif
        } else if (position >= 30 && position < 40) {
            ligne = 3;
            p.setLigne(ligne);
            if (position == 30) {
                colonne = 0;
                p.setColonne(colonne);
                return p;
            } else if (position == 31) {
                colonne = 1;
                p.setColonne(colonne);
                return p;
            } else if (position == 32) {
                colonne = 2;
                p.setColonne(colonne);
                return p;
            } else if (position == 33) {
                colonne = 3;
                p.setColonne(colonne);
                return p;
            } else if (position == 34) {
                colonne = 4;
                p.setColonne(colonne);
                return p;
            } else if (position == 35) {
                colonne = 5;
                p.setColonne(colonne);
                return p;
            } else if (position == 36) {
                colonne = 6;
                p.setColonne(colonne);
                return p;
            } else if (position == 37) {
                colonne = 7;
                p.setColonne(colonne);
                return p;
            } else if (position == 38) {
                colonne = 8;
                p.setColonne(colonne);
                return p;
            } else if (position == 39) {
                colonne = 9;
                p.setColonne(colonne);
                return p;
            }//endif

        } else if (position >= 40 && position < 50) {
            ligne = 4;
            p.setLigne(ligne);
            if (position == 40) {
                colonne = 0;
                p.setColonne(colonne);
                return p;
            } else if (position == 41) {
                colonne = 1;
                p.setColonne(colonne);
                return p;
            } else if (position == 42) {
                colonne = 2;
                p.setColonne(colonne);
                return p;
            } else if (position == 43) {
                colonne = 3;
                p.setColonne(colonne);
                return p;
            } else if (position == 44) {
                colonne = 4;
                p.setColonne(colonne);
                return p;
            } else if (position == 45) {
                colonne = 5;
                p.setColonne(colonne);
                return p;
            } else if (position == 46) {
                colonne = 6;
                p.setColonne(colonne);
                return p;
            } else if (position == 47) {
                colonne = 7;
                p.setColonne(colonne);
                return p;
            } else if (position == 48) {
                colonne = 8;
                p.setColonne(colonne);
                return p;
            } else if (position == 49) {
                colonne = 9;
                p.setColonne(colonne);
                return p;
            }//endif
        } else if (position >= 50 && position < 60) {
            ligne = 5;
            p.setLigne(ligne);
            if (position == 50) {
                colonne = 0;
                p.setColonne(colonne);
                return p;
            } else if (position == 51) {
                colonne = 1;
                p.setColonne(colonne);
                return p;
            } else if (position == 52) {
                colonne = 2;
                p.setColonne(colonne);
                return p;
            } else if (position == 53) {
                colonne = 3;
                p.setColonne(colonne);
                return p;
            } else if (position == 54) {
                colonne = 4;
                p.setColonne(colonne);
                return p;
            } else if (position == 55) {
                colonne = 5;
                p.setColonne(colonne);
                return p;
            } else if (position == 56) {
                colonne = 6;
                p.setColonne(colonne);
                return p;
            } else if (position == 57) {
                colonne = 7;
                p.setColonne(colonne);
                return p;
            } else if (position == 58) {
                colonne = 8;
                p.setColonne(colonne);
                return p;
            } else if (position == 59) {
                colonne = 9;
                p.setColonne(colonne);
                return p;
            }//endif
        } else if (position >= 60 && position < 70) {
            ligne = 6;
            p.setLigne(ligne);
            if (position == 60) {
                colonne = 0;
                p.setColonne(colonne);
                return p;
            } else if (position == 61) {
                colonne = 1;
                p.setColonne(colonne);
                return p;
            } else if (position == 62) {
                colonne = 2;
                p.setColonne(colonne);
                return p;
            } else if (position == 63) {
                colonne = 3;
                p.setColonne(colonne);
                return p;
            } else if (position == 64) {
                colonne = 4;
                p.setColonne(colonne);
                return p;
            } else if (position == 65) {
                colonne = 5;
                p.setColonne(colonne);
                return p;
            } else if (position == 66) {
                colonne = 6;
                p.setColonne(colonne);
                return p;
            } else if (position == 67) {
                colonne = 7;
                p.setColonne(colonne);
                return p;
            } else if (position == 68) {
                colonne = 8;
                p.setColonne(colonne);
                return p;
            } else if (position == 69) {
                colonne = 9;
                p.setColonne(colonne);
                return p;
            }//endif

        } else if (position >= 70 && position < 80) {
            ligne = 7;
            p.setLigne(ligne);
            if (position == 70) {
                colonne = 0;
                p.setColonne(colonne);
                return p;
            } else if (position == 71) {
                colonne = 1;
                p.setColonne(colonne);
                return p;
            } else if (position == 72) {
                colonne = 2;
                p.setColonne(colonne);
                return p;
            } else if (position == 73) {
                colonne = 3;
                p.setColonne(colonne);
                return p;
            } else if (position == 74) {
                colonne = 4;
                p.setColonne(colonne);
                return p;
            } else if (position == 75) {
                colonne = 5;
                p.setColonne(colonne);
                return p;
            } else if (position == 76) {
                colonne = 6;
                p.setColonne(colonne);
                return p;
            } else if (position == 77) {
                colonne = 7;
                p.setColonne(colonne);
                return p;
            } else if (position == 78) {
                colonne = 8;
                p.setColonne(colonne);
                return p;
            } else {
                colonne = 9;
                p.setColonne(colonne);
                return p;
            }//endif
        } else if (position >= 80 && position < 90) {
            ligne = 8;
            p.setLigne(ligne);
            if (position == 80) {
                colonne = 0;
                p.setColonne(colonne);
                return p;
            } else if (position == 81) {
                colonne = 1;
                p.setColonne(colonne);
                return p;
            } else if (position == 82) {
                colonne = 2;
                p.setColonne(colonne);
                return p;
            } else if (position == 83) {
                colonne = 3;
                p.setColonne(colonne);
                return p;
            } else if (position == 84) {
                colonne = 4;
                p.setColonne(colonne);
                return p;
            } else if (position == 85) {
                colonne = 5;
                p.setColonne(colonne);
                return p;
            } else if (position == 86) {
                colonne = 6;
                p.setColonne(colonne);
                return p;
            } else if (position == 87) {
                colonne = 7;
                p.setColonne(colonne);
                return p;
            } else if (position == 88) {
                colonne = 8;
                p.setColonne(colonne);
                return p;
            } else if (position == 89) {
                colonne = 9;
                p.setColonne(colonne);
                return p;
            }//endif
        } else if (position >= 90 && position < 100) {
            ligne = 9;
            p.setLigne(ligne);
            if (position == 90) {
                colonne = 0;
                p.setColonne(colonne);
                return p;
            } else if (position == 91) {
                colonne = 1;
                p.setColonne(colonne);
                return p;
            } else if (position == 92) {
                colonne = 2;
                p.setColonne(colonne);
                return p;
            } else if (position == 93) {
                colonne = 3;
                p.setColonne(colonne);
                return p;
            } else if (position == 94) {
                colonne = 4;
                p.setColonne(colonne);
                return p;
            } else if (position == 95) {
                colonne = 5;
                p.setColonne(colonne);
                return p;

            } else if (position == 96) {
                colonne = 6;
                p.setColonne(colonne);
                return p;
            } else if (position == 97) {
                colonne = 7;
                p.setColonne(colonne);
                return p;
            } else if (position == 98) {
                colonne = 8;
                p.setColonne(colonne);
                return p;
            } else if (position == 99) {
                colonne = 9;
                p.setColonne(colonne);
                return p;
            }//endif
        }
        return p;
    }

    // placement de dino adverse de maniere aléatoire
    public void grilleAdverse() {

        double ordi = Math.random();
        Position position = new Position((int) Math.round(Math.random() * 9.5), (int) Math.round(Math.random() * 9.5));
        if (ordi > 0.5) {
            orientation = "Vertical";
        } else {
            orientation = "Horizontal";
        }
        while (verifPosition(position, 5, orientation) != true) {
            position = new Position((int) Math.round(Math.random() * 9.5), (int) Math.round(Math.random() * 9.5));
        }
        Log.i("position", "diplo"+position);
        initDiploAdverse(position, orientation);

        //////////spino

        ordi = Math.random();
        position = new Position((int) Math.round(Math.random() * 9.5), (int) Math.round(Math.random() * 9.5));

        if (ordi > 0.5) {
            orientation = "Vertical";

        } else {
            orientation = "Horizontal";
        }
        while (verifPosition(position, 4, orientation) != true) {
            position = new Position((int) Math.round(Math.random() * 9.5), (int) Math.round(Math.random() * 9.5));
        }
        Log.i("position", "spino"+position);
        initSpinoAd(position, orientation);

        ///////////////Carno

        ordi = Math.random();
        position = new Position((int) Math.round(Math.random() * 9.5), (int) Math.round(Math.random() * 9.5));
        if (ordi > 0.5) {

            orientation = "Vertical";

        } else {
            orientation = "Horizontal";
        }

        while (verifPosition(position, 3, orientation) != true) {
            position = new Position((int) Math.round(Math.random() * 9.5), (int) Math.round(Math.random() * 9.5));
        }
        Log.i("position", "carno"+position);
        initCarnoAd(position, orientation);
        //////////////trex
        ordi = Math.random();
        position = new Position((int) Math.round(Math.random() * 9.5), (int) Math.round(Math.random() * 9.5));

        if (ordi > 0.5) {

            orientation = "Vertical";
        } else {
            orientation = "Horizontal";

        }while (verifPosition(position, 3, orientation) != true) {
            position = new Position((int) Math.round(Math.random() * 9.5), (int) Math.round(Math.random() * 9.5));
        }
        Log.i("position", "trex"+position);
        initTrexAd(position, orientation);

        /////// velo
        ordi = Math.random();
        position = new Position((int) Math.round(Math.random() * 9.5), (int) Math.round(Math.random() * 9.5));
        if (ordi > 0.5) {

            orientation = "Vertical";

        } else {
            orientation = "Horizontal";

        }while (verifPosition(position, 2, orientation) != true) {
            position = new Position((int) Math.round(Math.random() * 9.5), (int) Math.round(Math.random() * 9.5));
        }
        Log.i("position", "velo"+position);
        initVeloAd(position, orientation);

        afficherAdverser();


    }
    // verification, dino adverse dans grille et pas de chevauchement
    public boolean verifPosition(Position p, int taille, CharSequence orientation){
        int t=0;

        if(orientation.equals("Vertical")) {
            Log.i("verif p +orientation:","" + orientation+ "  "+ p);
            if (p.getLigne() + taille >=10) {
                return false;
            } else {

                for (int i = 0; i < taille; i++) {
                    Log.i("verif p couleur case", "" +bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne() + i][p.getColonne()].getCouleur());
                    if (bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne() + i][p.getColonne()].getCouleur() != Couleur.BLANC) {
                        return false;
                    }
                    t++;
                }
                if (t != taille) {
                    return false;
                }
            } return true; //end if

        }//end if verit
        else {
            if (p.getColonne() + taille >= 10) {
                return false;
            } else {
                for (int i = 0; i < taille; i++) {
                    if (bataille.getBataille().getGrilleAdverse().getPlateauJetons()[p.getLigne()][p.getColonne() + i].getCouleur()!= Couleur.BLANC) {
                        return false;
                    }
                    t++;
                }
                if (t != taille) {
                    return false;
                }
            }return true;

        }//end horiz

    }

    public boolean verifPositionJoueur(Position p, int taille, CharSequence orientation){
        int t=0;

        if(orientation.equals("Vertical")) {
            Log.i("verif p +orientation:","" + orientation+ "  "+ p);


            if (p.getLigne() + taille >10) {
                return false;
            } else {

                for (int i = 0; i < taille; i++) {
                    Log.i("verif p couleur case", "" +bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne() + i][p.getColonne()].getCouleur());
                    if (bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne() + i][p.getColonne()].getCouleur() != Couleur.BLANC) {
                        return false;
                    }
                    t++;
                }
                if (t != taille) {
                    return false;
                }
            } return true; //end if

        }//end if verti
        else {
            if (p.getColonne() + taille > 10) {
                return false;
            } else {
                for (int i = 0; i < taille; i++) {
                    if (bataille.getBataille().getGrilleJoueur().getPlateauJetons()[p.getLigne()][p.getColonne() + i].getCouleur()!= Couleur.BLANC) {
                        return false;
                    }
                    t++;
                }
                if (t != taille) {
                    return false;
                }
            }return true;

        }//end horiz

    }
}



