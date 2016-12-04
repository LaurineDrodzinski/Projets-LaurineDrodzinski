/**
 * <b>Navire est la classe repr�sentant le navire d'un joueur. C'est un type de
 * Parcelle</b>
 * <p>
 * Elle est caract�ris�e par :
 * <ul>
 * <li>Un int concernant le num�ro de l'�quipe</li>
 * </ul>
 * </p>
 * <p>
 * Il est possible de regarder la valeur de son attribut (�quipe).
 * </p>
 * 
 * @see Parcelle
 */
public class Navire extends Parcelle {

	/** Cet attribut correspond au num�ro de l'�quipe. */
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
	 *            Coordonn�e.
	 * @param y
	 *            Coordonn�e.
	 * @param e
	 *            Num�ro de l'�quipe � qui il appartient.
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