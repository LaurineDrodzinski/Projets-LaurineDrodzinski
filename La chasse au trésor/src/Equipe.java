import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * <b>Equipe est la classe repr�sentant l'�quipe d'un joueur.</b>
 * <p>
 * Une �quipe est caract�ris�e par :
 * <ul>
 * <li>Un num�ro</li>
 * <li>Un nom d'�quipe</li>
 * <li>Une liste contenant les diff�rents personnages de l'�quipe</li>
 * <li>Une vue de l'ile</li>
 * </ul>
 * </p>
 */
public class Equipe {

	/** Num�ro de l'�quipe. */
	private int num;

	/** Nom de l'�quipe. */
	private String nom;

	/** Liste des diff�rents personnages de l'�quipe. */
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
	 *            Nom voulu pour l'�quipe.
	 * @param numero
	 *            Num�ro de l'�quipe.
	 */
	public Equipe(String nom, int numero, Ile ile) {
		this.nom = nom;
		num = numero;
		v = new Vue(ile.getTaille(), team);
	}

	/**
	 * Renvoie le num�ro de l'�quipe.
	 * 
	 * @return Retourne un int.
	 */
	public int getNum() {
		return num;
	}

	/**
	 * Renvoie le nom de l'�quipe.
	 * 
	 * @return Retourne un String.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Renvoie la liste des personnages de l'�quipe.
	 * 
	 * @return Retourne une ArrayList de Personnage.
	 */
	public ArrayList<Personnage> getTeam() {
		return team;
	}

	/**
	 * Renvoie la vue de l'�quipe sous forme d'un tableau de bool�ens.
	 * 
	 * @return Retourne un tableau de boolean � deux dimensions.
	 */
	public boolean[][] getBrouillard() {
		return v.getBrouillard();
	}

	/**
	 * Renvoie la vue de l'�quipe.
	 * 
	 * @return Retourne une Vue.
	 */
	public Vue getVue() {
		return v;
	}

	/**
	 * Modifie le num�ro de l'�quipe.
	 * 
	 * @param numero
	 *            Num�ro voulu.
	 */
	public void setNum(int numero) {
		this.num = numero;
	}

	/**
	 * Modifie le nom de l'�quipe.
	 * 
	 * @param nom
	 *            Nom voulu.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Modifie la liste des personnages de l'�quipe.
	 * 
	 * @param team
	 *            Liste voulue.
	 */
	public void setTeam(ArrayList<Personnage> team) {
		this.team = team;
	}

	/**
	 * Permet d'ajouter un personnage � l'�quipe (seulement si elle ne contient
	 * pas d�j� X personnages).
	 * 
	 * @return Retourne un bool�en, qui servira � d�terminer si l'ajout du
	 *         personnage �tait possible ou non.
	 * 
	 * @param p
	 *            Correspond au personnage que l'on veut ajouter (Ex: un
	 *            explorateur, un voleur ..).
	 * @param sizeteam
	 *            Correspond � la taille de l'�quipe.
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
	 * Permet de savoir si l'�quipe a gagn� ou non. Une �quipe a gagn� si l'un
	 * de ses personnages rejoint son bateau en possession du tr�sor.
	 * 
	 * @return Retourne un bool�en.
	 * 
	 * @param ile
	 *            Correspond � l'ile de la partie en cours.
	 * 
	 * @see Ile
	 * @see Personnage
	 */
	public boolean teamGoToWin(Ile ile) {
		for (Personnage p : team) {
			if (p.getPossedeTresor() && p.estSurBase(ile)) {
				JOptionPane.showMessageDialog(null, "L'�quipe " + num + " a gagn� !!");
				return true;
			}
		}
		return false;
	}

	/**
	 * Permet de savoir si l'�quipe a perdu ou non. Une �quipe a perdu si tout
	 * ses personnages sont morts.
	 * 
	 * @return Retourne un bool�en.
	 * 
	 * @param ile
	 *            Correspond � l'ile de la partie en cours.
	 * 
	 * @see Ile
	 * @see Personnage
	 */
	public boolean teamGoToLose(Ile ile) {
		if (team.size() == 0) {
			JOptionPane.showMessageDialog(null, "L'�quipe " + num + " a perdue...");
			return true;
		}
		return false;
	}
}