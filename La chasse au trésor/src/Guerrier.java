import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import tps.Plateau;

/**
 * <b>Guerrier est la classe représentant un type de personnage. Elle hérite de
 * Personnage.</b>
 * <p>
 * Un guerrier est caractérisé par :
 * <ul>
 * <li>Un booléen, qui permet de définir s'il est en possession d'une arme pour
 * blesser les ennemis</li>
 * </ul>
 * </p>
 * 
 * @see Personnage
 */
public class Guerrier extends Personnage {

	/** Cet attribut permet de savoir si le guerrier possède une arme. */
	private boolean possedeUneArme = false;

	/** Dégats infligés par le guerrier. */
	private int degats;

	/** Nom du guerrier. */
	private String nom = "guerrier";

	/** Constructeur vide de Guerrier. */
	public Guerrier() {
	}

	/**
	 * Constructeur de Guerrier.
	 * 
	 * @param x
	 *            Coordonnée.
	 * @param y
	 *            Coordonnée.
	 * @param equipe
	 *            Numéro de l'équipe à qui il appartient.
	 */
	public Guerrier(int x, int y, Equipe equipe) {
		this.setX(x);
		this.setY(y);
		this.setEquipe(equipe);
		this.setDegats(4);
		this.setEnergieIni(40);
		this.setEnergie(40);
	}

	/**
	 * Renvoie le nom du guerrier.
	 * 
	 * @return Retourne un String.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Renvoie les dégats infligés par le guerrier.
	 * 
	 * @return Retourne un int.
	 */
	public int getDegats() {
		return degats;
	}

	/**
	 * Renvoie la valeur de possedeArme.
	 * 
	 * @return Retourne un boolean correspondant à la possession d'une arme.
	 */
	public boolean getArme() {
		return possedeUneArme;
	}

	/**
	 * Modifie les dégâts du guerrier.
	 * 
	 * @param degats
	 *            Dégats qu'il infligera.
	 */
	public void setDegats(int degats) {
		this.degats = degats;
	}

	/**
	 * Modifie les dégâts du guerrier s'il récupère une arme ou perd celle qu'il
	 * possédait.
	 * 
	 * @param t
	 *            Si true alors le guerrier récupère une arme.
	 */
	public void setArme(boolean t) {
		possedeUneArme = t;
		if (t) {
			this.setDegats(8);
		} else {
			this.setDegats(4);
		}
	}

	/**
	 * Méthode permettant de frapper des ennemis. Le guerrier inflige des dégâts
	 * augmentés s'il possède une arme.
	 *
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param perso
	 *            Personnage à frapper.
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 */
	public void frapper(Plateau plateau, Personnage perso, Ile ile, boolean ia) {
		if (!ia) {
			plateau.println("Vous venez d'infliger " + this.getDegats() + " dégats !");
		} else {
			plateau.println("Vous avez perdu " + this.getDegats() + " points de vie !");
		}
		
		try {Thread.sleep(1000);} catch(InterruptedException e){/*gestion de l'erreur*/}

		if (possedeUneArme) {
			if (!ia) {
				plateau.println("Votre arme s'est cassée. Retournez à votre navire pour en récupérer une !");
				try {Thread.sleep(1000);} catch(InterruptedException e){/*gestion de l'erreur*/}
			}
			((Guerrier) this).setArme(false);
		}
		this.getEnergie().frapperPerso();
		perso.subitDegats(this, ile);

	}

	/**
	 * Chaîne de caractères résumant le guerrier.
	 * 
	 * @return Retourne un String.
	 */
	public String toString() {
		String arme = "";
		if (possedeUneArme) {
			arme = "Armé";
		} else {
			arme = "Désarmé";
		}
		return "Equipe " + this.getEquipe().getNum() + " :\nGuerrier - " + this.getEnergie().toString() + " - " + arme;
	}
	
	/**
	 * Methode qui gère l'action du personnage.
	 * Elle attend un évènement (clic sur une case).
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
	public Ile action(Ile ile, Plateau plateau, boolean ia) {

		int xPerso = 0;
		int yPerso = 0;
		int x = getX();
		int y = getY();
		Equipe equipe = getEquipe();
		Energie energie = getEnergie();

		int direction = 0;

		boolean actionFinie=false;
		
		InputEvent evenement=null;
		
		do {
			if(!ia){
				do{
					evenement = plateau.waitEvent();
				}while(!(evenement instanceof MouseEvent));
				xPerso = plateau.getX((MouseEvent) evenement);
				yPerso = plateau.getY((MouseEvent) evenement);
			}else{
				direction=(new Random()).nextInt(5);
				if(direction==0){	 	xPerso=x-1; yPerso=y;
				}else if(direction==1){ xPerso=x; 	yPerso=y+1;
				}else if(direction==2){ xPerso=x+1; yPerso=y;
				}else if(direction==3){ xPerso=x; 	yPerso=y-1;
				}else if(direction==4){ xPerso=x; 	yPerso=y;}
			}
		} while (!((xPerso == x - 1 && yPerso == y) || (xPerso == x && yPerso == y + 1)
				|| (xPerso == x + 1 && yPerso == y) || (xPerso == x && yPerso == y - 1)
				|| (xPerso == x && yPerso == y) || (xPerso == x - 1 && yPerso == y - 1)
				|| (xPerso == x - 1 && yPerso == y + 1) || (xPerso == x + 1 && yPerso == y + 1)
				|| (xPerso == x + 1 && yPerso == y - 1)) || xPerso == 0 || xPerso == ile.getTaille() - 1
				|| yPerso == 0 || yPerso == ile.getTaille() - 1
				|| (ile.getParcelle(xPerso, yPerso) instanceof Personnage
						&& this.estSurBase(ile))
				|| (ile.getParcelle(xPerso, yPerso) instanceof Personnage
						&& ((Personnage)ile.getParcelle(xPerso, yPerso)).getEquipe().getNum() == equipe.getNum()
						&& this != ile.getParcelle(xPerso, yPerso)
						&& (!this.getPossedeCle()
						&& !this.getPossedeTresor())
						&& !((Personnage)ile.getParcelle(xPerso, yPerso)).getPossedeCle() 
						&& !((Personnage)ile.getParcelle(xPerso, yPerso)).getPossedeTresor())
				|| (ile.getParcelle(xPerso, yPerso) instanceof Rocher
						&& !((Rocher) ile.getParcelle(xPerso, yPerso)).getDecouverteCoffre(equipe.getNum()))
				|| (ile.getParcelle(xPerso, yPerso) instanceof Navire
						&& ((Navire) ile.getParcelle(xPerso, yPerso)).getEquipe() != equipe.getNum())
				|| (!ia && ((MouseEvent) evenement).getButton() != MouseEvent.BUTTON1)
				|| (ile.getParcelle(xPerso, yPerso) instanceof Terre
						&& ((Terre) ile.getParcelle(xPerso, yPerso)).getPiege()
						&& this.getEquipe().getNum() == ((Terre) ile.getParcelle(xPerso, yPerso)).getEquipePiege()));
		
		if ((ile.getParcelle(xPerso, yPerso) instanceof Rocher)) {
			recupererCleTresor(ile, plateau, xPerso, yPerso, ia);
			actionFinie=true;
		} 

		if (!actionFinie) {
			actionFinie = tomberPiege(ile, plateau, xPerso, yPerso, ia);
		}

		if (!actionFinie) {
			actionFinie = passerObjet(ile, plateau, xPerso, yPerso, ia);
		}
		
		if (!actionFinie) {
			actionFinie = rentrerNavire(ile, plateau, xPerso, yPerso, ia);
		}

		if (!actionFinie && ile.getParcelle(xPerso, yPerso) instanceof Personnage
				&& ile.getParcelle(xPerso, yPerso) != this
				&& this.getEquipe().getNum() != ((Personnage) ile.getParcelle(xPerso, yPerso)).getEquipe().getNum()
				&& !this.estSurBase(ile)) {
			frapper(plateau, (Personnage) ile.getParcelle(xPerso, yPerso), ile, ia);
			actionFinie = true;
		}

		if (!actionFinie) {
			actionFinie = deplacementSimple(ile, plateau, xPerso, yPerso, ia);
		}
		
		if (this.estSurBase(ile)) {
			energie.recup();
			setArme(true);
		}	

		return ile;
	}
}
