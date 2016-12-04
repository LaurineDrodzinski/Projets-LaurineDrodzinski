import extensions.*;

class dinogo extends Program {

    

    /*SONS*/
    Sound menu_sound = newSound("barn-beat-01.mp3");
    Sound bouton = newSound("button-11.mp3");

    /*CSV*/
    CSVFile ScoresLoad = loadCSV("score-joueurs.csv",';');//Scores des joueurs
    CSVFile QuestionsJ1 = loadCSV("questions.csv",';');//questions/reponses J1 (bonne reponse : derniere colonne)
    CSVFile QuestionsJ2 = loadCSV("questionsJ2.csv",';');//questions/reponses J2 (bonne reponse : derniere colonne)
    CSVFile DinoJ1;//Le dinosaure en construction du J1 sera affecte a cette variable
    CSVFile DinoJ2;//Le dinosaure en construction du J2 sera affecte a cette variable

    /*VARIABLES*/
    String[] fichiersDinoJ1 = new String[15];//Contiendra les differents noms de fichiers concernant les dinosaures a construire
    String[] fichiersDinoJ2 = new String[15];//Contiendra les differents noms de fichiers concernant les dinosaures a construire
    int ligneAnim = 0;//Pour l'animation dans l'intro
    char touche;//Prendra comme valeur la touche sur laquelle a appuye le joueur
    boolean mode2joueurs;//Mode 2 joueurs actif ou non
    boolean RepeterPlusDeUneFois=false;//Utile dans le void algorithm,pour eviter certaines repetitions
    
    //TESTS============================================================================================================
    //=================================================================================================================
    
    void testTriScores(){
	String[][]tab=new String [][]{{"nom2","2"},
				      {"nom1","1"},
				      {"nom3","3"}};
	
	String[][]res=new String[][]{{"nom3","3"},
				     {"nom2","2"},
				     {"nom1","1"}};	
	triScores(tab);
	assertArrayEquals(tab,res);
    }
   
    void testChar_Bonne_Reponse(){
	assertEquals('1',char_bonne_reponse(0,0,false));
	assertEquals('2',char_bonne_reponse(1,1,false));
	assertEquals('1',char_bonne_reponse(2,2,false));
	assertEquals('3',char_bonne_reponse(5,5,false));
	assertEquals('3',char_bonne_reponse(2,2,true));
	assertEquals('1',char_bonne_reponse(1,0,true));
    }

    void testBonne_Reponse(){
	assertTrue(bonne_reponse('1',char_bonne_reponse(0,0,false)));
	assertTrue(bonne_reponse('2',char_bonne_reponse(1,0,false)));
	assertTrue(bonne_reponse('3',char_bonne_reponse(2,2,true)));
	assertTrue(bonne_reponse('1',char_bonne_reponse(1,0,true)));
	assertFalse(bonne_reponse('3',char_bonne_reponse(2,0,false)));
	assertFalse(bonne_reponse('8',char_bonne_reponse(5,5,false)));
	assertFalse(bonne_reponse('5',char_bonne_reponse(3,0,false)));
	assertFalse(bonne_reponse('9',char_bonne_reponse(1,0,true)));
    }

    void testAffichageMenus(){
	affichageMenus(true);
	println("*********");
	affichageMenus(false);
    }

    void testAffichageQuestionsReponses(){
	mode2joueurs=false;
	affichage_question_reponses(0,0,false);
	affichage_question_reponses(6,6,false);
	affichage_question_reponses(1,1,true);	
	mode2joueurs=true;
	affichage_question_reponses(0,0,false);
	affichage_question_reponses(0,0,true);
	affichage_question_reponses(6,6,false);
	affichage_question_reponses(6,6,true);
	
    }

    void testAffichageBonMauvais(){
	println("J1 : correct facile");
	affichage_bon_mauvais(true,false,1,'1');
	println("J1 : incorrect facile");
	affichage_bon_mauvais(false,false,1,'1');
	println("J1 : correct difficile");
	affichage_bon_mauvais(true,false,1,'2');
	println("J1 : correct difficile");
	affichage_bon_mauvais(true,false,5,'2');
	println("J1 : incorrect difficile");
	affichage_bon_mauvais(false,false,1,'2');
	println("J2 : correct");
	affichage_bon_mauvais(true,true,1,'1');
	println("J1 : incorrect");
	affichage_bon_mauvais(false,true,1,'1');
	
    }

    //=================================================================================================================
    //=================================================================================================================
    
    void algorithm(){
		
	initialisationTableauDino();
	touche = '0';//Renitialise la capture de touche
	mode2joueurs = false;//Au depart le mode 2 joueurs est desactive
	clearScreen();//Nettoie le terminal
	hide();//Cache le curseur
	
	if(!RepeterPlusDeUneFois){//Pour eviter de repeter l'intro et de reactiver la capture a chaque lancement du void algorithm
	    intro();
	    enableKeyTypedInConsole(true);
	    RepeterPlusDeUneFois = true;
	}
	
	cursor(0,0);//Replace le curseur au début de la console
	menu();
	
    }

    //=================================================================================================================
    //=================================================================================================================
    
    void keyTypedInConsole(char key) {//Pour la capture de touches
	touche = key;
    }
    
    //=================================================================================================================
    //=================================================================================================================
    
    void initialisationTableauDino(){//Initialise la liste des noms de fichiers concernant les dinosaures
	int num;
	for(int i=0;i<length(fichiersDinoJ1);i++){
	    num=i+1;
	    fichiersDinoJ1[i] = "dino"+num+".csv";
	    fichiersDinoJ2[i] = "dino"+num+".csv";
	}
    }

    //=================================================================================================================
    //=================================================================================================================
    
    /*Introduction*/
    void intro(){
        
    CSVFile Terre = loadCSV("intro.csv",'$');//animation
    CSVFile Dinogo = loadCSV("introDinogo.csv",'$');//titre
    CSVFile Dinosaure = loadCSV("introDinosaures.csv",'$');//dinosaure (gauche)
    CSVFile Dinosaurebis = loadCSV("introDinosauresbis.csv",'$');//dinosaure (droite)
    
	Sound intro = newSound("Intro_sound-4.mp3");
	play(intro,true);
	
	/*Tant que l'on a pas parcouru toutes les lignes du fichier (tant que l'animation n'est pas finie)*/ 
	while(ligneAnim<rowCount(Terre)){

	    /*Titre:DINOGO*/
	    for(int ligneDinogo=0;ligneDinogo<rowCount(Dinogo);ligneDinogo++){
		cursor(ligneDinogo+3,16);
		println(getCell(Dinogo,ligneDinogo,0));
	    }

	    /*Dinosaures*/
	    for(int ligneDinosaure=0;ligneDinosaure<rowCount(Dinosaure);ligneDinosaure++){
		cursor(ligneDinosaure+14,2);
		println(getCell(Dinosaure,ligneDinosaure,0));
		cursor(ligneDinosaure+14,109);
		println(getCell(Dinosaurebis,ligneDinosaure,0));
	    }

	    cursor(14,0);

	    /*Animation de la terre*/
	    while(!equals(getCell(Terre,ligneAnim,0),"a") && !equals(getCell(Terre,ligneAnim,0),"@")){//Pour passer a la terre suivante
		println(getCell(Terre,ligneAnim,0));
		ligneAnim = ligneAnim+1;
	    }
	    ligneAnim = ligneAnim+1;//Permet de passer la ligne contenant le 'a' separant les differentes terres	    
	    delay(110);
	    clearScreen();
	}

    }

    //=================================================================================================================
    //=================================================================================================================

    /*Menu principal*/
    void menu(){
        
	play(menu_sound,true);
	boolean menuPrincipalFini = false;
	touche = '0';
	affichageMenus(menuPrincipalFini);
	
	do{
	    delay(100);
	    if(touche=='1'){//Mode 1 joueur
		play(bouton,true);
		menuPrincipalFini = true;
	    difficulte(menuPrincipalFini);
	    }else if(touche=='2'){//Mode 2 joueurs
		play(bouton,true);
		menuPrincipalFini = true;
		mode2joueurs = true;
		game_2j();
	    }else if(touche=='3'){//Classement
		play(bouton,true);
		menuPrincipalFini = true;
		affichageScores();
	    }else if(touche=='4'){//Regles
		play(bouton,true);
		clearScreen();
		affReglesJeu();
		cursor(0,0);
	    }else if(touche=='q'){//Quitter
		stop(menu_sound);
		clearScreen();
		cursor(0,0);
		return;
	    }
	}while(!menuPrincipalFini);

    }
    
    //=================================================================================================================
    //=================================================================================================================  
    
    /*Menu secondaire, celui du choix de la difficulte*/
    void difficulte(boolean menuPrincipalFini){
        
	boolean menuSecondaireFini = false;
	touche = '0';
	affichageMenus(menuPrincipalFini);
	do{
	    delay(100);
	    if(touche>='1' && touche<='2'){
		stop(menu_sound);
		play(bouton,true);
		menuSecondaireFini = true;
		game_1j(touche);
	    }else if(touche=='q'){//Quitter
		clearScreen();
		cursor(0,0);
		return;
	    }
	}while(!menuSecondaireFini);
	
    }
  
    //=================================================================================================================
    //=================================================================================================================
    
    /*Regles*/
    void affReglesJeu(){
        
	CSVFile Regles = loadCSV("regle.csv",'$');
	cursor(4,5);
	for (int i=0;i<rowCount(Regles);i++){
	    println(getCell(Regles,i,0));
	}
	sortir();
	
    }
   
    //=================================================================================================================
    //=================================================================================================================
    
    void sortir(){
        
	boolean fini = false;
	do{
	    delay(100);
	    if (touche=='m'){
		fini=true;
		stop(menu_sound);
		algorithm();
	    }else if (touche=='q'){
		clearScreen();
		cursor(20,70);
		print("A bientot !");
		delay(1000);
		cursor(0,0);
		clearScreen();
		return;
	    }
	}while(!fini);
	
    }
 
    //=================================================================================================================
    //=================================================================================================================   

    void affichageMenus(boolean menuPrincipalFini){
	
	clearScreen();
	decor(false);//false: n'affiche pas les options "quitter" et "menu"

	if(!menuPrincipalFini){//Si le menu principal n'est pas termine
	    
	    //Affichage du menu
	    cursor(7,16);
	    println("1) 1 joueur");
	    cursor(8,16);
	    println("2) 2 joueurs");
	    cursor(9,16);
	    println("3) Classement");
	    cursor(10,16);
	    println("4) Règles du jeu");
	    cursor(11,16);
	    println("q) Quitter");
	    //Le joueur choisit une section
	    cursor(12,16);
	    print("Faites votre choix (tapez 1, 2, 3 ou 4): ");
	    
	}else if(menuPrincipalFini){//Si le menu principal est termine
	    
	    //Affichage du menu secondaire
	    cursor(8,16);
	    println("1) Facile");
	    cursor(9,16);
	    println("2) Difficile");

	    //Le joueur choisit une difficulte
	    cursor(11,16);
	    print("Faites votre choix (tapez 1 ou 2): ");	    
	}
	
    }

    //=================================================================================================================
    //=================================================================================================================

    //Convertit la valeur de getTime en secondes
    int tempsJoueur(){
        
	int tempsj;
	tempsj=(int)getTime();
	return tempsj/1000;
	
    }
  
    //=================================================================================================================
    //=================================================================================================================
    
    /*Affiche le decor (cadre...etc)*/
    void decor(boolean jeu){
        
    if(jeu){//Si on est dans le jeu et pas dans le menu
	cursor(50,130);	   
	print("Quitter: \"q\"");
    }
	    
	if(!mode2joueurs){//Si mode 1 joueur: 1 cadre
	    for(int i=0;i<141;i=i+3){//lignes horizontales du cadre
		cursor(2,10+i);
		print("-~-");	    
		cursor(3,10+i);
		print("-~-");
		cursor(15,10+i);
		print("-~-");
		cursor(16,10+i);
		print("-~-");
	    }
	    for(int i=0;i<13;i++){//lignes verticales du cadre
		cursor(3+i,10);
		print("|");
		cursor(3+i,150);
		print("|");
	    }
	}else{//Si mode 2 joueurs: 2 cadres
	    for(int i=0;i<68;i=i+3){//lignes horizontales du cadre
		cursor(2,3+i);
		print("-~-");	    
		cursor(3,3+i);
		print("-~-");
		cursor(15,3+i);
		print("-~-");
		cursor(16,3+i);
		print("-~-");
		
		cursor(2,81+i);
		print("-~-");	    
		cursor(3,81+i);
		print("-~-");
		cursor(15,81+i);
		print("-~-");
		cursor(16,81+i);
		print("-~-");
		
	    }
	    for(int i=0;i<13;i++){//lignes verticales du cadre
		cursor(3+i,3);
		print("|");
		cursor(3+i,71);
		print("|");
		
		cursor(3+i,81);
		print("|");
		cursor(3+i,149);
		print("|");
	    }
	}
	
    }
 
 //=================================================================================================================
 //=================================================================================================================
    
    /*Affiche la question et les reponses possibles*/
    void affichage_question_reponses (int cptJ1,int cptJ2,boolean tourJ2){
        
	CSVFile questions;//Fichier contenant les questions
	int placement;//Pour les curseurs
	int cpt_ligne;//Ligne de la question a afficher
	int longueur;//Longueur maximale de la question (si la phrase est trop longue -> retour a la ligne)
	int c1,c2,c3;//Pour definir a quel caractere on coupe la phrase
	
	if(!mode2joueurs){//Si 1 joueur
	    questions = QuestionsJ1;
	    placement = 12;
	    cpt_ligne = cptJ1;
	    longueur = 135;
	}else{//Si 2 joueurs
	    longueur = 62;
	    if(!tourJ2){//Si au tour du J1
		questions = QuestionsJ1;
		placement = 5;
		cpt_ligne = cptJ1;
	    }else{//Si au tour du J2
		questions = QuestionsJ2;
		placement = 83;
		cpt_ligne = cptJ2;
	    }
	}
	c1=longueur;
	c2=longueur*2;
	c3=longueur*3;

	//Question (affichage adapte en fonction de la longueru des questions)
	cursor(5,placement);
	
	if(length(getCell(questions,cpt_ligne,0))<=longueur){
	
	    println(getCell(questions,cpt_ligne,0) + " ?");//Affiche la question sur une seule ligne
	    
	}else if(length(getCell(questions,cpt_ligne,0))>longueur && length(getCell(questions,cpt_ligne,0))<=longueur*2){
	    
	    while(charAt(getCell(questions,cpt_ligne,0),c1)!=' '){//Pour eviter de couper en plein milieu d'un mot	
		c1=c1-1;
	    }
	    
	    println(substring(getCell(questions,cpt_ligne,0),0,c1));//Affiche une partie sur la 1ere ligne
	    println(substring(getCell(questions,cpt_ligne,0),c1+1,length(getCell(questions,cpt_ligne,0)))+ '?');//2eme ligne
	    
	}else if(length(getCell(questions,cpt_ligne,0))>longueur*2 && length(getCell(questions,cpt_ligne,0))<=longueur*3){
	    
	    while(charAt(getCell(questions,cpt_ligne,0),c1)!=' '){//Pour eviter de couper en plein milieu d'un mot			
		c1=c1-1;
	    }
	    
	    println(substring(getCell(questions,cpt_ligne,0),0,c1));//1ere ligne
	    
	    while(charAt(getCell(questions,cpt_ligne,0),c2)!=' '){//Pour eviter de couper en plein milieu d'un mot			
		c2=c2-1;
	    }
	    
	    println(substring(getCell(questions,cpt_ligne,0),c1+1,c2));//2eme ligne	    
	    println(substring(getCell(questions,cpt_ligne,0),c2+1,length(getCell(questions,cpt_ligne,0)))+ '?');//3eme ligne
	    
	}else{
	    
	    while(charAt(getCell(questions,cpt_ligne,0),c1)!=' '){//Pour eviter de couper en plein milieu d'un mot			
		c1=c1-1;
	    }
	    
	    println(substring(getCell(questions,cpt_ligne,0),0,c1));//1ere ligne
	    
	    while(charAt(getCell(questions,cpt_ligne,0),c2)!=' '){//Pour eviter de couper en plein milieu d'un mot			
		c2=c2-1;
	    }
	    
	    println(substring(getCell(questions,cpt_ligne,0),c1+1,c2));//2eme ligne

	    while(charAt(getCell(questions,cpt_ligne,0),c3)!=' '){//Pour eviter de couper en plein milieu d'un mot			
		c3=c3-1;
	    }	    
	    
	    println(substring(getCell(questions,cpt_ligne,0),c2+1,c3));//3eme ligne
	    println(substring(getCell(questions,cpt_ligne,0),c3+1,length(getCell(questions,cpt_ligne,0)))+ '?');//4eme ligne
	}

	//Reponses
	for(int i=1;i<4;i++){
	    
	    cursor(i+8,placement);
	    if(!tourJ2){//Si au tour duJ1
		if(i<3){
		    println(((char)(i+118))+") "+getCell(questions,cpt_ligne,i));//Pour afficher "w" et "x"
		}else{		    
		    println(((char)(i+96))+") "+getCell(questions,cpt_ligne,i));//Pour afficher "c"
		}
	    }else{//Si au tour du J2
		println(i+") "+getCell(questions,cpt_ligne,i));//Pour afficher "1", "2" et "3"
	    }

	}	
	cursor(13,placement);
	print("Entrez une reponse: ");
	
    }
 
    //=================================================================================================================
    //=================================================================================================================
   
    /*Repere la bonne reponse et l'associe a un numero ou une lettre (la bonne reponse etant toujours situee dans la derniere colonne)*/
    char char_bonne_reponse(int cptJ1,int cptJ2, boolean tourJ2){
        
	CSVFile questions;//Fichier questions
	int cpt_ligne;//Numero de la question
	
	if(!tourJ2){//Si au tour du J1
	    questions = QuestionsJ1;
	    cpt_ligne = cptJ1;
	}else{
	    questions = QuestionsJ2;
	    cpt_ligne = cptJ2;
	}
	
	for(int i=1; i<columnCount(questions,cpt_ligne)-1;i++){
	    
	    //Si la reponse est egale a la derniere case de la ligne
	    if(equals(getCell(questions,cpt_ligne,i),getCell(questions,cpt_ligne,columnCount(questions,cpt_ligne)-1))){ 
		if(!tourJ2){//Si au tour du J1
		    if(i==1 || i==2){
			return (char)(i+118);//Retourne soit "w" ou "x"
		    }else{
			return (char)(i+96);//Retourne "c"
		    }
		}else{
		    return (char)(i+48);//Retourne soit "1", "2" ou "3"
		}
	    }
	    
	}	
	return '$';
	
    }
 
    //=================================================================================================================
    //=================================================================================================================
 
    /*Verifie si la reponse du joueur est coherente avec la bonne reponse*/
    boolean bonne_reponse(char ReponseJoueur, char CharBonneReponse){
	if(ReponseJoueur==CharBonneReponse){
	    return true;
	}else{
	    return false;
	}	
    }

 //=================================================================================================================
 //=================================================================================================================
    
    /*Affiche si la réponse est correcte ou non*/
    void affichage_bon_mauvais(boolean resultat,boolean tourJ2,long temps,char diff){
        //initialitation des variabes Sound pour les réponses 
	Sound Correct_Answer = newSound("Correct_Answer_Sound_Effect.mp3"); 
	Sound Wrong_Answer = newSound("wrong_Answer.mp3");
	
	int placement;//Pour les curseurs
	
	if(!mode2joueurs){//Si mode 1 joueur
	    placement=33;
	}else{//Si mode 2 joueurs
	    if(!tourJ2){//Si au tour du J1
		placement=25;
	    }else{//Si au tour du J2
		placement=100;
	    }
	}
	
	cursor(8,placement);
	
	if(diff=='1'){//Si mode facile
	    if(resultat){//Si la reponse est correcte
		play(Correct_Answer,true);
		println("Bravo !");
	    }else{//Si la reponse est incorrecte
		play(Wrong_Answer,true); //joue le son pour la mauvaise réponse en arrière plan
		println("Dommage !");	    
	    }
	}else{//Si mode diffcile
	    if(resultat && temps<7){//Si la reponse est correcte et qu'il a etait rapide (moins de 7 secondes)
		play(Correct_Answer,true);
		println("Bravo !");
	    }else if(resultat && temps>=7){//Si la reponse est correcte et qu'il a etait rapide (moins de 7 secondes)
		play(Correct_Answer,true);
		println("Correct !");
		println("Malheureusement tu n'as pas été assez rapide (plus de 7 secondes)!");
	    }else{//Si la reponse est incorrecte
		println("Dommage !");
		println("Tu perds une ligne !");
	    }
	}
		
    }
    
    //=================================================================================================================
    //=================================================================================================================
  
    /*Mode 1 joueur*/
    void game_1j(char diff){
        
	char CharBonneReponse; //Reponse a la question
	char RepJoueur; //Reponse du joueur
	int cptBonnesReponses = 0; //Compte les reponses correctes
	boolean AuMoinsUneBonneReponse; //Passe a true des la premiere bonne reponse, redevient false lors du changement de dinosaure 
	boolean reponseCorrecte; //Analyse si la reponse du joueur est correcte
	int nb_lignes = 0; //Concerne la construction des dinosaures(le dinosaure mesure toujours 22 lignes)
	int nbDino = 0; //Compte le nombre de dinosaures construits
	int tempsIni; //variable pour initialiser le temps avant que le joueur répond 
	int tempsFin;//Temps mis par le joueur pour repondre 
	int tempsFinal = 0;//Temps total mis par le joueur
	int nbQuestions = 1;//Nombre de questions auquel le joueur a repondu
	int[][] tirageQuestions = new int[2][20];//Pour stocker les questions tirees
	int caseTab = 0;//Pour avancer dans le tableau "tirageQuestions"
	int cpt_ligne = tirage_question(tirageQuestions,false,caseTab,0); //Numero de la question (numero de la ligne dans le csv)
	SelectionDinosaure(nb_lignes,0);//Selectionne aleatoirement un dinosaure de la liste (qui n'a pas encore ete choisi)
	nb_lignes = rowCount(DinoJ1)-1;//Nombre de lignes du dinosaure (22)

	while(nbQuestions<=20){//Tant que le joueur n'a pas repondu a 20 questions
   			   
	    AuMoinsUneBonneReponse=false;	      
		
	    while(nb_lignes>0 && nbQuestions<=20){//Tant que le dinosaure n'est pas termine et tant qu'il reste encore des questions
           
		touche = '0';//Rénitialise la touche
		
		clearScreen();    	    
		decor(true);//true: affiche les options "quitter" et "menu"
		afficheDino(nb_lignes,AuMoinsUneBonneReponse,false,0);//Affiche la construction en cours      	    
		cursor(20,10);
		print(nbDino);//Affiche le nombre de dinosaures deja construits       
		affichage_question_reponses(cpt_ligne,0,false);//Affichage de la question et des reponses possibles
		bonus(nb_lignes,cptBonnesReponses,10,false,diff,false);//Affiche le bonus du joueur
		
		CharBonneReponse = char_bonne_reponse(cpt_ligne,0,false);//Identification de la bonne reponse
		tempsIni = tempsJoueur();
		RepJoueur = attenteReponseJoueur(false);//Attente de la réponse du joueur
		tempsFin = tempsJoueur();
		tempsFin = tempsFin-tempsIni;
		tempsFinal = tempsFinal+tempsFin;
		    
		if(RepJoueur=='q'){//Pour quitter
		    return;
		}
		    		    
		reponseCorrecte=bonne_reponse(RepJoueur,CharBonneReponse);//Analyse la reponse du joueur pour savoir si elle est correcte
		    
		if(reponseCorrecte){//Si la reponse est bonne
		    if(cptBonnesReponses<=6){
			cptBonnesReponses = cptBonnesReponses+1;//Incrementation compteur de bonnes reponses (afin de definir un bonus eventuel)
		    }
		    nb_lignes=bonus(nb_lignes,cptBonnesReponses,tempsFin,false,diff,reponseCorrecte);//Mise a jour du nombre de lignes du dinosaure qui vont etre affichees
		    AuMoinsUneBonneReponse = true;
		}else{//Si mauvaise reponse
		    cptBonnesReponses=0;//S'il le joueur se trompe alors le compteur de bonnes reponses redescend a 0 (plus de bonus)
		    if(diff=='2' && nb_lignes<rowCount(DinoJ1)-1){//Si mode difficile et que le joueur se trompe il perd une ligne
			nb_lignes = nb_lignes+1;
		    }
		}
		    
		clearScreen();
		affichage_bon_mauvais(reponseCorrecte,false,tempsFin,diff);//Affiche si le joueur a repondu correctement
		decor(true);
		bonus(nb_lignes,cptBonnesReponses,tempsFin,false,diff,reponseCorrecte);	   
		delay(2000);//Delai de 1 seconde
		cpt_ligne=tirage_question(tirageQuestions,false,caseTab,0);//Pour passer a la question suivante
		caseTab=caseTab+1; //avance de le tirage des questions
		nbQuestions=nbQuestions+1;
	    }
	    afficheDino(0,true,false,0);//Affiche le dinosaure en entier
	    delay(1000);    
	    nbDino=nbDino+1;//Incremente le compteur de dinosaures termines
	    cursor(20,10);
	    print(nbDino);//Affiche le nombre de dinosaures deja construits       
	    SelectionDinosaure(nb_lignes,0);//Selectionne aleatoirement un dinosaure de la liste (qui n'a pas encore ete choisi) (les "false" -> mode 2 joueurs desactive)
	    nb_lignes=rowCount(DinoJ1)-1;//Nombre de lignes du dinosaure
	}
	
    FinDuJeu(nbDino-1,0,tempsFinal,0);
    
    algorithm();
	
    }
    
 //=================================================================================================================    
 //=================================================================================================================
  
    void game_2j(){
        
	char CharBonneReponse; //Reponse a la question
	char RepJoueur; //Reponse du joueur
	int cptBonnesReponsesJ1=0; //Compte les reponses correctes du j1
	int cptBonnesReponsesJ2=0; //Compte les reponses correctes du j2
	boolean AuMoinsUneBonneReponseJ1=false;//Passe a true des la premiere bonne reponse pendant le dinosaure en construction, lors du changement de dinosaure la variable revient a false
	boolean AuMoinsUneBonneReponseJ2=false; //Passe a true des la premiere bonne reponse pendant le dinosaure en construction, lors du changement de dinosaure la variable revient a false 
	boolean reponseCorrecte; //Analyse si la reponse du joueur est correcte
	int nb_lignesJ1; //Concerne la construction des dinosaures du j1 (le dinosaure mesure toujours 22 lignes)
	int nb_lignesJ2; //Concerne la construction des dinosaures du j2 (le dinosaure mesure toujours 22 lignes)
	int nbDinoJ1=0; //Compte le nombre de dinosaures construits du j1
	int nbDinoJ2=0; //Compte le nombre de dinosaures construits du j2
	boolean tourJ2=false;
	int tempsIni; // variable pour initialiser le temps avant que le joueur répond 
	int tempsFin; // variable qui va permettre de calculer le temps que le joueur a mis pour répondre
	int tempsFinalJ1=0;
	int tempsFinalJ2=0;
	int[][] tirageQuestions=new int[2][20];
	initialisationTirage(tirageQuestions);	
	int caseTabJ1=0; //case pour le tirage des questions (jamais 2x la même)
	int caseTabJ2=0;
	int cpt_ligneJ1=tirage_question(tirageQuestions,false,caseTabJ1,caseTabJ2); //Concerne les questions (numero de la ligne)
	int cpt_ligneJ2=tirage_question(tirageQuestions,true,caseTabJ1,caseTabJ2); //Concerne les questions (numero de la ligne)
	int nbQuestions=1;
	
	SelectionDinosaure(0,0);
	nb_lignesJ1=rowCount(DinoJ1)-1;//Nombre de lignes du dinosaure du j1 
	nb_lignesJ2=rowCount(DinoJ2)-1;//Nombre de lignes du dinosaure du j2

	while(nbQuestions<=40){	    
		
	    touche='0';//Rénitialise la touche
	    clearScreen();    	    
	    decor(true);
	    afficheDino(nb_lignesJ1,AuMoinsUneBonneReponseJ1,AuMoinsUneBonneReponseJ2,nb_lignesJ2);    	    
	    cursor(20,5);
	    print(nbDinoJ1);//Affiche le nombre de dinosaures deja construits
	    cursor(20,80);
	    print(nbDinoJ2);
	    affichage_question_reponses(cpt_ligneJ1,cpt_ligneJ2,tourJ2);//Affichage de la question et des reponses possibles
	    bonus(nb_lignesJ1,cptBonnesReponsesJ1,10,tourJ2,'1',false);//Affiche le bonus du joueur
	    CharBonneReponse = char_bonne_reponse(cpt_ligneJ1,cpt_ligneJ2,tourJ2);//Identification de la bonne reponse
	    tempsIni=tempsJoueur(); //initialisation du temps pour le calcul
	    RepJoueur=attenteReponseJoueur(tourJ2);//Attente de la réponse du joueur
	    tempsFin=tempsJoueur(); 
	    tempsFin=tempsFin-tempsIni; //calcul du temps pour avoir répondu 
	    if(!tourJ2){
		tempsFinalJ1=tempsFinalJ1+tempsFin; //stock le temps final du joueur1
	    }else{
		tempsFinalJ2=tempsFinalJ2+tempsFin;  		//stock le temps final du joueur 2
	    }
		    
	    if(RepJoueur=='q'){//Pour quitter
		return;
	    }

	    reponseCorrecte=bonne_reponse(RepJoueur,CharBonneReponse);//Analyse la reponse du joueur pour savoir si elle est correcte
		  
	    if(!tourJ2){  
		if(reponseCorrecte){//Si la reponse est bonne
		    if(cptBonnesReponsesJ1<=6){
			cptBonnesReponsesJ1=cptBonnesReponsesJ1+1;//Incrementation compteur de bonnes reponses (afin de un bonus eventuel)
		    }
		    nb_lignesJ1=bonus(nb_lignesJ1,cptBonnesReponsesJ1,tempsFin,tourJ2,'1',reponseCorrecte);//Mise a jour du nombre de lignes du dinosaure qui vont etre affichees
		    if(nb_lignesJ2<rowCount(DinoJ2)-1){
			nb_lignesJ2=nb_lignesJ2+1;
		    }
		    AuMoinsUneBonneReponseJ1=true;
		}else{
		    cptBonnesReponsesJ1=0;//S'il le joueur se trompe alors le compteur de bonnes reponses redescend a 0 (plus de bonus)
		}
	    }else{
		if(reponseCorrecte){//Si la reponse est bonne
		    if(cptBonnesReponsesJ2<=6){
			cptBonnesReponsesJ2=cptBonnesReponsesJ2+1;//Incrementation compteur de bonnes reponses (afin de un bonus eventuel)
		    }
		    nb_lignesJ2=bonus(nb_lignesJ2,cptBonnesReponsesJ2,tempsFin,tourJ2,'1',reponseCorrecte);//Mise a jour du nombre de lignes du dinosaure qui vont etre affichees
		    if(nb_lignesJ1<rowCount(DinoJ1)-1){
			nb_lignesJ1=nb_lignesJ1+1;
		    }
		    AuMoinsUneBonneReponseJ2=true;
		}else{
		    cptBonnesReponsesJ2=0;//S'il le joueur se trompe alors le compteur de bonnes reponses redescend a 0 (plus de bonus)
		}
	    }
	    
		    
	    clearScreen();
	    affichage_bon_mauvais(reponseCorrecte,tourJ2,tempsFin,'1');//Affiche si le joueur a repondu correctement
	    decor(true);  	    
	    cursor(20,5);
	    print(nbDinoJ1);//Affiche le nombre de dinosaures deja construits
	    cursor(20,80);
	    print(nbDinoJ2); //faire une fonction printNbDino();
	    bonus(nb_lignesJ1,cptBonnesReponsesJ1,tempsFin,tourJ2,'1',reponseCorrecte);//Affiche le bonus du joueur
	    delay(1000);//Delai de 1 seconde
	    if(!tourJ2){
		cpt_ligneJ2=tirage_question(tirageQuestions,tourJ2,caseTabJ1,caseTabJ2);//Pour passer a la question suivante
		caseTabJ1=caseTabJ1+1;
	    }else{
		cpt_ligneJ1=tirage_question(tirageQuestions,tourJ2,caseTabJ1,caseTabJ2);//Pour passer a la question suivante
		caseTabJ2=caseTabJ2+1;
	    }
	
	    nbQuestions=nbQuestions+1;	
	    RepJoueur='0';
	    CharBonneReponse='0';
	    if(nb_lignesJ1<=0){
		afficheDino(0,true,AuMoinsUneBonneReponseJ2,nb_lignesJ2);//Affiche le dinosaure en entier
		delay(1000);   
		nbDinoJ1=nbDinoJ1+1;//Incremente le compteur de dinosaures termines
		SelectionDinosaure(0,nb_lignesJ2);		    	   
		nb_lignesJ1=rowCount(DinoJ1)-1;//Nombre de lignes du dinosaure
		AuMoinsUneBonneReponseJ1=false;	   
	    }
	    if(nb_lignesJ2<=0){
		afficheDino(nb_lignesJ1,AuMoinsUneBonneReponseJ1,true,0);//Affiche le dinosaure en entier
		delay(1000);  
		nbDinoJ2=nbDinoJ2+1;//Incremente le compteur de dinosaures termines 
		SelectionDinosaure(nb_lignesJ1,0);	   
		nb_lignesJ2=rowCount(DinoJ2)-1;//Nombre de lignes du dinosaure
		AuMoinsUneBonneReponseJ2=false;	   
	    }
	    
	    tourJ2=!tourJ2;

	} 
	
	FinDuJeu(nbDinoJ1-1,nbDinoJ2-1,tempsFinalJ1,tempsFinalJ2);
	
	algorithm(); //retour au menu
	
    }
    
    //=================================================================================================================
    //=================================================================================================================  
    
    void FinDuJeu(int nbDinoJ1,int nbDinoJ2,int tempsFinalJ1,int tempsFinalJ2){
        
    if(!mode2joueurs){
        
	cursor(8,33);
	println("Bravo, tu as construit "+nbDinoJ1+" dinosaure(s) en "+tempsFinalJ1+" secondes !");
	delay(2000);
	clearScreen();
	cursor(30,50);
	println("Nom et prénom (\"/\" pour effacer , \" * \" pour valider) : ");
	scores_joueurs(nbDinoJ1);
	
    }else{
        
	clearScreen();
	cursor(30,50);
        
    if(nbDinoJ1>nbDinoJ2){
	    print("Le joueur 1 a gagné");
	}else if(nbDinoJ1<nbDinoJ2){
	    print("Le joueur 2 a gagné");
	}else{
	    print("Egalité !");
	}
	
	delay(2000);
	clearScreen();
	
    cursor(30,50);
	println("Nom et prénom du Joueur 1 (\"/\" pour effacer , \" * \" pour valider) : ");
	scores_joueurs(nbDinoJ1);
	clearScreen();
	cursor(30,50);
	println("Nom et prénom du Joueur 2 (\"/\" pour effacer , \" * \" pour valider) : ");
	scores_joueurs(nbDinoJ2);
	
    }
	
    }
    
    //=================================================================================================================
    //=================================================================================================================  
   
    char attenteReponseJoueur(boolean tourJ2){ //verifie la saisie de l'utilisateur 
    
	boolean toucheCorrecte=false;
	char ReponseJoueur;
	
	do{
	    delay(100);
	    ReponseJoueur=touche;
	    if((!tourJ2 && (touche=='w' || touche=='x' || touche=='c')) || ((tourJ2 && (touche>='1' && touche<='3')))){
		toucheCorrecte=true;	  		
	    }else if(touche=='q'){//Quitter
		clearScreen();
		cursor(0,0);
		return 'q';
	    }
	}while(!toucheCorrecte); // attend une touche correct 
	
	toucheCorrecte=false;
	return ReponseJoueur;
	
    }
    
 //=================================================================================================================
 //=================================================================================================================
 
    void initialisationTirage(int[][] tirage){ //permet d'éviter un numéro à une case null
    
	for(int i=0;i<length(tirage,1);i++){
	    for(int j=0;j<length(tirage,2);j++){
		tirage[i][j]=-1;
	    }
	}       
	
    }
    
   //=================================================================================================================
   //=================================================================================================================   
   
    int tirage_question(int[][] tirage, boolean tourJ2, int caseTabJ1, int caseTabJ2){
        
	int cpt_ligne;
	int i=0;
	CSVFile questions;
	int ligne;
	int caseTab;
	
	if(!tourJ2){
	    questions=QuestionsJ1;
	    ligne=0;
	    caseTab=caseTabJ1;
	}else{
	    questions=QuestionsJ2;
	    ligne=1;
	    caseTab=caseTabJ2;
	}
	
	cpt_ligne=(int)(random()*(rowCount(questions)-1));
	
	while(i<=caseTab){
	    if(cpt_ligne==tirage[ligne][i]){
		cpt_ligne=(int)(random()*(rowCount(questions)-1));
		i=0;
	    }
	    i=i+1;
	}
	
	tirage[ligne][caseTab]=cpt_ligne;
	return cpt_ligne;
	
    }

 //=================================================================================================================
 //=================================================================================================================	 
 
    /*Affiche le dinosaure en construction*/
    void afficheDino(int nbJ1,boolean AuMoinsUneBonneReponseJ1,boolean AuMoinsUneBonneReponseJ2,int nbJ2){
        
	if(!mode2joueurs && AuMoinsUneBonneReponseJ1){//Pour corriger un bug qui affichait la premiere ligne meme si on repondait faux
	
	    int ligne_dino=nbJ1+1;
	    
	    if(nbJ1==0){
		ligne_dino=nbJ1;
	    }
	    
	    while(ligne_dino<rowCount(DinoJ1)){
		cursor(ligne_dino+25,45);
		print(getCell(DinoJ1,ligne_dino,0));
		ligne_dino=ligne_dino+1;			  	
	    }
	}
	
	if(mode2joueurs){
	    
	    int ligne_dinoJ1=nbJ1+1;
	    int ligne_dinoJ2=nbJ2+1;
	    
	    if(nbJ1==0){
		ligne_dinoJ1=nbJ1;
	    }
	    
	    if(nbJ2==0){
		ligne_dinoJ2=nbJ2;
	    }
	    
	    if(AuMoinsUneBonneReponseJ1){
		while(ligne_dinoJ1<rowCount(DinoJ1)){
		    cursor(ligne_dinoJ1+25,1);
		    print(getCell(DinoJ1,ligne_dinoJ1,0));
		    ligne_dinoJ1=ligne_dinoJ1+1;			  	
		}
	    }
	    
	    if(AuMoinsUneBonneReponseJ2){
		while(ligne_dinoJ2<rowCount(DinoJ2)){
		    cursor(ligne_dinoJ2+25,90);
		    print(getCell(DinoJ2,ligne_dinoJ2,0));
		    ligne_dinoJ2=ligne_dinoJ2+1;			  	
		}
	    }
	    
	}
    }

 //=================================================================================================================
 //=================================================================================================================
 
    /*Charge un fichier dinosaure CSV de facon aleatoire*/  
    void SelectionDinosaure(int nb_lignesJ1,int nb_lignesJ2){
        
	int numFichier;
	
	if(nb_lignesJ1<=0){//Tant que le dinosaure n'est pas fini
	    do{
		numFichier = (int)(random()*16);
	    }while(equals(fichiersDinoJ1[numFichier],"."));
	    
	    DinoJ1 = loadCSV(fichiersDinoJ1[numFichier],'$');
	    fichiersDinoJ1[numFichier]=".";//Pour ne plus tirer une deuxieme fois le dinosaure
	}
	       
	if(mode2joueurs && nb_lignesJ2<=0){
	    do{
		numFichier = (int)(random()*16);
	    }while(equals(fichiersDinoJ2[numFichier],"."));
	    
	    DinoJ2 = loadCSV(fichiersDinoJ2[numFichier],'$');
	    fichiersDinoJ2[numFichier]=".";	    
	}
	
    }
    
 //=================================================================================================================
 //=================================================================================================================
    
    //*****************Fonction qui calcul le bonus**********************************

    int bonus(int nbLignes,int cptBonnesReponses, long temps,boolean tourJ2,char diff,boolean resultat){ 
        
	int placement;
	
	if(!mode2joueurs){ //si mode 1 joueur
	    placement=10;
	}else{ // mode 2 joueurs
	    if(!tourJ2){ //tour j1
		placement=5;
	    }else{ // tour j2
		placement=80;
	    }
	}
	cursor(22,placement);
	if(diff=='1' || (diff=='2' && temps<7)){ //si mode facile OU (mode difficile ET temps de reponses < 7 secondes)
	    if(cptBonnesReponses<=2){ 
		nbLignes=nbLignes-1;
	    }else if(cptBonnesReponses<=4){
		nbLignes=nbLignes-2;
		print("Construction 2 fois plus rapide");
	    }else if(cptBonnesReponses<=6){
		nbLignes=nbLignes-4;
		print("Construction 4 fois plus rapide");
	    }else if(cptBonnesReponses<=8){
		nbLignes=nbLignes-6;
		print("Construction 6 fois plus rapide");
	    }
	}
	if(resultat && temps<=3 && diff=='1'){ 
	    nbLignes=nbLignes-2;
	    cursor(24,placement);	    
	    print("Tu as été rapide ! +2 Lignes !");
	}
	return nbLignes;//Pour le nombre de lignes a afficher (du dinosaure)
    }
    
 //=================================================================================================================
 //=================================================================================================================
 
    void scores_joueurs(int nombre_dino){
        
	String [][] scores_j = new String [(rowCount(ScoresLoad))+1][columnCount(ScoresLoad)];
	String scoreString;  //variable pour convertir le Score 'int' en 'String'
	int score;
	boolean finiScores=false;
	String prenom="";
	score=(nombre_dino*100);
	scoreString=""+score; //permet de convertir le score de depart  'int' en 'String'
	touche='=';
	
	for(int i=0;i<length(scores_j,1)-1;i++){ //stock dans score_j chaque valeur du fichier csv Scores
	    for(int k=0;k<2;k++){
		scores_j[i][k]=getCell(ScoresLoad,i,k);
	    }
	}
	
	do{
	    if(length(prenom)>1 && charAt(prenom,length(prenom)-1)=='*'){ 
		finiScores=true; //sorti de la boucle while quand l'utilisateur appui sur la touche '*' 
	    }else if(touche=='*' && length(prenom)<2){ //verifie si l'utilisateur à rentrer plus d'un caractère
		cursor(31,50);
		print("Tu n'as pas rentré assez de caractères");
		delay(1000);
		clearLine();
		cursor(31,50);
		touche='=';
	    }else if(touche=='/'){
		cursor(31,50);
		clearLine();
		prenom="";
	    }else if(touche!='='){
		prenom=prenom+touche; //affectation caractère par caractère dans prénom
		touche='=';
	    }
	    delay(50);
	}while(!finiScores);
	
	scores_j[length(scores_j,1)-1][0]=substring(prenom,0,length(prenom)-1);
	scores_j[length(scores_j,1)-1][1]=scoreString;
	
	triScores(scores_j); //tri le score avant de sauvegarder dans le CSV
	saveCSV(scores_j,"../ressources/score-joueurs.csv",';');
	saveCSV(scores_j,"score-joueurs.csv",';');
	ScoresLoad = loadCSV("score-joueurs.csv",';');
    }

    //====================================================================================================================
    //===================================================================================================================
    
    void triScores(String[][]tab){
        
	int cpt=0;
	boolean fini=false;
	String transfert="";//variable temporaire pour le changer de place le score dans le tableau
	
	while(!fini){
	    cpt=0; //une foi la boucle for fini cpt=0 -->  if(cpt=0) 
	    for(int i=1;i<length(tab,1)-1;i++){
		if(stringToInt(tab[i][1])<stringToInt(tab[i+1][1])){
		    transfert=tab[i][1];
		    tab[i][1]=tab[i+1][1];
		    tab[i+1][1]=transfert;
		    
		    transfert=tab[i][0];
		    tab[i][0]=tab[i+1][0];
		    tab[i+1][0]=transfert;
		    cpt=cpt+1;
		}
	    }
	    if(cpt==0){
		fini=true; //sortie de la boucle while 
	    }
	}
    }
    
    //====================================================================================================================
    //====================================================================================================================

    void affichageScores() {
        
	boolean scoresFini=false;
	touche='0';
	String[][]tab=new String[rowCount(ScoresLoad)+1][columnCount(ScoresLoad)];
	
	for(int i=0;i<length(tab,1)-1;i++){ //charge les valeurs du csv dans tab
	    for(int j=0;j<2;j++){
		tab[i][j]=getCell(ScoresLoad,i,j);
	    }
	}
	clearScreen();
	
	for (int i=0; i<length(tab,1) && i<rowCount(ScoresLoad); i++) {
	    cursor(i*2+6, 10);
	    if (tab[i][1]!=null) {
		print("#");
		print(i+1);
	    } else {
		print("  ");
	    }
	    for (int k=0; k<length(tab,2); k++){
		if (k == 1) {
		    cursor(i*2+6,40);
		}
		if(tab[i][k]!=null){
		    print("\t"+tab[i][k]);
		}
	    }
	}
	sortir();
    		    
    }


}

