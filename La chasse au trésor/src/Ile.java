
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <b>Ile est la classe principale du programme. Elle permet de générer la carte
 * de jeu.</b>
 * <p>
 * L'ile est caractérisée par :
 * <ul>
 * <li>Un tableau de "Parcelle" à deux dimensions , qui contiendront soit un
 * rocher, un navire, de la terre, du vide etc.</li>
 * <li>Une taille</li>
 * <li>Un pourcentage de rochers</li>
 * <li>Un tableau de int à deux dimensions , qui servira à l'affichage</li>
 * <li>Le navire du joueur 1 et du joueur 2</li>
 * <li>Un tableau de String, qui stock le chemin des images nécessaires à
 * l'affichage</li>
 * </ul>
 * </p>
 * 
 * @see Parcelle
 */
public class Ile {
	/** Contient le plateau de jeu. */
	private Parcelle[][] ile;

	/** Détermine la taille du plateau. */
	private int taille;

	/** Détermine le pourcentage de rochers présents sur le plateau. */
	private int pourcentageRochers;

	/**
	 * Contient le plateau de jeu sous forme de int. Il sera utilisé pour
	 * l'affichage surtout.
	 */
	private int[][] jeu;

	/** Navire du joueur 1. */
	private Navire nav1;

	/** Navire du joueur 2. */
	private Navire nav2;

	/** Contient le chemin vers chaque images nécessaires à l'affichage. */
	private String[] images = { "images/rocher.png", "images/rochercasse.png", "images/mer.png", "images/sand.png",
			"images/coffreouvert.png", "images/coffreferme.png", "images/navire1.png", "images/navire2.png",
			"images/explorateur1.png", "images/explorateur2.png", "images/voleur1.png", "images/voleur2.png",
			"images/piegeur1.png", "images/piegeur2.png", "images/guerrier1.png", "images/guerrier2.png",
			"images/brouillard.png", "images/explorateur1selec.png", "images/explorateur2selec.png",
			"images/voleur1selec.png", "images/voleur2selec.png", "images/guerrier1selec.png",
			"images/guerrier2selec.png", "images/piegeur1selec.png", "images/piegeur2selec.png",
			"images/navire1selec.png", "images/navire2selec.png", "images/cle.png", "images/tresor.png",
			"images/piege.png"};

	/**
	 * Constructeur vide de Ile.
	 * <p>
	 * La taille est fixée à 10 et le pourcentage de rochers à 30.
	 * Initialisation des tableaux concernant le plateau. Instanciation du
	 * tableau de "Parcelle".
	 * </p>
	 */
	public Ile() {
		taille = 10;
		pourcentageRochers = 15;
		ile = new Parcelle[taille][taille];
		jeu = new int[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {

				ile[i][j] = new Parcelle();

				if (i != 0 && i != taille - 1 && j != 0 && j != taille - 1) {
					ile[i][j].setParcelle(new Terre());
				}

			}
		}
	}

	/**
	 * Constructeur de Ile.
	 * <p>
	 * Initialisation de la taille et du pourcentage de rochers. Initialisation
	 * des tableaux concernant le plateau. Instanciation du tableau de
	 * "Parcelle".
	 * </p>
	 * 
	 * @param taille
	 *            Taille du plateau.
	 * @param pourcentage
	 *            Pourcentage de rochers.
	 */
	public Ile(int taille, int pourcentage) {
		this.taille = taille;
		pourcentageRochers = pourcentage;
		ile = new Parcelle[taille][taille];
		jeu = new int[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				ile[i][j] = new Parcelle();

				if (i != 0 && i != taille - 1 && j != 0 && j != taille - 1) {
					ile[i][j].setParcelle(new Terre());
				}
			}
		}
	}

	/**
	 * Renvoie le personnage situé à la case concernée. S'il n'y a pas de
	 * personnage à cette case alors un objet null est renvoyé.
	 * 
	 * @param x
	 *            Coordonnée.
	 * @param y
	 *            Coordonnée.
	 * 
	 * @return Retourne un "Personnage".
	 * 
	 * @see Parcelle#getParcelle()
	 */
	public Personnage getPerso(int x, int y) {
		if (ile[x][y].getParcelle() instanceof Personnage) {
			return (Personnage) ile[x][y].getParcelle();
		}
		return null;
	}

	/**
	 * Retourne la valeur de "taille".
	 * 
	 * @return Retourne un int.
	 * 
	 * @see Ile#taille
	 */
	public int getTaille() {
		return taille;
	}

	/**
	 * Retourne le tableau contenant les chemins des images du jeu.
	 * 
	 * @return Retourne un tableau de String.
	 * 
	 * @see Ile#images
	 */
	public String[] getImages() {
		return images;
	}

	/**
	 * Retourne l'ile.
	 * 
	 * @return Retourne un tableau à deux dimensions contenant des "Parcelle".
	 * 
	 * @see Ile#ile
	 */
	public Parcelle[][] getIle() {
		return ile;
	}

	/**
	 * Retourne la valeur de la parcelle en question.
	 * 
	 * @param x
	 *            Coordonnee.
	 * @param y
	 *            Coordonnee.
	 * 
	 * @return Retourne un objet.
	 * 
	 * @see Parcelle#getParcelle()
	 * @see Ile#ile
	 */
	public Object getParcelle(int x, int y) {
		return ile[x][y].getParcelle();
	}

	/**
	 * Retourne le navire concerné.
	 * 
	 * @return Retourne un Navire.
	 */
	public Navire getNav(int joueur) {
		if (joueur == 1) {
			return nav1;
		} else {
			return nav2;
		}
	}

	/**
	 * Retourne le tableau de int associé au plateau de jeu.
	 * 
	 * @return Retourne un tableau de int à deux dimensions.
	 */
	public int[][] getJeu() {
		return jeu;
	}

	/**
	 * Modifie le tableau associé au plateau. Sert généralement à faire une ou
	 * peu de modifications.
	 * 
	 * @param jeu
	 *            Tableau de int voulu.
	 */
	public void modifierJeu(int[][] jeu) {
		this.jeu = jeu;
	}

	/** Pour les tests*/
	public void setNav(Navire n, int e){
		if(e==1){
			nav1=n;
		}else{
			nav2=n;
		}
	}
	
	/**
	 * Permet de placer un personnage sur la parcelle voulue.
	 * 
	 * @param x
	 *            Coordonnée.
	 * @param y
	 *            Coordonnée.
	 * @param p
	 *            Type du personnage.
	 * 
	 * @see Parcelle#setParcelle(Object)
	 */
	public void setPerso(int x, int y, Personnage p) {
		ile[x][y].setParcelle(p);
	}

	/**
	 * Permet de changer la parcelle voulue.
	 * 
	 * @param x
	 *            Coordonnée.
	 * @param y
	 *            Coordonnée.
	 * @param p
	 *            Type de la parcelle.
	 * 
	 * @see Parcelle#setParcelle(Object)
	 */
	public void setParcelle(Parcelle p, int x, int y) {
		ile[x][y].setParcelle(p);
	}

	/**
	 * Efface la parcelle en question. La parcelle devient de type "Vide".
	 * 
	 * @param x
	 *            Coordonnee.
	 * @param y
	 *            Coordonnee.
	 * 
	 * @see Parcelle#removeParcelle()
	 * @see Ile#ile
	 */
	public void removeParcelle(int x, int y) {
		ile[x][y].removeParcelle();
	}

	/**
	 * Met à jour le tableau de int à deux dimensions associé au plateau de
	 * "Parcelle".
	 * 
	 * @see Ile#ile
	 * @see Ile#jeu
	 */
	public void setJeu() {

		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
					jeu[j][i] = 3;
				}
			}
		}

		for (int i = 1; i < taille - 1; i++) {
			for (int j = 1; j < taille - 1; j++) {
				if (ile[i][j].getParcelle() instanceof Rocher) {
					jeu[j][i] = 1;
					if ((((Rocher) ile[i][j].getParcelle()).getCoffre())!=null && !(((Rocher) ile[i][j].getParcelle()).getCoffre()).getTresor()) {
						jeu[j][i] = 5;
					} else if (((Rocher) ile[i][j].getParcelle()).getDecouverteCle()) {
						jeu[j][i] = 2;
					}
				} else if (ile[i][j].getParcelle() instanceof Terre) {
					jeu[j][i] = 4;
					if (((Terre) ile[i][j].getParcelle()).getCle()) {
						jeu[j][i] = 28;
					} else if (((Terre) ile[i][j].getParcelle()).getTresor()) {
						jeu[j][i] = 29;
					}
				} else if (ile[i][j].getParcelle() instanceof Navire) {
					if ((((Navire) ile[i][j].getParcelle()).getEquipe()) == 1) {
						jeu[j][i] = 7;
					} else {
						jeu[j][i] = 8;
					}
				} else if (ile[i][j].getParcelle() instanceof Personnage) {
					if (i == (nav1.getPos())[0] && j == (nav1.getPos())[1]) {
						jeu[j][i] = 7;
					} else if (i == (nav2.getPos())[0] && j == (nav2.getPos())[1]) {
						jeu[j][i] = 8;
					} else {
						if (ile[i][j].getParcelle() instanceof Explorateur) {
							if ((((Personnage) ile[i][j].getParcelle()).getEquipe().getNum()) == 1) {
								jeu[j][i] = 9;
							} else {
								jeu[j][i] = 10;
							}
						} else if (ile[i][j].getParcelle() instanceof Voleur) {
							if ((((Personnage) ile[i][j].getParcelle()).getEquipe().getNum()) == 1) {
								jeu[j][i] = 11;
							} else {
								jeu[j][i] = 12;
							}
						} else if (ile[i][j].getParcelle() instanceof Piegeur) {
							if ((((Personnage) ile[i][j].getParcelle()).getEquipe().getNum()) == 1) {
								jeu[j][i] = 13;
							} else {
								jeu[j][i] = 14;
							}
						} else if (ile[i][j].getParcelle() instanceof Guerrier) {
							if ((((Personnage) ile[i][j].getParcelle()).getEquipe().getNum()) == 1) {
								jeu[j][i] = 15;
							} else {
								jeu[j][i] = 16;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Pose les rochers sur le plateau en fonction du pourcentage de rochers
	 * choisi auparavant.
	 * 
	 * @see Ile#pourcentageRochers
	 * @see Rocher
	 */
	public void remplirRochers() {
		Random random = new Random();

		for (int i = 1; i < taille - 1; i++) {
			for (int j = 1; j < taille - 1; j++) {
				if (random.nextInt(100) < pourcentageRochers) {
					ile[i][j].setParcelle(new Rocher(false, false));
				}
			}
		}
	}

	/**
	 * Place la clé et le coffre dans un rocher chacun. La position des rochers
	 * est déterminée grâce à la fonction "getPositionsRochers". Les deux
	 * rochers sont tirés au hasard.
	 * 
	 * @see Ile#getPositionsRochers(Parcelle[][])
	 * @see Rocher
	 */
	public void remplirCleCoffre() {
		Random random = new Random();

		int[][] positionsRochers = getPositionsRochers(ile);

		int posCle = random.nextInt(positionsRochers.length - 1);
		int posCoffre;

		do {
			posCoffre = random.nextInt(positionsRochers.length - 1);
		} while (posCle == posCoffre);

		int ligneCle = positionsRochers[posCle][0];
		int colonneCle = positionsRochers[posCle][1];
		int ligneCoffre = positionsRochers[posCoffre][0];
		int colonneCoffre = positionsRochers[posCoffre][1];

		ile[ligneCle][colonneCle].setParcelle(new Rocher(true, false));
		ile[ligneCoffre][colonneCoffre].setParcelle(new Rocher(false, true));
	}

	/**
	 * Place un navire de façon aléatoire sur l'un des bords de l'ile.
	 * 
	 * @param joueur
	 *            Numéro du joueur
	 * 
	 * @see Navire
	 */
	public void remplirNavire(int joueur) {

		Random random = new Random();
		int x = 0;
		int y = 0;
		int bordIle;

		do {
			bordIle = random.nextInt(4);

			if (bordIle == 0) {
				x = 1;
				y = random.nextInt(taille - 4) + 2;
			} else if (bordIle == 1) {
				x = random.nextInt(taille - 4) + 2;
				y = taille - 2;
			} else if (bordIle == 2) {
				x = taille - 2;
				y = random.nextInt(taille - 4) + 2;
			} else if (bordIle == 3) {
				x = random.nextInt(taille - 4) + 2;
				y = 1;
			}
		} while (!(ile[x][y]).debarquementPossible(ile, x, y, taille));

		ile[x][y].setParcelle(new Navire(joueur, x, y));

		if (joueur == 1) {
			nav1 = new Navire(joueur, x, y);
		} else {
			nav2 = new Navire(joueur, x, y);
		}

	}

	/**
	 * Retourne la position des rochers dans un tableau à deux dimensions.
	 * 
	 * @param plateau
	 *            Ile de la partie en cours.
	 * 
	 * @return Retourne un tableau de int à deux dimensions.
	 */
	public int[][] getPositionsRochers(Parcelle[][] plateau) {
		int nbRochers = 0;

		for (int i = 1; i < taille - 1; i++) {
			for (int j = 1; j < taille - 1; j++) {
				if (plateau[j][i].getParcelle() instanceof Rocher) {
					nbRochers = nbRochers + 1;
				}
			}
		}

		int[][] positions = new int[nbRochers][2];
		int nb = 0;

		for (int i = 1; i < taille - 1; i++) {
			for (int j = 1; j < taille - 1; j++) {
				if (plateau[j][i].getParcelle() instanceof Rocher) {
					positions[nb][0] = j;
					positions[nb][1] = i;
					nb = nb + 1;
				}
			}
		}

		return positions;
	}

	/**
	 * Permet de savoir si tous les rochers sont accessibles. Fait appelle à une
	 * méthode récursive.
	 * 
	 * @param pos
	 *            Un tableau de int. Il contiendra les coordonneés d'un des
	 *            bateaux.
	 * 
	 * @return Retourne un booléen.
	 * 
	 * @see Ile#accessible(List, boolean[][])
	 */
	public boolean toutEstAccessible(int[] pos) {

		List<Coordonnees> coo = new ArrayList<Coordonnees>();
		boolean[][] accesCases = new boolean[taille][taille];

		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				accesCases[i][j] = false;
			}
		}
		coo.add(new Coordonnees(pos[0], pos[1]));

		boolean[][] acces = accessible(coo, accesCases);

		for (int i = 1; i < taille - 1; i++) {
			for (int j = 1; j < taille - 1; j++) {
				if (!acces[i][j]) {
					return false;
				}
			}
		}

		return true;

	}

	/**
	 * Méthode récursive parcourant l'ile à l'aide d'une liste de coordonnées.
	 * 
	 * @param coo
	 *            Une liste contenant les coordonnées des cases à analyser.
	 * @param accesCases
	 *            Un tableau à deux dimensions de même taille que l'ile
	 *            contenant des boolean. Si true, case accessible. Si false,
	 *            case inaccessible.
	 * 
	 * @return Retourne un tableau de booléens à deux dimensions.
	 */
	public boolean[][] accessible(List<Coordonnees> coo, boolean[][] accesCases) {
		int x = (coo.get(0)).getX();
		int y = (coo.get(0)).getY();

		final int rocher = 1;
		final int mer = 3;
		final int terre = 4;
		final int navire1 = 7;
		final int navire2 = 8;

		if (jeu[x][y - 1] == mer || jeu[x][y - 1] == rocher || jeu[x][y - 1] == navire1 || jeu[x + 1][y] == navire2) {
			accesCases[x][y - 1] = true;
		} else if (jeu[x][y - 1] == terre && !accesCases[x][y - 1]) {
			accesCases[x][y - 1] = true;
			coo.add(new Coordonnees(x, y - 1));
		}

		if (jeu[x - 1][y] == mer || jeu[x - 1][y] == rocher || jeu[x - 1][y] == navire1 || jeu[x + 1][y] == navire2) {
			accesCases[x - 1][y] = true;
		} else if (jeu[x - 1][y] == terre && !accesCases[x - 1][y]) {
			accesCases[x - 1][y] = true;
			coo.add(new Coordonnees(x - 1, y));
		}

		if (jeu[x][y + 1] == mer || jeu[x][y + 1] == rocher || jeu[x][y + 1] == navire1 || jeu[x + 1][y] == navire2) {
			accesCases[x][y + 1] = true;
		} else if (jeu[x][y + 1] == terre && !accesCases[x][y + 1]) {
			accesCases[x][y + 1] = true;
			coo.add(new Coordonnees(x, y + 1));
		}

		if (jeu[x + 1][y] == mer || jeu[x + 1][y] == rocher || jeu[x + 1][y] == navire1 || jeu[x + 1][y] == navire2) {
			accesCases[x + 1][y] = true;
		} else if (jeu[x + 1][y] == terre && !accesCases[x + 1][y]) {
			accesCases[x + 1][y] = true;
			coo.add(new Coordonnees(x + 1, y));
		}

		coo.remove(0);

		if (!coo.isEmpty()) {
			accessible(coo, accesCases);
		}

		return accesCases;

	}
}
