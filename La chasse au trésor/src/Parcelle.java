
/**
 * <b>Parcelle est le composant du plateau de jeu. Elle permet de stocker
 * différents objets, comme un rocher, un navire, du vide etc.</b>
 * <p>
 * La parcelle est caractérisée par :
 * <ul>
 * <li>Un objet (rocher,navire,vide...)</li>
 * </ul>
 * </p>
 * 
 * @see Rocher
 * @see Navire
 * @see Vide
 * @see Ile
 */
public class Parcelle {
	/**
	 * Un objet qui peut prendre différents types.
	 * 
	 * @see Rocher
	 * @see Navire
	 * @see Vide
	 * @see Parcelle#getParcelle()
	 * @see Parcelle#setParcelle(Object)
	 * @see Parcelle#removeParcelle()
	 */
	private Object obj;

	/**
	 * Constructeur vide de Parcelle.
	 * <p>
	 * Chaque parcelle est au départ initialisée avec le type Vide (qui
	 * correspond à la terre, ou à la mer)
	 * </p>
	 * 
	 * @see Vide
	 * @see Parcelle#obj
	 */
	public Parcelle() {
		obj = null;
	}

	/**
	 * Retourne l'objet que contient la parcelle.
	 * 
	 * @return Retourne un Object.
	 * 
	 * @see Parcelle#obj
	 */
	public Object getParcelle() {
		return obj;
	}

	/**
	 * Modifie l'objet contenu dans la parcelle.
	 * 
	 * @param objet
	 *            Objet voulu (rocher,navire,vide...)
	 * 
	 * @see Parcelle#obj
	 */
	public void setParcelle(Object objet) {
		obj = objet;
	}

	/**
	 * Remplace l'objet contenu dans la parcelle par un objet de type Vide.
	 * 
	 * @see Parcelle#obj
	 * @see Vide
	 */
	public void removeParcelle() {
		obj = new Terre();
	}

	/**
	 * Regarde si le débarquement du navire est possible (pas entouré de
	 * rochers).
	 * 
	 * @return Retourne un boolean.
	 */
	public boolean debarquementPossible(Parcelle[][] ile, int x, int y, int taille) {

		if (x == 1 && (y != 0 && y != taille - 1)) {
			if ((ile[x + 1][y].getParcelle() instanceof Rocher || ile[x + 1][y].getParcelle() instanceof Navire)
					&& (ile[x][y - 1].getParcelle() instanceof Rocher || ile[x][y - 1].getParcelle() instanceof Navire
							|| y == 2)
					&& (ile[x][y + 1].getParcelle() instanceof Rocher || ile[x][y + 1].getParcelle() instanceof Navire
							|| y == taille - 2)) {
				return false;
			}
		} else if (x == taille - 2 && (y != 0 && y != taille - 1)) {
			if ((ile[x - 1][y].getParcelle() instanceof Rocher || ile[x - 1][y].getParcelle() instanceof Navire)
					&& (ile[x][y - 1].getParcelle() instanceof Rocher || ile[x][y - 1].getParcelle() instanceof Navire
							|| y == 2)
					&& (ile[x][y + 1].getParcelle() instanceof Rocher || ile[x][y + 1].getParcelle() instanceof Navire
							|| y == taille - 2)) {
				return false;
			}
		} else if (y == 1 && (x != 0 && x != taille - 1)) {
			if ((ile[x][y + 1].getParcelle() instanceof Rocher || ile[x][y + 1].getParcelle() instanceof Navire)
					&& (ile[x - 1][y].getParcelle() instanceof Rocher || ile[x - 1][y].getParcelle() instanceof Navire
							|| x == 2)
					&& (ile[x + 1][y].getParcelle() instanceof Rocher || ile[x + 1][y].getParcelle() instanceof Navire
							|| x == taille - 2)) {
				return false;
			}
		} else if (y == taille - 2 && (x != 0 && x != taille - 1)) {
			if ((ile[x][y - 1].getParcelle() instanceof Rocher || ile[x][y - 1].getParcelle() instanceof Navire)
					&& (ile[x - 1][y].getParcelle() instanceof Rocher || ile[x - 1][y].getParcelle() instanceof Navire
							|| x == 2)
					&& (ile[x + 1][y].getParcelle() instanceof Rocher || ile[x + 1][y].getParcelle() instanceof Navire
							|| x == taille - 2)) {
				return false;
			}
		}
		if (ile[x][y].getParcelle() instanceof Rocher || ile[x][y].getParcelle() instanceof Navire) {
			return false;
		}

		return true;

	}

}
