/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

/**
 *
 * @author Marie
 */
public class Labyrinthe {
    int nbColonneTemp ; /// SANS les murs autour
    int nbLigneTemp ; /// Sans les murs autour
    int nbColonne ; /// labyrinthe avec les murs
    int nbLigne ; ///
    
    int casePosition ;
    int nbCasesNonVisitee ;
    
    int[][] position ;
    char[][] labyrinthe_temporaire ;
    
    String[][] labyrinthe;
    
    public Labyrinthe(int n, int m){
        
        nbColonne = n ;
        nbLigne = m ;
        
        nbColonneTemp = n-2 ; /// Sans le mur colonne = 0 et sans le mur colonne = n-1
        nbLigneTemp = m-2 ; /// Sans le mur ligne = 0 et sans le mur ligne = m-1
        nbCasesNonVisitee = nbColonneTemp * nbLigneTemp ;
        position = new int[2][nbCasesNonVisitee*2] ;
        
        labyrinthe_temporaire = new char[nbLigneTemp][nbColonneTemp] ;
        labyrinthe = new String[nbLigne][nbColonne] ;
    }
    
    public void initialisation(){
        for(int i = 0 ; i < nbLigneTemp ; i++){
            for(int j = 0 ; j < nbColonneTemp ; j++){
                labyrinthe_temporaire[i][j] = 'x' ;
            }    
        }
        labyrinthe_temporaire[nbLigneTemp-1][0] = '.' ; /// ON commence par la case la plus en bas à gauche
        position[0][0] = nbLigneTemp-1;
        position[1][0] = 0 ;
        casePosition = 1 ;
        nbCasesNonVisitee-- ;
    } 
    
    public void companionCube(){
        int randomLigne = 8 ;
        int randomLigneTemp = 8 ;
        int randomColonne = 5;
        int randomColonneTemp = 5 ;
        int nbCompanionCube = 3 ;
        do{
            if(labyrinthe[randomLigne][randomColonne].equals(Map.elementCarte.rien.toString())){
                labyrinthe[randomLigne][randomColonne]=Map.elementCarte.blocSpecial.toString() ;
                nbCompanionCube-- ;
                
                do{
                    randomLigneTemp=(int)(Math.random()*14)+1 ;
                    System.out.println(randomLigneTemp) ;
                }while( randomLigne==(randomLigneTemp+1) || randomLigne==(randomLigneTemp-1) ) ;
                
                do{
                    randomColonneTemp=(int)(Math.random()*8)+1 ;
                }while(randomColonne==(randomColonneTemp+1)||randomColonne==(randomColonneTemp-1)) ;
                randomLigne = randomLigneTemp ;
                randomColonne = randomColonneTemp ;
                
            }
            else{
                randomLigne=(int)(Math.random()*14)+1 ;
                randomColonne=(int)(Math.random()*8)+1 ;
            }
        
        }while(nbCompanionCube>0) ; 
    }
    
    public void Pengo(int nbPengo){
        int ligne ;
        int colonne ;
        char direction ;
        switch (nbPengo) {
             case 1 : {
                 ligne = 8 ; colonne = 6 ;
                 do{
                    if(labyrinthe[ligne][colonne].equals(Map.elementCarte.rien.toString())){
                        nbPengo-- ;
                        labyrinthe[ligne][colonne]=Map.elementCarte.pengo.toString() ;
                    }
                    else{
                        direction = regardeAutour(ligne,colonne);
                        switch (direction){
                            case 'B' : {
                            labyrinthe[ligne+1][colonne] =Map.elementCarte.pengo.toString();
                            nbPengo-- ;
                            } break ;
                            case 'H' :{
                                labyrinthe[ligne-1][colonne] = Map.elementCarte.pengo.toString();
                                nbPengo-- ;
                            } break ;
                            case 'D':{
                                labyrinthe[ligne][colonne+1] = Map.elementCarte.pengo.toString() ;
                                nbPengo-- ;
                            } break ;
                            case 'G' :{
                                labyrinthe[ligne][colonne-1] = Map.elementCarte.pengo.toString() ;
                                nbPengo-- ;
                            } break ;
                            case '/' : {
                                ligne-- ;
                                colonne -- ;
                            }
                        }
                    }
                } while(nbPengo>0) ;     
            } break ;
             case 2 : {  
            }     
        }         
    }
    
    public void blocEmpoisonne(int nbBE){
        int randomLigne = (int)(Math.random()*14)+1 ;
        int randomLigneTemp ;
        int randomColonneTemp ;
        int randomColonne = (int)(Math.random()*8)+1 ;
        do{
           if(labyrinthe[randomLigne][randomColonne].equals(Map.elementCarte.blocGlace.toString())){
               labyrinthe[randomLigne][randomColonne]=Map.elementCarte.blocAvecSnoBees.toString();
               do{
                    randomLigneTemp=(int)(Math.random()*14)+1 ;
                }while( randomLigne==(randomLigneTemp+3) || randomLigne==(randomLigneTemp-3) ) ;
                do{
                    randomColonneTemp=(int)(Math.random()*8)+1 ;
                }while(randomColonne==(randomColonneTemp+2)||randomColonne==(randomColonneTemp-2)) ;
               nbBE-- ;
           } 
           else{
               randomLigne = (int)(Math.random()*14)+1 ;
               randomColonne = (int)(Math.random()*8+1) ;
           }
        } while(nbBE >0) ;
    }
    
    public void SnoBees(int nbSB){
        int randomLigne = (int)(Math.random()*15)+1 ;
        int randomLigneTemp ;
        int randomColonneTemp ;
        int randomColonne = (int)(Math.random()*8)+1 ;
        do{
           if(labyrinthe[randomLigne][randomColonne].equals(Map.elementCarte.rien.toString())){
               labyrinthe[randomLigne][randomColonne]=Map.elementCarte.snoBees.toString() ;
               do{
                    randomLigneTemp=(int)(Math.random()*14)+1;
                }while( randomLigne==(randomLigneTemp+3) || randomLigne==(randomLigneTemp-3) ) ;
                do{
                    randomColonneTemp=(int)(Math.random()*8)+1 ;
                }while(randomColonne==(randomColonneTemp+2)||randomColonne==(randomColonneTemp-2)) ;
               nbSB-- ;
           } 
           else{
               randomLigne = (int)(Math.random()*14)+1 ;
               randomColonne = (int)(Math.random()*8)+1 ;
           }
            
        }while(nbSB >0);
    }
    
    public void finalisation(){
        for(int i = 0 ; i < nbLigne ; i++)
            for(int j = 0 ; j < nbColonne ; j++){
                if(i==0 || i == (nbLigne-1) || j==0 || j==(nbColonne-1))
                    labyrinthe[i][j]=Map.elementCarte.mur.toString() ;
            }
                        
        for(int i = 0 ; i < nbLigneTemp ; i++)
            for(int j = 0 ; j < nbColonneTemp ; j++){

                if(labyrinthe_temporaire[i][j]=='x' || labyrinthe_temporaire[i][j]=='X')
                    labyrinthe[i+1][j+1]=Map.elementCarte.blocGlace.toString() ;
                else
                    if(labyrinthe_temporaire[i][j]=='.')
                     labyrinthe[i+1][j+1]=Map.elementCarte.rien.toString() ;    
            }
        
       
    }
    
    public void affichage(){
        for(int i=0 ; i < nbLigne ; i++){
            for(int j = 0 ; j < nbColonne ; j++)
                System.out.print(labyrinthe[i][j]);
            System.out.println("");
        }
    }
    
    public void setPosition(int x, int y){
        position[0][casePosition] = x ;
        position[1][casePosition] = y ;
        labyrinthe_temporaire[x][y] = '.' ;
        casePosition++ ;
        nbCasesNonVisitee-- ;
    }
  
    public void mettreMurIntersection(int x, int y){
        if(x>=1 && x<=(nbLigneTemp-2) && y>=1 && y<=(nbColonneTemp-2)){
            if( labyrinthe_temporaire[x][y]=='.' ){
                
                    if(labyrinthe_temporaire[x+1][y]=='.' && labyrinthe_temporaire[x+1][y-1]=='.')
                        setMur(x,y-1) ;
                    if(labyrinthe_temporaire[x+1][y]=='.' && labyrinthe_temporaire[x+1][y+1]=='.')
                        setMur(x,y+1) ;
                    
                    if(labyrinthe_temporaire[x-1][y]=='.' && labyrinthe_temporaire[x-1][y+1]=='.')
                        setMur(x,y+1) ;
                    if(labyrinthe_temporaire[x-1][y]=='.' && labyrinthe_temporaire[x-1][y-1]=='.')
                        setMur(x,y-1) ;
                               
                    
                    if(labyrinthe_temporaire[x][y+1]=='.' && labyrinthe_temporaire[x+1][y+1]=='.')
                        setMur(x+1,y) ;
                    if(labyrinthe_temporaire[x][y+1]=='.' && labyrinthe_temporaire[x-1][y+1]=='.')
                        setMur(x-1,y);
                    
                    if(labyrinthe_temporaire[x][y-1]=='.' && labyrinthe_temporaire[x+1][y-1]=='.')
                        setMur(x+1,y);
                    if(labyrinthe_temporaire[x][y-1]=='.' && labyrinthe_temporaire[x-1][y-1]=='.')
                        setMur(x-1,y);
                }
            }
        
    }
    
    public void mettreMurTriangle(int x, int y){
        
        ////// VOIR SI TRIANGLE 
        if(x>=1 && x<=(nbLigneTemp-2) && y>=1 && y<=(nbColonneTemp-2)){
            if(labyrinthe_temporaire[x-1][y+1]=='X' && labyrinthe_temporaire[x][y+1]=='.')
                setMur(x-1,y);
            else
                if(labyrinthe_temporaire[x-1][y+1]=='X' && labyrinthe_temporaire[x-1][y]=='.')
                    setMur(x,y+1);
                else
                    if(labyrinthe_temporaire[x-1][y-1]=='X' && labyrinthe_temporaire[x-1][y]=='.')
                       setMur(x,y-1);
                    else
                        if(labyrinthe_temporaire[x-1][y-1]=='X' && labyrinthe_temporaire[x][y-1]=='.')
                           setMur(x-1,y);
                        else
                            if(labyrinthe_temporaire[x+1][y-1]=='X' && labyrinthe_temporaire[x][y-1]=='.')
                               setMur(x+1,y);
                            else
                                if(labyrinthe_temporaire[x+1][y-1]=='X' && labyrinthe_temporaire[x+1][y]=='.')
                                   setMur(x,y-1);
                                else
                                    if(labyrinthe_temporaire[x+1][y+1]=='X' && labyrinthe_temporaire[x+1][y]=='.')
                                       setMur(x,y+1);
                                    else
                                        if(labyrinthe_temporaire[x+1][y+1]=='X' && labyrinthe_temporaire[x][y+1]=='.')
                                           setMur(x+1,y);
                }       
    }
    
    public void setMur(int x, int y){
        if( ( x>= 0 ) && ( x < nbLigneTemp )  && ( y >= 0 ) && ( y < nbColonneTemp ) ){
            if( labyrinthe_temporaire[x][y]=='x' ){
                labyrinthe_temporaire[x][y] = 'X' ; 
                nbCasesNonVisitee-- ;
                if(y<(nbColonneTemp-1) && y>1 && x<(nbLigneTemp-1) && x>1)
                    mettreMurTriangle(x,y) ;
            }
        }
    }
    
    public void creationLabyrinthe(){
        int ligne ;
        int colonne ;
        char anciennePosition = '/' ;
        do{
            if(casePosition==0)
                nbCasesNonVisitee = 0 ;
            
            else {
                ligne = position[0][casePosition-1] ;
                colonne = position[1][casePosition-1] ;
            
                char prochainePosition = regardeAutour(ligne, colonne) ;
                switch (prochainePosition){
                    case 'H' : {
                        setPosition(ligne-1,colonne) ;
                        mettreMurIntersection(ligne-1,colonne) ;

                        if(anciennePosition=='D')
                            setMur(ligne-1,colonne-1);
                        else
                            if(anciennePosition=='G')
                                setMur(ligne-1,colonne+1);
                            else
                                if(anciennePosition=='H' && colonne==(nbColonneTemp-2))
                                    setMur(ligne,nbColonneTemp-1);
                                else
                                    if(anciennePosition=='H' && colonne == 1)
                                        setMur(ligne,0);
                    } break ;
                    
                    case 'D' : {           
                        setPosition(ligne,colonne+1) ;
                        mettreMurIntersection(ligne, colonne+1) ;
                        if(colonne==1 && ligne==0)
                            setMur(0,0) ;
                        if(anciennePosition=='H')
                            setMur(ligne+1,colonne+1);
                        else
                            if(anciennePosition=='B')
                                setMur(ligne-1,colonne+1);
                    } break ;  
                    
                    case 'B' : {
                        setPosition(ligne+1,colonne ) ;
                        mettreMurIntersection(ligne+1,colonne) ;
                        if(anciennePosition=='G')
                            setMur(ligne+1,colonne+1);
                        else
                            if(anciennePosition=='D')
                                setMur(ligne+1,colonne-1) ;
                        if(anciennePosition=='B')
                            if(colonne==1)
                                setMur(ligne,0);
                            else
                                if(colonne==(nbColonneTemp-2))
                                    setMur(ligne,nbColonneTemp-1) ;
                        else
                            if(colonne==1)
                                setMur(ligne+1,0);
                            else
                                if(colonne==(nbColonneTemp-2))
                                    setMur(ligne+1,nbColonneTemp-1);

                    } break ;   
                    
                    case 'G' : {
                        setPosition(ligne, colonne-1) ;
                        mettreMurIntersection(ligne,colonne-1) ;

                        if(anciennePosition=='H'){
                            setMur(ligne+1,colonne-1);

                            if(ligne==1 && colonne==(nbColonneTemp-2))
                                setMur(0,nbColonneTemp-1);
                        }
                        else
                            if(anciennePosition=='B')
                                setMur(ligne-1,colonne-1);
                            else
                                if(ligne==1 && anciennePosition=='G')
                                    setMur(0,colonne-1);
                    } break ;
                    
                    case '/' : {
                        casePosition-- ;
                        prochainePosition = anciennePosition ;
                    }
                }
                anciennePosition = prochainePosition ;
            }
        }
        while(nbCasesNonVisitee>0);
    }
    
    public char regardeAutour(int x, int y){
        int i ;
        boolean test ;
        boolean[] nonVisite = new boolean[4] ; // 0 = en haut, 1 = à droite, 2 = en bas, 3 = à gauche
        for( i = 0 ; i < 4 ; i++ ) 
            nonVisite[i] = false ; /// Toutes les cases autour ont été visitées
        
        if(x==0 && y ==0){
            /// On ne peut regarder qu'en bas et à droite si déja visitée
            if(labyrinthe_temporaire[x][y+1]=='x' ){ /// à droite /// SI case à droite n'est pas déjà visitée
                nonVisite[1] = true ; /// On met non visitée à true
            }
            if(labyrinthe_temporaire[x+1][y]=='x') /// en bas
                nonVisite[2] = true ;
        }
        else{
            if(x==0 && y ==(nbColonneTemp-1)){ /// On ne peut regarder qu'en bas et à gauche
                if(labyrinthe_temporaire[x][y-1]=='x') /// à gauche
                    nonVisite[3] = true ;
                if(labyrinthe_temporaire[x+1][y]=='x') /// en bas
                    nonVisite[2] = true ;
            }
            else {
                if(x==(nbLigneTemp-1) && y == 0){ // On ne peut regarder qu'en haut et à droite
                    if(labyrinthe_temporaire[x-1][y]=='x') ///en haut
                        nonVisite[0] = true ;
                    if(labyrinthe_temporaire[x][y+1]=='x')///à droite
                        nonVisite[1] = true ;
                }
                else{
                    if(x==(nbLigneTemp-1) && y == (nbColonneTemp-1)){ /// On ne peut regarder qu'en haut et à gauche
                        if(labyrinthe_temporaire[x-1][y]=='x') /// en haut
                            nonVisite[0] = true ;
                        if(labyrinthe_temporaire[x][y-1]=='x') /// à gauche
                            nonVisite[3] = true ;
                    }
                    
                    else { /// SI PAS DANS COIN
                        if(x!=0) /// Si pas dans premiere ligne
                            if(labyrinthe_temporaire[x-1][y]=='x') /// en haut
                                nonVisite[0] = true ;
                            
                        if(x!=(nbLigneTemp-1)) /// si pas dans derniere ligne
                            if(labyrinthe_temporaire[x+1][y]=='x' ) /// en bas
                                nonVisite[2] = true ;
                                  
                        
                        if(y!=0) /// si pas dans premiere colonne
                            if(labyrinthe_temporaire[x][y-1]=='x') /// à gauche
                                nonVisite[3] = true ;
                        
                        if(y!=(nbColonneTemp-1)) /// si pas dans derniere colonne
                            if(labyrinthe_temporaire[x][y+1]=='x') /// à droite
                                nonVisite[1] = true ;
                    }
                } /// FIN SI NI dans coin en haut à droite / gauche ni dans coin bas gauche      
            } /// FIN si dans pas dans un coin en hat
        } /// FIN SI pas coin haut / gauche
        
    i = 0 ;
    test = false ;
    do{
      if(nonVisite[i]==true)
          test = true ;
      else
          i++ ;
    }
    while(test==false && i < 4);  
    int random ; 
    if(test==true){
        do{
            random = (int)(Math.random()*4) ;
            test = nonVisite[random] != false;
        }while(test!=true) ;
        
        switch (random){
            case 0 :
                return 'H' ;
            case 1 : 
                return 'D' ;
            case 2 : 
                return 'B' ;
            case 3 : 
                return 'G' ;
        }
    }
    return '/' ;
}
    
} /// FIN

