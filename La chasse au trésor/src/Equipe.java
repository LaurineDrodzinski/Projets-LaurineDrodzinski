import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * <b>Equipe est la classe représentant l'équipe d'un joueur.</b>
 * <p>
 * Une équipe est caractérisée par :
 * <ul>
 * <li>Un numéro</li>
 * <li>Un nom d'équipe</li>
 * <li>Une liste contenant les différents personnages de l'équipe</li>
 * <li>Une vue de l'ile</li>
 * </ul>
 * </p>
 */
public class Equipe {

	/** Numéro de l'équipe. */
	private int num;

	/** Nom de l'équipe. */
	private String nom;

	/** Liste des différents personnages de l'équipe. */
	private ArrayList<Personnage> team = new ArrayList<Personnage>();

	/** Vue de l'ile */
	private Vue v;

	/** Constructeur vide de Equipe. */
	public Equipe() {
	}

	/**
	 * Constructeur de Equipe.
	 * 
	 * @param nom
	 *            Nom voulu pour l'équipe.
	 * @param numero
	 *            Numéro de l'équipe.
	 */
	public Equipe(String nom, int numero, Ile ile) {
		this.nom = nom;
		num = numero;
		v = new Vue(ile.getTaille(), team);
	}

	/**
	 * Renvoie le numéro de l'équipe.
	 * 
	 * @return Retourne un int.
	 */
	public int getNum() {
		return num;
	}

	/**
	 * Renvoie le nom de l'équipe.
	 * 
	 * @return Retourne un String.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Renvoie la liste des personnages de l'équipe.
	 * 
	 * @return Retourne une ArrayList de Personnage.
	 */
	public ArrayList<Personnage> getTeam() {
		return team;
	}

	/**
	 * Renvoie la vue de l'équipe sous forme d'un tableau de booléens.
	 * 
	 * @return Retourne un tableau de boolean à deux dimensions.
	 */
	public boolean[][] getBrouillard() {
		return v.getBrouillard();
	}

	/**
	 * Renvoie la vue de l'équipe.
	 * 
	 * @return Retourne une Vue.
	 */
	public Vue getVue() {
		return v;
	}

	/**
	 * Modifie le numéro de l'équipe.
	 * 
	 * @param numero
	 *            Numéro voulu.
	 */
	public void setNum(int numero) {
		this.num = numero;
	}

	/**
	 * Modifie le nom de l'équipe.
	 * 
	 * @param nom
	 *            Nom voulu.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Modifie la liste des personnages de l'équipe.
	 * 
	 * @param team
	 *            Liste voulue.
	 */
	public void setTeam(ArrayList<Personnage> team) {
		this.team = team;
	}

	/**
	 * Permet d'ajouter un personnage à l'équipe (seulement si elle ne contient
	 * pas déjà X personnages).
	 * 
	 * @return Retourne un booléen, qui servira à déterminer si l'ajout du
	 *         personnage était possible ou non.
	 * 
	 * @param p
	 *            Correspond au personnage que l'on veut ajouter (Ex: un
	 *            explorateur, un voleur ..).
	 * @param sizeteam
	 *            Correspond à la taille de l'équipe.
	 * 
	 * @see Equipe#team
	 * @see Personnage
	 */
	public boolean ajouterPerso(Personnage p, int sizeteam) {
		if (team.size() < sizeteam) {
			team.add(p);
			v.setTeam(team);
			return true;
		} else {
			v.setTeam(team);
			return false;
		}
	}

	/**
	 * Permet de savoir si l'équipe a gagné ou non. Une équipe a gagné si l'un
	 * de ses personnages rejoint son bateau en possession du trésor.
	 * 
	 * @return Retourne un booléen.
	 * 
	 * @param ile
	 *            Correspond à l'ile de la partie en cours.
	 * 
	 * @see Ile
	 * @see Personnage
	 */
	public boolean teamGoToWin(Ile ile) {
		for (Personnage p : team) {
			if (p.getPossedeTresor() && p.estSurBase(ile)) {
				JOptionPane.showMessageDialog(null, "L'équipe " + num + " a gagné !!");
				return true;
			}
		}
		return false;
	}

	/**
	 * Permet de savoir si l'équipe a perdu ou non. Une équipe a perdu si tout
	 * ses personnages sont morts.
	 * 
	 * @return Retourne un booléen.
	 * 
	 * @param ile
	 *            Correspond à l'ile de la partie en cours.
	 * 
	 * @see Ile
	 * @see Personnage
	 */
	public boolean teamGoToLose(Ile ile) {
		if (team.size() == 0) {
			JOptionPane.showMessageDialog(null, "L'équipe " + num + " a perdue...");
			return true;
		}
		return false;
	}
}