import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import tps.Plateau;

public class Voleur extends Personnage {

	/** Nom de l'explorateur. */
	private String nom = "voleur";

	/** Constructeur vide de Voleur. */
	public Voleur() {
	}

	/**
	 * Constructeur de Voleur.
	 * 
	 * @param x
	 *            Coordonnée.
	 * @param y
	 *            Coordonnée.
	 * @param equipe
	 *            Numéro de l'équipe à qui il appartient.
	 */
	public Voleur(int x, int y, Equipe equipe) {
		this.setX(x);
		this.setY(y);
		this.setEquipe(equipe);
		this.setEnergieIni(30);
		this.setEnergie(30);
	}

	/**
	 * Renvoie le nom du voleur.
	 * 
	 * @return Retourne un String.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Méthode permettant de voler des ennemis.
	 *
	 * @param p
	 *            Personnage à voler.
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 */
	public void volerObjet(Plateau plateau, Personnage p, boolean ia) {

		if (p.getPossedeCle() == true && this.getEquipe() != p.getEquipe()) {
			p.setPossedeCle(false);
			this.setPossedeCle(true);
			this.getEnergie().volerObjet();
			if (!ia) {
				plateau.println("Vous avez volé la clé !");
			} else {
				plateau.println("Quelqu'un a volé votre clé !");
			}
		} else if (p.getPossedeTresor() == true && this.getEquipe() != p.getEquipe()) {
			p.setPossedeTresor(false);
			this.setPossedeTresor(true);
			this.getEnergie().volerObjet();
			if (!ia) {
				plateau.println("Vous avez volé le trésor !");
			} else {
				plateau.println("Quelqu'un a volé votre trésor !");
			}
		}
		
		try {Thread.sleep(1000);} catch(InterruptedException e){/*gestion de l'erreur*/}

	}

	/**
	 * Chaîne de caractères résumant le voleur.
	 * 
	 * @return Retourne un String.
	 */
	public String toString() {
		return "Equipe " + this.getEquipe().getNum() + " :\nVoleur - " + this.getEnergie().toString();
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
			volerObjet(plateau, (Personnage) ile.getParcelle(xPerso, yPerso), ia);
			actionFinie=true;
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
