

import javax.swing.JOptionPane;

import tps.Plateau;

/**
 * <b>Jeu est la classe qui permet de lancer deux modes différents, un avec des
 * valeurs par défaut et un avec des valeurs personnalisables</b>
 * <p>
 * Elle est caractérisée par :
 * <ul>
 * <li>Une ile</li>
 * </ul>
 * </p>
 * <p>
 * Elle créer l'ile, la remplit et l'affiche.
 * </p>
 * 
 * @see Ile
 */
public class Jeu {

	/**Ile du jeu en cours.*/
	private Ile ile;

	/**Equipe 1*/
	private Equipe equipe1 = new Equipe();
	
	/**Equipe 2*/
	private Equipe equipe2 = new Equipe();
	
	/**Taille des équipes*/
	private int tailleTeam;
	
	/**Plateau du jeu en cours.*/
	private Plateau plateau;
	
	/**
	 * Renvoie une Equipe en fonction du paramètre.
	 * 
	 * @param equipe
	 * 				Numéro de l'équipe
	 * 
	 * @return Retourne une Equipe.
	 */
	public Equipe getEquipe(int equipe){
		if(equipe==1){
			return equipe1;
		}else{
			return equipe2;
		}
	}
	
	/**
	 * Renvoie la taille des équipes.
	 * 
	 * @return Retourne un int.
	 */
	public int getTailleTeam(){
		return tailleTeam;				
	}
	
	/**
	 * Renvoie le plateau.
	 * 
	 * @return Retourne un Plateau.
	 */
	public Plateau getPlateau(){
		return plateau;				
	}
	
	/**
	 * Renvoie l'ile.
	 * 
	 * @return Retourne une Ile.
	 */
	public Ile getIle(){
		return ile;				
	}
	
	/**
	 * Constructeur vide de Jeu.
	 * 
	 * @see Ile#Ile()
	 * @see Ile#remplirRochers()
	 * @see Ile#remplirNavire(int)
	 * @see Ile#remplirCleCoffre()
	 * @see Ile#getJeu()
	 * @see Jeu#suiteJeu()
	 */
	public Jeu() {

		do {
			ile = new Ile();
			ile.remplirRochers();
			ile.remplirNavire(1);
			ile.remplirNavire(2);
			ile.setJeu();
		} while (!ile.toutEstAccessible((ile.getNav(1)).getPos()) || !ile.toutEstAccessible((ile.getNav(2)).getPos()));

		ile.remplirCleCoffre();

		suiteJeu();
	}

	/**
	 * Constructeur de Jeu.
	 * 
	 * @param taille
	 *            Taille de l'ile voulue
	 * 
	 * @param pourcentageRochers
	 *            Pourcentage de rochers voulu
	 * 
	 * @see Ile#Ile(int, int)
	 * @see Ile#remplirRochers()
	 * @see Ile#remplirNavire(int)
	 * @see Ile#remplirCleCoffre()
	 * @see Ile#getJeu()
	 * @see Jeu#suiteJeu()
	 */
	public Jeu(int taille, int pourcentageRochers) {
		do {
			ile = new Ile(taille, pourcentageRochers);
			ile.remplirRochers();
			ile.remplirNavire(1);
			ile.remplirNavire(2);
			ile.setJeu();
		} while (!ile.toutEstAccessible((ile.getNav(1)).getPos()) || !ile.toutEstAccessible((ile.getNav(2)).getPos()));

		ile.remplirCleCoffre();
		suiteJeu();
	}

	
	/**
	 * Méthode s'éxécutant à la fin des deux constructeurs. Lance la suite du
	 * jeu.
	 */
	public void suiteJeu() {

		equipe1 = new Equipe("Equipe 1", 1, ile);
		equipe2 = new Equipe("Equipe 2", 2, ile);
		String choix2 = "";
		boolean tailleEquipe = false;

		// Affichage pour ajouter les persos
		choix2 = JOptionPane.showInputDialog(null,
				"Veuillez rentrer la taille de l'équipe (Dont un explorateur obligatoirement inscrit dès le départ).");
		
		while(!tailleEquipe){
			if(choix2 == null || choix2.equals("")){
				JOptionPane.showMessageDialog(null, "Veuillez rentrer un argument s'il vous plait");
				choix2 = JOptionPane.showInputDialog(null,
						"Veuillez rentrer la taille de l'équipe (Dont un explorateur obligatoirement inscrit dès le départ).");
			}
			else if(!choix2.matches("[1-5]*")){
				JOptionPane.showMessageDialog(null, "Veuillez rentrer un argument valide s'il vous plait");
				choix2 = JOptionPane.showInputDialog(null,
						"Veuillez rentrer la taille de l'équipe (Dont un explorateur obligatoirement inscrit dès le départ).");	
			}
			else {
				tailleTeam = Integer.parseInt(choix2);
				tailleEquipe = true;
			}
		}

		equipe1.ajouterPerso(new Explorateur(ile.getNav(1).getPos()[0],ile.getNav(1).getPos()[1], equipe1), tailleTeam);
		equipe2.ajouterPerso(new Explorateur(ile.getNav(2).getPos()[0],ile.getNav(2).getPos()[1], equipe2), tailleTeam);

		// Ajout des persos dans leur team respective
		ajoutPerso();

		plateau = new Plateau(ile.getImages(), ile.getTaille(), true);
		ile.setJeu();
		plateau.setJeu(ile.getJeu());
		deroulementJeu();
	}
	
	/**
	 * Permet la sélection des personnages dans les équipes
	 */
	private void ajoutPerso() {
		int choix=0;
		String choix2 = "";
		boolean boo1 = false;
		//Boucle pour ajouter les persos
		for (int i = 0; i < (tailleTeam * 2 )- 2; i++) {

			boo1 = false;
			//If de verification des deux teams 
			if(i < tailleTeam -1){
				choix2 = JOptionPane.showInputDialog(null, "Quel personnage voulez vous ajouter ("
						+ equipe1.getNom() + ")?\n1 : Explorateur\n2 : Voleur\n3 : Guerrier\n4 : Piégeur");
			}else{
				choix2 = JOptionPane.showInputDialog(null, "Quel personnage voulez vous ajouter ("
						+ equipe2.getNom() + ")?\n1 : Explorateur\n2 : Voleur\n3 : Guerrier\n4 : Piégeur");
			}
			//Boucle de verification de la saisie
			while(!boo1){
				if(choix2 == null || choix2.equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez rentrer un argument s'il vous plait");
					choix2 = JOptionPane.showInputDialog(null, "Quel personnage voulez vous ajouter ("
							+ equipe1.getNom() + ")?\n1 : Explorateur\n2 : Voleur\n3 : Guerrier\n4 : Piégeur");
				}else if(!choix2.matches("[1-4]")){
					JOptionPane.showMessageDialog(null, "Veuillez rentrer un argument valide s'il vous plait");
					choix2 = JOptionPane.showInputDialog(null, "Quel personnage voulez vous ajouter ("
							+ equipe1.getNom() + ")?\n1 : Explorateur\n2 : Voleur\n3 : Guerrier\n4 : Piégeur");
				}else {
					boo1 = true;
					choix = Integer.parseInt(choix2);
				}
			}
			if (i < tailleTeam - 1) {
				if (choix == 1) {
					equipe1.ajouterPerso(new Explorateur(ile.getNav(1).getPos()[0],ile.getNav(1).getPos()[1], equipe1), tailleTeam);
				} else if (choix == 2) {
					equipe1.ajouterPerso(new Voleur(ile.getNav(1).getPos()[0],ile.getNav(1).getPos()[1], equipe1), tailleTeam);
				} else if (choix == 3) {
					equipe1.ajouterPerso(new Guerrier(ile.getNav(1).getPos()[0],ile.getNav(1).getPos()[1], equipe1), tailleTeam);
				} else if (choix == 4) {
					equipe1.ajouterPerso(new Piegeur(ile.getNav(1).getPos()[0],ile.getNav(1).getPos()[1], equipe1), tailleTeam);
				}

			} else {// Ajout dans la team 2
				if (choix == 1) {
					equipe2.ajouterPerso(new Explorateur(ile.getNav(2).getPos()[0],ile.getNav(2).getPos()[1], equipe2), tailleTeam);
				} else if (choix == 2) {
					equipe2.ajouterPerso(new Voleur(ile.getNav(2).getPos()[0],ile.getNav(2).getPos()[1], equipe2), tailleTeam);
				} else if (choix == 3) {
					equipe2.ajouterPerso(new Guerrier(ile.getNav(2).getPos()[0],ile.getNav(2).getPos()[1], equipe2), tailleTeam);
				} else if (choix == 4) {
					equipe2.ajouterPerso(new Piegeur(ile.getNav(2).getPos()[0],ile.getNav(2).getPos()[1], equipe2), tailleTeam);
				}
			}
			
		}

	}
	
	/**
	 * Contient tout le déroulement d'une partie
	 */
	public void deroulementJeu() {
		
		int tourEquipe = 1;
		
		Personnage p;
		
		int i = 0;
		
		do {
			
			while (i < equipe1.getTeam().size() && tourEquipe == 1 && !equipe1.teamGoToWin(ile) && !equipe1.teamGoToLose(ile) && !equipe2.teamGoToLose(ile)  && !equipe2.teamGoToWin(ile) ) {

				vueEquipe(1);
				p = equipe1.getTeam().get(i);
				
				if(!p.getPieger()){
					
				plateau.clearConsole();
				plateau.println(p.toString());
				
				int[][] caseSelec = ile.getJeu();
				
				if (!p.estSurBase(ile)) {
					if (p instanceof Explorateur) {
						caseSelec[p.getY()][p.getX()] = 18;
					} else if (p instanceof Voleur) {
						caseSelec[p.getY()][p.getX()] = 20;
					} else if (p instanceof Guerrier) {
						caseSelec[p.getY()][p.getX()] = 22;
					} else if (p instanceof Piegeur) {
						caseSelec[p.getY()][p.getX()] = 24;
					}

				} else {
					caseSelec[p.getY()][p.getX()] = 26;
				}
				ile.modifierJeu(caseSelec);

				ile = p.action(ile, plateau,false);
				
				if(!p.Vivant()){
					plateau.println("Malheureusement votre "+ p.getNom() +" est mort...");
					ile.removeParcelle(p.getX(), p.getY());
					
					if (ile.getParcelle(p.getX(), p.getY()) instanceof Terre) {
						if (p.getPossedeCle()) {
							((Terre) ile.getParcelle(p.getX(), p.getY())).setCle(true);
						} else if(p.getPossedeTresor()){
							((Terre) ile.getParcelle(p.getX(), p.getY())).setTresor(true);
						}
					}
					
					p.getEquipe().getTeam().remove(p);
					i = i - 1;
					try {Thread.sleep(1200) ;}  catch (InterruptedException e) {/*gestion de l'erreur*/}
					ile.setJeu();
					plateau.setJeu(ile.getJeu());
					plateau.affichage();
				}
				}else{
					p.miseAJourPieger(ile);
				}
				
				ile.setJeu();
				vueEquipe(1);
				plateau.setJeu(ile.getJeu());
				plateau.affichage();
				i = i + 1;
				
				plateau.clearConsole();
				plateau.println(p.toString());

				try {Thread.sleep(400) ;}  catch (InterruptedException e) {/*gestion de l'erreur*/}
			}
			tourEquipe = 2;
			i = 0;

			try {Thread.sleep(400) ;}  catch (InterruptedException e) {/*gestion de l'erreur*/}
			

			plateau.clearConsole();
			while (i < equipe2.getTeam().size() && tourEquipe == 2 && !equipe2.teamGoToWin(ile) && !equipe2.teamGoToLose(ile) && !equipe1.teamGoToLose(ile)  && !equipe1.teamGoToWin(ile) ) {

				vueEquipe(2);
				p = equipe2.getTeam().get(i);
				
				if(!p.getPieger()){
					
				plateau.clearConsole();
				plateau.println(p.toString());
				
				int[][] caseSelec = ile.getJeu();
				
				if (!p.estSurBase(ile)) {
					if(p instanceof Explorateur){
						caseSelec[p.getY()][p.getX()] = 19;
					}else if(p instanceof Voleur){
						caseSelec[p.getY()][p.getX()] = 21;
					}else if(p instanceof Guerrier){
						caseSelec[p.getY()][p.getX()] = 23;
					}else if(p instanceof Piegeur){
						caseSelec[p.getY()][p.getX()] = 25;
					}
				}else{
					caseSelec[p.getY()][p.getX()] = 27;
				}
				
				ile.modifierJeu(caseSelec);
				
				ile = p.action(ile, plateau,false);
				
				if(!p.Vivant()){
					plateau.println("Malheureusement votre "+ p.getNom() +" est mort...");
					ile.removeParcelle(p.getX(), p.getY());
					if (ile.getParcelle(p.getX(), p.getY()) instanceof Terre) {
						if (p.getPossedeCle()) {
							((Terre) ile.getParcelle(p.getX(), p.getY())).setCle(true);
						} else if(p.getPossedeTresor()){
							((Terre) ile.getParcelle(p.getX(), p.getY())).setTresor(true);
						}
					}
					p.getEquipe().getTeam().remove(p);
					i = i - 1;
					try {Thread.sleep(1200) ;}  catch (InterruptedException e) {/*gestion de l'erreur*/}
					ile.setJeu();
					plateau.setJeu(ile.getJeu());
					plateau.affichage();
				}
				}else{
					p.miseAJourPieger(ile);
				}
				ile.setJeu();
				vueEquipe(2);
				plateau.setJeu(ile.getJeu());
				plateau.affichage();
				i = i + 1;
				
				plateau.clearConsole();
				plateau.println(p.toString());

				try {Thread.sleep(400) ;}  catch (InterruptedException e) {/*gestion de l'erreur*/}
			}
			tourEquipe=1;
			i=0;
			
			try {Thread.sleep(400) ;}  catch (InterruptedException e) {/*gestion de l'erreur*/}
			
		} while (!equipe1.teamGoToWin(ile) && !equipe2.teamGoToWin(ile) && !equipe1.teamGoToLose(ile) && !equipe2.teamGoToLose(ile));
		
		plateau.close();
	}
	
	
	/**
	 * Gère la vue de chacune des équipes
	 */
	public void vueEquipe(int tourEquipe){
		if (tourEquipe == 1) {
			equipe1.getVue().updateVue();
		} else {
			equipe2.getVue().updateVue();
		}

		boolean[][] b1 = equipe1.getVue().getBrouillard();
		boolean[][] b2 = equipe2.getVue().getBrouillard();

		ile.setJeu();
		int[][] vueFinale = ile.getJeu();

		for (int i = 0; i < ile.getTaille(); i++) {
			for (int j = 0; j < ile.getTaille(); j++) {
				if (tourEquipe == 1) {
					if (!b1[i][j]) {
						vueFinale[j][i] = 17;
					}
					if (i != 0 && i != ile.getTaille() - 1 && j != 0 && j != ile.getTaille() - 1){
						if (ile.getIle()[i][j].getParcelle() instanceof Terre && ((Terre) ile.getIle()[i][j].getParcelle()).getPiege() && ((Terre) ile.getIle()[i][j].getParcelle()).getEquipePiege()==tourEquipe) {
							vueFinale[j][i] = 30;
						}else if (ile.getIle()[i][j].getParcelle() instanceof Rocher && ((Rocher) ile.getIle()[i][j].getParcelle()).getDecouverteCoffre(tourEquipe)) {
							if ((((Rocher) ile.getIle()[i][j].getParcelle()).getCoffre()).getTresor()) {
								vueFinale[j][i] = 6;
							} else {
								vueFinale[j][i] = 5;
							}
						} 
					}
				} else {
					if (!b2[i][j]) {
						vueFinale[j][i] = 17;
					}
					if (i != 0 && i != ile.getTaille() - 1 && j != 0 && j != ile.getTaille() - 1){
						if (ile.getIle()[i][j].getParcelle() instanceof Terre && ((Terre) ile.getIle()[i][j].getParcelle()).getPiege() && ((Terre) ile.getIle()[i][j].getParcelle()).getEquipePiege()==tourEquipe) {
							vueFinale[j][i] = 30;
						}else if (ile.getIle()[i][j].getParcelle() instanceof Rocher && ((Rocher) ile.getIle()[i][j].getParcelle()).getDecouverteCoffre(tourEquipe)) {
							if ((((Rocher) ile.getIle()[i][j].getParcelle()).getCoffre()).getTresor()) {
								vueFinale[j][i] = 6;
							} else {
								vueFinale[j][i] = 5;
							}
						} 
					}
				}
			}
		}

		ile.modifierJeu(vueFinale);
		plateau.setJeu(ile.getJeu());
		plateau.affichage();
	}
	

}