/**
 * <b>Energie est la classe représentant l'énergie d'un personnage.</b>
 * <p>
 * Energie est caractérisée par :
 * <ul>
 * <li>Un boolean permettant de savoir si le personnage est en vie</li>
 * <li>Un nombre X représentant l'énergie initiale du personnage</li>
 * <li>Un nombre de 0 à X représentant l'énergie actuelle du personnage</li>
 * </ul>
 * </p>
 */
public class Energie {

	/** Energie du personnage. */
	private int energie;

	/** Energie initiale du personnage. */
	private int energieIni;

	/** Energie retirée au personnage lors de son déplacement. */
	private final int deplacement = -1;

	/** Energie retirée au personnage lorsqu'il soulève un rocher. */
	private final int souleverRocher = -5;

	/** Energie retirée au personnage lorsqu'il vole un objet. */
	private final int volerObjet = -10;

	/** Energie retirée au personnage lorsqu'il frappe un autre personnage. */
	private final int frapper = -10;

	/** Energie retirée au personnage lorsqu'il pose un piège. */
	private final int poser = -10;

	/** Energie retirée au personnage lorsqu'il enlève un piège. */
	private final int enlever = -5;
	
	/** Energie que récupère un personnage lorsqu'il se trouve sur son navire */
	private final int recup = 10;

	/** Personnage en vie(true) ou non(false). */
	private boolean enVie = true;

	/** Constructeur vide de Energie. */
	public Energie() {
	}

	/**
	 * Modifie l'énergie du personnage.
	 * 
	 * @param valeur
	 *            Valeur de l'énergie.
	 */
	public void setEnergie(int valeur) {
		if (valeur <= energieIni) {
			energie = valeur;
		} else {
			energie = energieIni;
		}
	}

	/**
	 * Modifie l'énergie initiale du personnage.
	 * 
	 * @param valeur
	 *            Valeur de l'énergie initiale.
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
	 * Renvoie l'énergie du personnage.
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
	 * Retire à l'énergie du personnage le coût correspondant au "déplacement".
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
	 * Retire à l'énergie du personnage le coût correspondant à l'action
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
	 * Retire à l'énergie du personnage le coût correspondant à l'action
	 * "poser un piège".
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
	 * Retire à l'énergie du personnage le coût correspondant à l'action
	 * "enlever un piège".
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
	 * Retire à l'énergie du personnage le coût correspondant au "vol d'objet".
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
	 * Retire à l'énergie du personnage le coût correspondant à
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
	 * Augmente l'énergie du personnage, l'énergie actuelle ne peut pas dépasser
	 * l'énergie initiale.
	 */
	public void recup() {
		if ((energie + recup) >= energieIni) {
			energie = energieIni;
		} else {
			energie += recup;
		}
	}

	/**
	 * Chaîne de caractères incluant la valeur de l'énergie actuelle.
	 * 
	 * @return Retourne un String.
	 */
	public String toString() {
		return "Energie : " + energie;
	}
}
