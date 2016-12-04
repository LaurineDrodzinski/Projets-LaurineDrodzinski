
import tps.Plateau;

/**
 * <b>Personnage est une classe regroupant tout type de personnage.</b>
 * <p>
 * Un personnage est caractérisé par :
 * <ul>
 * <li>Une energie actuelle</li>
 * <li>Des coordonnées</li>
 * <li>Un attribut associé à la possession du trésor ou non</li>
 * <li>Un attribut associé à la possession de la clé ou non</li>
 * <li>Une équipe</li>
 * </ul>
 * </p>
 */
public abstract class Personnage extends Parcelle {

	/** Coordonnée */
	private int x;

	/** Coordonnée */
	private int y;

	/**
	 * Energie du personnage
	 * 
	 * @see Energie
	 */
	private Energie energie = new Energie();

	/**
	 * Equipe du personnage.
	 * 
	 * @see Equipe
	 */
	private Equipe equipe;

	/** Possession de la clé. */
	private boolean possedeLaCle = false;

	/** Possession du trésor. */
	private boolean possedeTresor;
	
	/** Indique si le joueur est pris dans un piège */
	private boolean pieger;
	
	/** Indique pendant combien de temps le joueur est pris dans le piège */
	private int tempsPieger;

	/**
	 * Renvoie true si le personnage est en vie, false sinon.
	 * 
	 * @return Retourne un boolean.
	 */
	public boolean Vivant() {
		return energie.getEnVie();
	}
	
	/**
	 * Renvoie true si le personnage est bloqué, false sinon.
	 * 
	 * @return Retourne un boolean.
	 */
	public boolean getPieger() {
		return pieger;
	}

	/** Le personnage est désormais piégé */
	public void estPieger(){
		tempsPieger=2;
		pieger=true;
	}
	
	/** Met à jour le piège à chaque tour.
	 * 
	 * @param ile
	 *            Ile de la partie en cours.
	 */
	public void miseAJourPieger(Ile ile) {
		if (pieger) {
			if (tempsPieger > 0) {
				tempsPieger = tempsPieger - 1;
			}

			if (tempsPieger == 0) {
				pieger = false;
			}
		}
	}

	/**
	 * Renvoie une coordonnée (x).
	 * 
	 * @return Retourne un int.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Renvoie une coordonnée (y).
	 * 
	 * @return Retourne un int.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Indique si le personnage possède la clé.
	 * 
	 * @return Retourne un boolean.
	 */
	public boolean getPossedeCle() {
		return possedeLaCle;
	}

	/**
	 * Retourne un l'énergie du personnage.
	 * 
	 * @return Retourne une Energie
	 */
	public Energie getEnergie() {
		return energie;
	}

	/**
	 * Retourne l'équipe du personnage.
	 * 
	 * @return Retourne une Equipe.
	 */
	public Equipe getEquipe() {
		return equipe;
	}

	/**
	 * Renvoie le nom du personnage.
	 * 
	 * @return Retourne un String.
	 */
	public String getNom() {
		return this.getNom();
	}

	/**
	 * Indique si le personnage possède le trésor.
	 * 
	 * @return Retourne un boolean.
	 */
	public boolean getPossedeTresor() {
		return possedeTresor;
	}

	/**
	 * Modifie la possession du trésor.
	 * 
	 * @param t
	 *            Valeur voulue.
	 */
	public void setPossedeTresor(boolean t) {
		possedeTresor = t;
	}

	/**
	 * Modifie la possession de la clé.
	 * 
	 * @param c
	 *            Valeur voulue.
	 */
	public void setPossedeCle(boolean c) {
		possedeLaCle = c;
	}

	/**
	 * Modifie l'attribut x.
	 * 
	 * @param x
	 *            Valeur voulue
	 * 
	 * @see Personnage#x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Modifie l'attribut y.
	 * 
	 * @param y
	 *            Valeur voulue
	 * 
	 * @see Personnage#y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Modifie le l'énergie du personnage.
	 * 
	 * @param e
	 *            Valeur voulue
	 * 
	 * @see Personnage#energie
	 */
	public void setEnergie(int e) {
		energie.setEnergie(e);
	}

	/**
	 * Modifie le l'énergie initiale du personnage.
	 * 
	 * @param e
	 *            Valeur voulue
	 * 
	 * @see Personnage#energie
	 */
	public void setEnergieIni(int e) {
		energie.setEnergieIni(e);
	}

	/**
	 * Modifie l'equipe.
	 * 
	 * @param equipe
	 *            Valeur voulue
	 * 
	 * @see Personnage#equipe
	 */
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	/**
	 * Methode qui gère l'action du personnage.
	 * Elle attend un évènement (clic sur une case).
	 * Cette fonction sera différente en fonction du type du personnage.
	 * 
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 * 
	 * @return Retourne une ile. Pour pouvoir mettre à jour le jeu.
	 */
	public Ile action(Ile ile, Plateau plateau, boolean ia){
		return null;
	};
	
	
	/**
	 * Methode qui gère le déplacement simple du personnage.
	 * 
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 * @param xPerso
	 *            Coordonnée.
	 * @param yPerso
	 *            Coordonnée.
	 * 
	 * @return Retourne un boolean. Pour savoir si l'action a été réalisée.
	 */
	public boolean deplacementSimple(Ile ile, Plateau plateau, int xPerso, int yPerso, boolean ia){
		if (!(ile.getParcelle(xPerso, yPerso) instanceof Personnage) && !(ile.getParcelle(xPerso, yPerso) instanceof Rocher)) {
			if (!this.estSurBase(ile) && ile.getParcelle(xPerso, yPerso) != this) {
				ile.removeParcelle(x, y);
				this.ramassage(ile, plateau, xPerso, yPerso, ia);
				ile.setPerso(xPerso, yPerso, this);
				this.getEnergie().deplacementSimple();
			} else if (this.estSurBase(ile) && !(ile.getParcelle(xPerso, yPerso) instanceof Navire)) {
				ile.setPerso(xPerso, yPerso, this);
			}
			this.setX(xPerso);
			this.setY(yPerso);
			return true;
		}
		return false;
	}
	
	/**
	 * Methode qui gère le fait de récupérer une clé ou un trésor sous un rocher.
	 * 
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 * @param xPerso
	 *            Coordonnée.
	 * @param yPerso
	 *            Coordonnée.
	 * 
	 * @return Retourne un boolean. Pour savoir si l'action a été réalisée.
	 */
	public boolean recupererCleTresor(Ile ile, Plateau plateau, int xPerso, int yPerso, boolean ia){
		if (this.getPossedeCle() && ((Rocher) ile.getParcelle(xPerso, yPerso)).getDecouverteCoffre(equipe.getNum())) {
			if (((Rocher) ile.getParcelle(xPerso, yPerso)).getCoffre().prendreTresor()) {
				if (!ia) {
					plateau.println("Bravo vous venez de prendre le trésor!");
				}
				((Rocher) ile.getParcelle(xPerso, yPerso)).getCoffre().prendreTresor();
				this.setPossedeCle(false);
				this.setPossedeTresor(true);
			} else {
				if (!ia) {
					plateau.println("Le trésor n'est plus là.");
				}
			}
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				// gestion de l'erreur
			}
		} else if (((Rocher) ile.getParcelle(xPerso, yPerso)).getDecouverteCoffre(equipe.getNum())
				&& !this.getPossedeCle()) {
			if (!ia) {
				plateau.println("Vous n'avez pas la clé.");

				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					// gestion de l'erreur
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Methode qui gère le fait de tomber dans un piège.
	 * 
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 * @param xPerso
	 *            Coordonnée.
	 * @param yPerso
	 *            Coordonnée.
	 * 
	 * @return Retourne un boolean. Pour savoir si l'action a été réalisée.
	 */
	public boolean tomberPiege(Ile ile, Plateau plateau, int xPerso, int yPerso, boolean ia){
		if (ile.getParcelle(xPerso, yPerso) instanceof Terre
				&& ((Terre) ile.getParcelle(xPerso, yPerso)).getPiege()
				&& this.getEquipe().getNum() != ((Terre) ile.getParcelle(xPerso, yPerso)).getEquipePiege()) {

			this.estPieger();
			ile.removeParcelle(x, y);
			ile.setPerso(xPerso, yPerso, this);
			this.setX(xPerso);
			this.setY(yPerso);

			if (!ia) {
				plateau.println("Vous êtes tombé dans un piège.\nVous devez attendre 3 tours !");
			} else {
				plateau.println("Quelqu'un est tombé dans votre piège !");
			}

			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// gestion de l'erreur
			}
			return true;
		}
		return false;
	}

	/**
	 * Methode qui gère le fait de ramasser un objet au sol.
	 * 
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 * @param xPerso
	 *            Coordonnée.
	 * @param yPerso
	 *            Coordonnée.
	 * 
	 * @return Retourne un boolean. Pour savoir si l'action a été réalisée.
	 */
	public boolean ramassage(Ile ile, Plateau plateau, int xPerso, int yPerso, boolean ia) {
		if (ile.getParcelle(xPerso, yPerso) instanceof Terre) {
			if (((Terre) ile.getParcelle(xPerso, yPerso)).getCle()) {
				this.setPossedeCle(true);
				((Terre) ile.getParcelle(xPerso, yPerso)).setCle(false);
				if (!ia) {
					plateau.println("Vous avez ramassé la clé.");

					try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						// gestion de l'erreur
					}
				}
			} else if (((Terre) ile.getParcelle(xPerso, yPerso)).getTresor()) {
				this.setPossedeTresor(true);
				((Terre) ile.getParcelle(xPerso, yPerso)).setTresor(false);
				if (!ia) {
					plateau.println("Vous avez ramassé le trésor.");
					try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						// gestion de l'erreur
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Methode qui gère le fait de rentrer au navire.
	 * 
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 * @param xPerso
	 *            Coordonnée.
	 * @param yPerso
	 *            Coordonnée.
	 * 
	 * @return Retourne un boolean. Pour savoir si l'action a été réalisée.
	 */
	public boolean rentrerNavire(Ile ile, Plateau plateau, int xPerso, int yPerso, boolean ia) {
		if (ile.getParcelle(xPerso, yPerso) instanceof Navire && (x != xPerso || y != yPerso)) {

			ile.removeParcelle(x, y);
			this.setX(xPerso);
			this.setY(yPerso);

			return true;
		}
		return false;
	}
	
	/**
	 * Methode qui gère le fait de passer un objet à un personnage ami.
	 * 
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 * @param xPerso
	 *            Coordonnée.
	 * @param yPerso
	 *            Coordonnée.
	 * 
	 * @return Retourne un boolean. Pour savoir si l'action a été réalisée.
	 */
	public boolean passerObjet(Ile ile, Plateau plateau, int xPerso, int yPerso, boolean ia) {
		Personnage p;
		
		if ((ile.getParcelle(xPerso, yPerso) instanceof Personnage)
				&& this != ((Personnage) ile.getParcelle(xPerso, yPerso))
				&& this.getEquipe().getNum() == ((Personnage) ile.getParcelle(xPerso, yPerso)).getEquipe().getNum()
				&& (((Personnage) ile.getParcelle(xPerso, yPerso)).getPossedeCle() || this.getPossedeCle()
						|| ((Personnage) ile.getParcelle(xPerso, yPerso)).getPossedeTresor() || this.getPossedeTresor())
				&& !this.estSurBase(ile)) {

			p = (Personnage) ile.getParcelle(xPerso, yPerso);
			
			if (p.getPossedeCle() == true) {
				p.setPossedeCle(false);
				this.setPossedeCle(true);
				if (!ia) {
					plateau.println("Vous avez récupéré la clé.");
				}
			} else if (p.getPossedeTresor() == true) {
				p.setPossedeTresor(false);
				this.setPossedeTresor(true);
				if (!ia) {
					plateau.println("Vous avez récupéré le trésor.");
				}
			} else if (this.getPossedeCle() == true) {
				p.setPossedeCle(true);
				this.setPossedeCle(false);
				if (!ia) {
					plateau.println("Vous avez donné la clé.");
				}
			} else if (this.getPossedeTresor() == true) {
				p.setPossedeTresor(true);
				this.setPossedeTresor(false);
				if (!ia) {
					plateau.println("Vous avez donné le trésor.");
				}
			}

			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// gestion de l'erreur
			}
			return true;
		}
		return false;

	}

	/**
	 * Analyse si le personnage est sur son bateau.
	 * 
	 * @param ile
	 *            Ile de la partie en cours.
	 * 
	 * @return Retourne un booléen.
	 * 
	 */
	public boolean estSurBase(Ile ile) {
		int[] posNavire = ile.getNav(equipe.getNum()).getPos();
		if (x == posNavire[0] && y == posNavire[1]) {
			return true;
		}
		return false;
	}

	/**
	 * Diminue l'energie du personnage en fonction de qui lui inflige des
	 * dégats.
	 * 
	 * @param perso
	 *            Personnage attaquant.
	 */
	public void subitDegats(Personnage perso, Ile ile) {
		if(energie.getEnergie() - ((Guerrier) perso).getDegats()>0){
			energie.setEnergie(energie.getEnergie() - ((Guerrier) perso).getDegats());
		}else{
			this.getEnergie().setEnergie(0);
			this.getEnergie().setEnVie(false);
			ile.removeParcelle(this.getX(), this.getY());
			
			if (ile.getParcelle(this.getX(), this.getY()) instanceof Terre) {
				if (this.getPossedeCle()) {
					((Terre) ile.getParcelle(this.getX(), this.getY())).setCle(true);
				} else if(this.getPossedeTresor()){
					((Terre) ile.getParcelle(this.getX(), this.getY())).setTresor(true);
				}
			}
			this.getEquipe().getTeam().remove(this);
		}
	}
}