import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import tps.Plateau;

/**
 * <b>Explorateur est la classe représentant un type de personnage. Elle hérite
 * de Personnage.</b>
 * <p>
 * Un explorateur est caractérisé par :
 * <ul>
 * <li>Un nom</li>
 * </ul>
 * </p>
 * 
 * @see Personnage
 */
public class Explorateur extends Personnage {

	/** Nom de l'explorateur. */
	private String nom = "explorateur";

	/** Constructeur vide de Explorateur. */
	public Explorateur() {
	}
	
	/**
	 * Constructeur de Explorateur.
	 * 
	 * @param x
	 *            Coordonnée.
	 * @param y
	 *            Coordonnée.
	 * @param equipe
	 *            Numéro de l'équipe à qui il appartient.
	 */
	public Explorateur(int x, int y, Equipe equipe) {
		this.setX(x);
		this.setY(y);
		this.setEquipe(equipe);
		this.setEnergieIni(30);
		this.setEnergie(30);
	}

	/**
	 * Renvoie le nom de l'explorateur.
	 * 
	 * @return Retourne un String.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Méthode permettant de regarder ce qu'il y a sous le rocher. Si il y a une
	 * clé alors il est possible de la prendre. S'il y a un coffre avec un
	 * trésor et que l'explorateur possède la clé alors il est possible de
	 * prendre ce trésor.
	 * 
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param rocher
	 *            Rocher que l'on veut soulever.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 */
	public void souleverRocher(Plateau plateau, Rocher rocher, boolean ia) {
		if (!rocher.getDecouverteCoffre(this.getEquipe().getNum()) && !rocher.getDecouverteCle()) {
			this.getEnergie().souleverRocher();
		}

		if (rocher.getPresenceCle()) {
			rocher.decouvreCle();
			rocher.prendreCle();
			this.setPossedeCle(true);
			if (!ia) {
				plateau.println("Vous avez pris la clé!");
				try {Thread.sleep(1000);} catch(InterruptedException e){/*gestion de l'erreur*/}
			}
		} else if (rocher.getPresenceCoffre()) {
			rocher.decouvreCoffre(this.getEquipe().getNum());
		}
	}

	/**
	 * Chaîne de caractères résumant l'explorateur.
	 * 
	 * @return Retourne un String.
	 */
	public String toString() {
		return "Equipe " + this.getEquipe().getNum() + " :\nExplorateur - " + this.getEnergie().toString();
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
				|| (xPerso == x && yPerso == y)) 
				|| xPerso == 0 || xPerso == ile.getTaille() - 1
				|| yPerso == 0 || yPerso == ile.getTaille() - 1
				|| (ile.getParcelle(xPerso, yPerso) instanceof Personnage
						&& ((Personnage)ile.getParcelle(xPerso, yPerso)).getEquipe().getNum() != equipe.getNum())
				|| (ile.getParcelle(xPerso, yPerso) instanceof Personnage
						&& ((Personnage)ile.getParcelle(xPerso, yPerso)).getEquipe().getNum() == equipe.getNum()
						&& this != ile.getParcelle(xPerso, yPerso)
						&& (!this.getPossedeCle()
						&& !this.getPossedeTresor()
						&& !((Personnage)ile.getParcelle(xPerso, yPerso)).getPossedeCle() 
						&& !((Personnage)ile.getParcelle(xPerso, yPerso)).getPossedeTresor()))
				|| (ile.getParcelle(xPerso, yPerso) instanceof Navire
						&& ((Navire) ile.getParcelle(xPerso, yPerso)).getEquipe() != equipe.getNum())
				|| (!ia && ((MouseEvent) evenement).getButton() != MouseEvent.BUTTON1)
				|| (ile.getParcelle(xPerso, yPerso) instanceof Terre
						&& ((Terre) ile.getParcelle(xPerso, yPerso)).getPiege()
						&& this.getEquipe().getNum() == ((Terre) ile.getParcelle(xPerso, yPerso)).getEquipePiege()));

		if ((ile.getParcelle(xPerso, yPerso) instanceof Rocher) && !this.estSurBase(ile)) {
			souleverRocher(plateau, (Rocher) ile.getParcelle(xPerso, yPerso), ia);
			recupererCleTresor(ile, plateau, xPerso, yPerso, ia);
			actionFinie = true;
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
		
		if (!actionFinie) {
			actionFinie = deplacementSimple(ile, plateau, xPerso, yPerso, ia);
		}
		
		if (this.estSurBase(ile)) {
			energie.recup();
		}
		
		return ile;
	}

}
