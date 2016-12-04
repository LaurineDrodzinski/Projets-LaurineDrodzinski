/**
 * <b>Navire est la classe représentant le navire d'un joueur. C'est un type de
 * Parcelle</b>
 * <p>
 * Elle est caractérisée par :
 * <ul>
 * <li>Un int concernant le numéro de l'équipe</li>
 * </ul>
 * </p>
 * <p>
 * Il est possible de regarder la valeur de son attribut (équipe).
 * </p>
 * 
 * @see Parcelle
 */
public class Navire extends Parcelle {

	/** Cet attribut correspond au numéro de l'équipe. */
	private int equipe;

	private int x;
	private int y;

	/** Constructeur vide de Navire */
	public Navire() {
	}

	/**
	 * Constructeur de Navire.
	 * 
	 * @param x
	 *            Coordonnée.
	 * @param y
	 *            Coordonnée.
	 * @param e
	 *            Numéro de l'équipe à qui il appartient.
	 */
	public Navire(int e, int x, int y) {
		if (e == 1 || e == 2) {
			equipe = e;
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * Retourne la valeur de l'attribut "equipe"
	 * 
	 * @return Retourne un int.
	 * 
	 * @see Navire#equipe
	 */
	public int getEquipe() {
		return equipe;
	}

	/**
	 * Retourne la position du navire.
	 * 
	 * @return Retourne un tableau de int.
	 */
	public int[] getPos() {
		return new int[] { x, y };
	}

}