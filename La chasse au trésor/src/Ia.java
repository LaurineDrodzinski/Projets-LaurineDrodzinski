import tps.Plateau;

/**
 * <b>Ia est la classe qui permet de lancer le mode contre l'ordinateur. Elle hérite de Jeu.</b>
 * 
 * @see Jeu
 */
public class Ia extends Jeu{
	
	private Ile ile;
	private Equipe equipe1;
	private Equipe equipe2;
	private Plateau plateau;
	
	/**Constructeur vide Ia.*/
	public Ia(){
		super();
	}
	
	/**
	 * Modification de la fonction deroulementJeu() de Jeu, pour intégrer l'ia.
	 */
	public void deroulementJeu() {

		ile = getIle();
		equipe1 = getEquipe(1);
		equipe2 = getEquipe(2);
		plateau = getPlateau();

		int tourEquipe = 1;

		Personnage p;

		int i = 0;

		do {

			while (i < equipe1.getTeam().size() && tourEquipe == 1 && !equipe1.teamGoToWin(ile)
					&& !equipe1.teamGoToLose(ile) && !equipe2.teamGoToLose(ile) && !equipe2.teamGoToWin(ile)) {

				vueEquipe(1);
				p = equipe1.getTeam().get(i);

				if (!p.getPieger()) {

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

					ile = p.action(ile, plateau, false);

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
						i = i - 1;
						try {Thread.sleep(1200);} catch (InterruptedException e) {/*gestion de l'erreur*/}
						ile.setJeu();
						plateau.setJeu(ile.getJeu());
						plateau.affichage();
					}
				} else {
					p.miseAJourPieger(ile);
				}

				ile.setJeu();
				vueEquipe(1);
				plateau.setJeu(ile.getJeu());
				plateau.affichage();
				i = i + 1;

				plateau.clearConsole();
				plateau.println(p.toString());

				try {Thread.sleep(200);} catch (InterruptedException e) {/*gestion de l'erreur*/}
			}
			tourEquipe = 2;
			i = 0;

			plateau.clearConsole();
			while (i < equipe2.getTeam().size() && tourEquipe == 2 && !equipe2.teamGoToWin(ile)
					&& !equipe2.teamGoToLose(ile) && !equipe1.teamGoToLose(ile) && !equipe1.teamGoToWin(ile)) {

				p = equipe2.getTeam().get(i);

				if (!p.getPieger()) {

					ile = p.action(ile, plateau, true);

					if (!p.Vivant()) {
						plateau.println("Un personnage adverse est mort : " + p.getNom());
						ile.removeParcelle(p.getX(), p.getY());
						if (ile.getParcelle(p.getX(), p.getY()) instanceof Terre) {
							if (p.getPossedeCle()) {
								((Terre) ile.getParcelle(p.getX(), p.getY())).setCle(true);
							} else if (p.getPossedeTresor()) {
								((Terre) ile.getParcelle(p.getX(), p.getY())).setTresor(true);
							}
						}
						p.getEquipe().getTeam().remove(p);
						i = i - 1;
						ile.setJeu();
						plateau.setJeu(ile.getJeu());
						try {Thread.sleep(1200);} catch (InterruptedException e) {/*gestion de l'erreur*/}
					}
				} else {
					p.miseAJourPieger(ile);
				}
				ile.setJeu();
				plateau.setJeu(ile.getJeu());
				i = i + 1;
			}
			tourEquipe = 1;
			i = 0;
		} while (!equipe1.teamGoToWin(ile) && !equipe2.teamGoToWin(ile) && !equipe1.teamGoToLose(ile)
				&& !equipe2.teamGoToLose(ile));

		plateau.close();
	}

}