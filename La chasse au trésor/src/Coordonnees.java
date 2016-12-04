/**
 * <b>Coordonnees est une classe utilisée lors de la vérification de
 * l'accessibilité aux rochers.</b>
 * <p>
 * Coordonnees est caractérisée par :
 * <ul>
 * <li>Une coordonnée x</li>
 * <li>Une coordonnée y</li>
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
