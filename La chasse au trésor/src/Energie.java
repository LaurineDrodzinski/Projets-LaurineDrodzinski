/**
 * <b>Energie est la classe repr�sentant l'�nergie d'un personnage.</b>
 * <p>
 * Energie est caract�ris�e par :
 * <ul>
 * <li>Un boolean permettant de savoir si le personnage est en vie</li>
 * <li>Un nombre X repr�sentant l'�nergie initiale du personnage</li>
 * <li>Un nombre de 0 � X repr�sentant l'�nergie actuelle du personnage</li>
 * </ul>
 * </p>
 */
public class Energie {

	/** Energie du personnage. */
	private int energie;

	/** Energie initiale du personnage. */
	private int energieIni;

	/** Energie retir�e au personnage lors de son d�placement. */
	private final int deplacement = -1;

	/** Energie retir�e au personnage lorsqu'il soul�ve un rocher. */
	private final int souleverRocher = -5;

	/** Energie retir�e au personnage lorsqu'il vole un objet. */
	private final int volerObjet = -10;

	/** Energie retir�e au personnage lorsqu'il frappe un autre personnage. */
	private final int frapper = -10;

	/** Energie retir�e au personnage lorsqu'il pose un pi�ge. */
	private final int poser = -10;

	/** Energie retir�e au personnage lorsqu'il enl�ve un pi�ge. */
	private final int enlever = -5;
	
	/** Energie que r�cup�re un personnage lorsqu'il se trouve sur son navire */
	private final int recup = 10;

	/** Personnage en vie(true) ou non(false). */
	private boolean enVie = true;

	/** Constructeur vide de Energie. */
	public Energie() {
	}

	/**
	 * Modifie l'�nergie du personnage.
	 * 
	 * @param valeur
	 *            Valeur de l'�nergie.
	 */
	public void setEnergie(int valeur) {
		if (valeur <= energieIni) {
			energie = valeur;
		} else {
			energie = energieIni;
		}
	}

	/**
	 * Modifie l'�nergie initiale du personnage.
	 * 
	 * @param valeur
	 *            Valeur de l'�nergie initiale.
	 */
	public void setEnergieIni(int valeur) {
		energieIni = valeur;
	}

	/**
	 * Renvoie true si le personnage est en vie, false sinon.
	 * 
	 * @return Retourne un boolean.
	 */
	public boolean getEnVie() {
		return enVie;
	}

	/**
	 * Renvoie l'�nergie du personnage.
	 * 
	 * @return Retourne un int.
	 */
	public int getEnergie() {
		return energie;
	}
	
	/**
	 * Modifie la vei du personnage.
	 */
	public void setEnVie(boolean v) {
		enVie=v;
	}

	/**
	 * Retire � l'�nergie du personnage le co�t correspondant au "d�placement".
	 */
	public void deplacementSimple() {
		if (energie + deplacement > 0) {
			energie += deplacement;
		} else {
			energie=0;
			enVie = false;
		}

	}

	/**
	 * Retire � l'�nergie du personnage le co�t correspondant � l'action
	 * "soulever le rocher".
	 */
	public void souleverRocher() {
		if ((energie + souleverRocher) > 0) {
			energie += souleverRocher;
		} else {
			energie=0;
			enVie = false;
		}
	}
	
	/**
	 * Retire � l'�nergie du personnage le co�t correspondant � l'action
	 * "poser un pi�ge".
	 */
	public void poserPiege() {
		if ((energie + poser) > 0) {
			energie += poser;
		} else {
			energie=0;
			enVie = false;
		}
	}
	
	/**
	 * Retire � l'�nergie du personnage le co�t correspondant � l'action
	 * "enlever un pi�ge".
	 */
	public void enleverPiege() {
		if ((energie + enlever) > 0) {
			energie += enlever;
		} else {
			energie=0;
			enVie = false;
		}
	}

	/**
	 * Retire � l'�nergie du personnage le co�t correspondant au "vol d'objet".
	 */
	public void volerObjet() {
		if ((energie + volerObjet) > 0) {
			energie += volerObjet;
		} else {
			energie=0;
			enVie = false;
		}
	}

	/**
	 * Retire � l'�nergie du personnage le co�t correspondant �
	 * "frapper un personnage".
	 */
	public void frapperPerso() {
		if ((energie + frapper) > 0) {
			energie += frapper;
		} else {
			energie=0;
			enVie = false;
		}
	}

	/**
	 * Augmente l'�nergie du personnage, l'�nergie actuelle ne peut pas d�passer
	 * l'�nergie initiale.
	 */
	public void recup() {
		if ((energie + recup) >= energieIni) {
			energie = energieIni;
		} else {
			energie += recup;
		}
	}

	/**
	 * Cha�ne de caract�res incluant la valeur de l'�nergie actuelle.
	 * 
	 * @return Retourne un String.
	 */
	public String toString() {
		return "Energie : " + energie;
	}
}
