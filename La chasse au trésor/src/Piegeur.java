import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import tps.Plateau;

public class Piegeur extends Personnage {

	/** Nom du Piegeur. */
	private String nom = "piégeur";

	/** Constructeur vide de Piegeur */
	public Piegeur() {
	}

	/**
	 * Constructeur de Piegeur.
	 * 
	 * @param x
	 *            Coordonnée.
	 * @param y
	 *            Coordonnée.
	 * @param equipe
	 *            Numéro de l'équipe à qui il appartient.
	 */
	public Piegeur(int x, int y, Equipe equipe) {
		this.setX(x);
		this.setY(y);
		this.setEquipe(equipe);
		this.setEnergieIni(30);
		this.setEnergie(30);
	}

	/**
	 * Renvoie le nom du piégeur.
	 * 
	 * @return Retourne un String.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Méthode permettant de poser un piège. Si la clé ou le trésor se trouve là
	 * où le piège devrait être posé, ce n'est pas possible. Sinon, le piège est
	 * posé.
	 * 
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param x
	 *            Coordonnée x du piège.
	 * @param y
	 *            Coordonnée y du piège.
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 */
	public void poserPiege(Plateau plateau, int x, int y, Ile ile, boolean ia) {
		if (!((Terre) ile.getParcelle(x, y)).getCle() && !((Terre) ile.getParcelle(x, y)).getTresor()) {
			((Terre) ile.getParcelle(x, y)).setPiege(true, this.getEquipe().getNum());
			this.getEnergie().poserPiege();
			if (!ia) {
				plateau.println("Vous avez posé un piège!");
				try {Thread.sleep(1000);} catch(InterruptedException e){/*gestion de l'erreur*/}
			}
		}
	}

	/**
	 * Méthode permettant d'enlever un piège.
	 * 
	 * @param plateau
	 *            Plateau de la partie en cours.
	 * @param x
	 *            Coordonnée x du piège.
	 * @param y
	 *            Coordonnée y du piège.
	 * @param ile
	 *            Ile de la partie en cours.
	 * @param ia
	 *            Est-ce que le personnage est une ia.
	 */
	public void enleverPiege(Plateau plateau, int x, int y, Ile ile, boolean ia) {
		if (((Terre) ile.getParcelle(x, y)).getPiege()) {
			((Terre) ile.getParcelle(x, y)).setPiege(false, 0);
			this.getEnergie().enleverPiege();
			if (!ia) {
				plateau.println("Vous avez retiré votre piège !");
				try {Thread.sleep(900);} catch(InterruptedException e){/*gestion de l'erreur*/}
			}
		}
	}

	/**
	 * Chaîne de caractères résumant le piégeur.
	 * 
	 * @return Retourne un String.
	 */
	public String toString() {
		return "Equipe " + this.getEquipe().getNum() + " :\nPiégeur - " + this.getEnergie().toString();
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
		int p=-1;//Pour l'ia, pour choisir s'il pose un piège ou se déplace

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
						&& ((Personnage)ile.getParcelle(xPerso, yPerso)).getEquipe().getNum() != equipe.getNum())
				|| (ile.getParcelle(xPerso, yPerso) instanceof Personnage
						&& ((Personnage)ile.getParcelle(xPerso, yPerso)).getEquipe().getNum() == equipe.getNum()
						&& this != ile.getParcelle(xPerso, yPerso)
						&& (!this.getPossedeCle()
						&& !this.getPossedeTresor())
						&& !((Personnage)ile.getParcelle(xPerso, yPerso)).getPossedeCle() 
						&& !((Personnage)ile.getParcelle(xPerso, yPerso)).getPossedeTresor())
				|| (ile.getParcelle(xPerso, yPerso) instanceof Navire
						&& ((Navire) ile.getParcelle(xPerso, yPerso)).getEquipe() != equipe.getNum())
				|| (!ia && (((MouseEvent) evenement).getButton() != MouseEvent.BUTTON1
						&& ((MouseEvent) evenement).getButton() != MouseEvent.BUTTON3))
				|| ((!ia && ((MouseEvent) evenement).getButton() == MouseEvent.BUTTON1)
						&& ile.getParcelle(xPerso, yPerso) instanceof Terre
						&& ((Terre) ile.getParcelle(xPerso, yPerso)).getPiege()
						&& this.getEquipe().getNum() == ((Terre) ile.getParcelle(xPerso, yPerso)).getEquipePiege())
				|| ((!ia && ((MouseEvent) evenement).getButton() == MouseEvent.BUTTON3)
						&& this.estSurBase(ile)));

		if (p == 0 || (!ia && ((MouseEvent) evenement).getButton() == MouseEvent.BUTTON1)) {
			
			if ((ile.getParcelle(xPerso, yPerso) instanceof Rocher)) {
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
			
		} else if ((!ia && ((MouseEvent) evenement).getButton() == MouseEvent.BUTTON3) || p == 1) {
			if (ile.getParcelle(xPerso, yPerso) instanceof Terre && this instanceof Piegeur) {
				if (!((Terre) ile.getParcelle(xPerso, yPerso)).getPiege() || (((Terre) ile.getParcelle(xPerso, yPerso)).getPiege()
						&& ((Terre) ile.getParcelle(xPerso, yPerso)).getEquipePiege() != this.getEquipe().getNum())) {
					poserPiege(plateau, xPerso, yPerso, ile, ia);
				} else if (((Terre) ile.getParcelle(xPerso, yPerso)).getPiege()
						&& ((Terre) ile.getParcelle(xPerso, yPerso)).getEquipePiege() == this.getEquipe().getNum()) {
					enleverPiege(plateau, xPerso, yPerso, ile, ia);
				}
			}
		}

		return ile;
	}

}