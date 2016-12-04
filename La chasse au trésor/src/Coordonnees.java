/**
 * <b>Coordonnees est une classe utilis�e lors de la v�rification de
 * l'accessibilit� aux rochers.</b>
 * <p>
 * Coordonnees est caract�ris�e par :
 * <ul>
 * <li>Une coordonn�e x</li>
 * <li>Une coordonn�e y</li>
 * </ul>
 * </p>
 * 
 * @see Ile#toutEstAccessible(int[])
 * @see Ile#accessible(java.util.List, boolean[][])
 */
public class Coordonnees {
	private int x;
	private int y;

	/** Constructeur vide de Coordonnees. */
	public Coordonnees() {
	}

	/**
	 * Constructeur de Coordonnees.
	 * 
	 * @param x
	 * @param y
	 */
	public Coordonnees(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Retourne la valeur de l'attribut "x"
	 * 
	 * @return Retourne un entier.
	 * 
	 * @see Coordonnees#x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retourne la valeur de l'attribut "y"
	 * 
	 * @return Retourne un entier.
	 * 
	 * @see Coordonnees#y
	 */
	public int getY() {
		return y;
	}
}
