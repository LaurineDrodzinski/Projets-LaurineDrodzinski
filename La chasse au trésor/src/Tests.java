import javax.swing.JOptionPane;

import tps.Plateau;

public class Tests {

	private Equipe equipe1;
	private Equipe equipe2;
	private Ile ile;
	private Navire navire1;
	private Navire navire2;
	private Plateau plateau;

	public Tests() {
		boolean boo = false;
		String persoTestString;
		ile = new Ile(10, 0);
		navire1=new Navire(1, 1, 1);
		navire2=new Navire(2,8, 8);
		equipe1=new Equipe("Equipe1", 1, ile);
		equipe2=new Equipe("Equipe2", 2, ile);
		plateau = new Plateau(ile.getImages(), ile.getTaille(), true);

		ile.setNav(navire1,1);
		ile.setNav(navire2,2);

		ile.setParcelle(navire1, 1, 1);
		ile.setParcelle(navire2, 8, 8);
		ile.setParcelle(new Rocher(true,false), 3, 1);
		ile.setParcelle(new Rocher(false,true), 4, 1);
		ile.setParcelle(new Rocher(false,false), 5, 1);

		ile.setPerso(1,3,new Explorateur(1, 3, equipe1));
		ile.setPerso(1,4,new Voleur(1, 4, equipe1));
		ile.setPerso(1,5,new Guerrier(1, 5, equipe1));
		ile.setPerso(1,6,new Piegeur(1, 6, equipe1));

		ile.getPerso(1,3).setPossedeCle(true);
		ile.getPerso(1,4).setPossedeTresor(true);

		ile.setPerso(8,3,new Explorateur(8, 3, equipe2));
		ile.setPerso(8,4,new Voleur(8, 4, equipe2));
		ile.setPerso(8,5,new Guerrier(8, 5, equipe2));
		ile.setPerso(8,6,new Piegeur(8, 6, equipe2));

		ile.getPerso(8,3).setPossedeCle(true);
		ile.getPerso(8,4).setPossedeTresor(true);

		ile.setJeu();
		plateau.setJeu(ile.getJeu());

		persoTestString = JOptionPane.showInputDialog(null,
				"Quel personnage souhaitez-vous tester ?\n1 : Explorateur\n2 : Voleur\n3 : Guerrier\n4 : Piegeur");

		while(!boo){

			if(persoTestString == null){
				JOptionPane.showMessageDialog(null, "Veuillez rentrer un argument s'il vous plait");
				persoTestString = JOptionPane.showInputDialog(null,
						"Quel personnage souhaitez-vous tester ?\n1 : Explorateur\n2 : Voleur\n3 : Guerrier\n4 : Piegeur");
			}
			else if(persoTestString.equals("") || !persoTestString.matches("[0-4]")){
				JOptionPane.showMessageDialog(null, "Veuillez rentrer un argument valide s'il vous plait");
				persoTestString = JOptionPane.showInputDialog(null,
						"Quel personnage souhaitez-vous tester ?\n1 : Explorateur\n2 : Voleur\n3 : Guerrier\n4 : Piegeur");
			}
			else if (persoTestString.equals("1")) {
				equipe1.ajouterPerso(new Explorateur(ile.getNav(1).getPos()[0], ile.getNav(1).getPos()[1], equipe1),5);
				boo = true;
			}else if (persoTestString.equals("2")) {
				equipe1.ajouterPerso(new Voleur(ile.getNav(1).getPos()[0], ile.getNav(1).getPos()[1], equipe1), 5);
				boo = true;
			}else if (persoTestString.equals("3")) {
				equipe1.ajouterPerso(new Guerrier(ile.getNav(1).getPos()[0], ile.getNav(1).getPos()[1], equipe1),5);
				boo = true;
			}else if (persoTestString.equals("4")) {
				equipe1.ajouterPerso(new Piegeur(ile.getNav(1).getPos()[0], ile.getNav(1).getPos()[1], equipe1),5);
				boo = true;
			}

		}

		equipe1.ajouterPerso(ile.getPerso(1, 3), 5);
		equipe1.ajouterPerso(ile.getPerso(1,4), 5);
		equipe1.ajouterPerso(ile.getPerso(1,5), 5);
		equipe1.ajouterPerso(ile.getPerso(1,6), 5);


		equipe2.ajouterPerso(ile.getPerso(8, 3), 4);
		equipe2.ajouterPerso(ile.getPerso(8, 4), 4);
		equipe2.ajouterPerso(ile.getPerso(8, 5), 4);
		equipe2.ajouterPerso(ile.getPerso(8, 6), 4);

		Personnage p = equipe1.getTeam().get(0);

		do {
			vueEquipe(1);
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

			if (!p.Vivant()) {
				plateau.println("Malheureusement votre " + p.getNom() + " est mort...");
				ile.removeParcelle(p.getX(), p.getY());

				if (ile.getParcelle(p.getX(), p.getY()) instanceof Terre) {
					if (p.getPossedeCle()) {
						((Terre) ile.getParcelle(p.getX(), p.getY())).setCle(true);
					} else if (p.getPossedeTresor()) {
						((Terre) ile.getParcelle(p.getX(), p.getY())).setTresor(true);
					}
				}

				p.getEquipe().getTeam().remove(p);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// gestion de l'erreur
				}
				ile.setJeu();
				plateau.setJeu(ile.getJeu());
				plateau.affichage();
			}

			ile.setJeu();
			plateau.setJeu(ile.getJeu());
			plateau.affichage();

			plateau.clearConsole();
			plateau.println(p.toString());
		} while (p.Vivant() && !equipe1.teamGoToWin(ile) && !equipe1.teamGoToLose(ile));
		plateau.close();
	}

	public void vueEquipe(int tourEquipe){
		ile.setJeu();
		int[][] vueFinale = ile.getJeu();

		for (int i = 0; i < ile.getTaille(); i++) {
			for (int j = 0; j < ile.getTaille(); j++) {
				if (tourEquipe == 1) {
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
