
/**
 * <b>Terre est un type de Parcelle</b>
 * <p>
 * Elle correspond aux zones de sable. Elle peut parfois contenir une cl� ou un
 * tr�sor, si l'un des personnages est mort sur celle-ci alors qu'il
 * transportait un objet.
 * </p>
 * 
 * @see Parcelle
 */
public class Terre extends Parcelle {

	/** Repr�sente la pr�sence du tr�sor. */
	private boolean tresor;

	/** Repr�sente la pr�sence de la cl�. */
	private boolean cle;

	/** Repr�sente la pr�sence d'un pi�ge. */
	private boolean piege;

	/** Repr�sente l'�quipe qui a pos� le pi�ge, s'il y a un pi�ge. */
	private int equipePiege;

	/** Constructeur vide de Terre */
	public Terre() {
		tresor = false;
		cle = false;
		piege = false;
		equipePiege = 0;
	}

	/**
	 * Retourne un boolean correspondant � la pr�sence du tr�sor.
	 * 
	 * @return Un boolean
	 */
	public boolean getTresor() {
		return tresor;
	}

	/**
	 * Retourne un boolean correspondant � la pr�sence de la cl�.
	 * 
	 * @return Un boolean
	 */
	public boolean getCle() {
		return cle;
	}

	/**
	 * Retourne un boolean correspondant � la pr�sence d'un pi�ge.
	 * 
	 * @return Un boolean
	 */
	public boolean getPiege() {
		return piege;
	}

	/**
	 * Retourne un int correspondant � l'�quipe qui a pos� le pi�ge.
	 * 
	 * @return Un int
	 */
	public int getEquipePiege() {
		return equipePiege;
	}

	/**
	 * Modifie la pr�sence du tr�sor.
	 * 
	 * @param t
	 *            Pr�sence du tr�sor.
	 */
	public void setTresor(boolean t) {
		tresor = t;
	}

	/**
	 * Modifie la pr�sence de la cl�.
	 * 
	 * @param c
	 *            Pr�sence de la cl�.
	 */
	public void setCle(boolean c) {
		cle = c;
	}

	/**
	 * Modifie la pr�sence d'un pi�ge.
	 * 
	 * @param c
	 *            Pr�sence d'un pi�ge.
	 * @param num
	 *            Equipe qui a pos� le pi�ge.
	 */
	public void setPiege(boolean p, int num) {
		piege = p;
		equipePiege = num;
	}

}
