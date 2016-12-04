import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import tps.Plateau;

/**
 * <b>Guerrier est la classe repr�sentant un type de personnage. Elle h�rite de
 * Personnage.</b>
 * <p>
 * Un guerrier est caract�ris� par :
 * <ul>
 * <li>Un bool�en, qui permet de d�finir s'il est en possession d'une arme pour
 * blesser les ennemis</li>
 * </ul>
 * </p>
 * 
 * @see Personnage
 */
public class Guerrier extends Personnage {

	/** Cet attribut permet de savoir si le guerrier poss�de une arme. */
	private boolean possedeUneArme = false;

	/** D�gats inflig�s par le guerrier. */
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
	 *            Coordonn�e.
	 * @param y
	 *            Coordonn�e.
	 * @param equipe
	 *            Num�ro de l'�quipe � qui il appartient.
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
	 * Renvoie les d�gats inflig�s par le guerrier.
	 * 
	 * @return Retourne un int.
	 */
	public int getDegats() {
		return degats;
	}

	/**
	 * Renvoie la valeur de possedeArme.
	 * 
	 * @return Retourne un boolean correspondant � la possession d'une arme.
	 */
	public boolean getArme() {
		return possedeUneArme;
	}

	/**
	 * Modifie les d�g�ts du guerrier.
	 * 
	 * @param degats
	 *            D�gats qu'il infligera.
	 */
	public void setDegats(int degats) {
		this.degats = degats;
	}

	/**
	 * Modifie les d�g�ts du guerrier s'il r�cup�re une arme ou perd celle qu'il
	 * poss�dait.
	 * 
	 * @param t
	 *            Si true alors le guerrier r�cup�re une arme.
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
	 * M�thode permettant de frapper des ennemis. Le guerrier inflige des d�g�ts
	 * augment�s s'il poss�de une arme.
	 *
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param perso
	 *            Personnage � frapper.
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 */
	public void frapper(Plateau plateau, Personnage perso, Ile ile, boolean ia) {
		if (!ia) {
			plateau.println("Vous venez d'infliger " + this.getDegats() + " d�gats !");
		} else {
			plateau.println("Vous avez perdu " + this.getDegats() + " points de vie !");
		}
		
		try {Thread.sleep(1000);} catch(InterruptedException e){/*gestion de l'erreur*/}

		if (possedeUneArme) {
			if (!ia) {
				plateau.println("Votre arme s'est cass�e. Retournez � votre navire pour en r�cup�rer une !");
				try {Thread.sleep(1000);} catch(InterruptedException e){/*gestion de l'erreur*/}
			}
			((Guerrier) this).setArme(false);
		}
		this.getEnergie().frapperPerso();
		perso.subitDegats(this, ile);

	}

	/**
	 * Cha�ne de caract�res r�sumant le guerrier.
	 * 
	 * @return Retourne un String.
	 */
	public String toString() {
		String arme = "";
		if (possedeUneArme) {
			arme = "Arm�";
		} else {
			arme = "D�sarm�";
		}
		return "Equipe " + this.getEquipe().getNum() + " :\nGuerrier - " + this.getEnergie().toString() + " - " + arme;
	}
	
	/**
	 * Methode qui g�re l'action du personnage.
	 * Elle attend un �v�nement (clic sur une case).
	 * 
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 * 
	 * @return Retourne une ile. Pour pouvoir mettre � jour le jeu.
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
