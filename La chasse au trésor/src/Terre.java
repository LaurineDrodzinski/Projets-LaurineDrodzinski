
/**
 * <b>Terre est un type de Parcelle</b>
 * <p>
 * Elle correspond aux zones de sable. Elle peut parfois contenir une clé ou un
 * trésor, si l'un des personnages est mort sur celle-ci alors qu'il
 * transportait un objet.
 * </p>
 * 
 * @see Parcelle
 */
public class Terre extends Parcelle {

	/** Représente la présence du trésor. */
	private boolean tresor;

	/** Représente la présence de la clé. */
	private boolean cle;

	/** Représente la présence d'un piège. */
	private boolean piege;

	/** Représente l'équipe qui a posé le piège, s'il y a un piège. */
	private int equipePiege;

	/** Constructeur vide de Terre */
	public Terre() {
		tresor = false;
		cle = false;
		piege = false;
		equipePiege = 0;
	}

	/**
	 * Retourne un boolean correspondant à la présence du trésor.
	 * 
	 * @return Un boolean
	 */
	public boolean getTresor() {
		return tresor;
	}

	/**
	 * Retourne un boolean correspondant à la présence de la clé.
	 * 
	 * @return Un boolean
	 */
	public boolean getCle() {
		return cle;
	}

	/**
	 * Retourne un boolean correspondant à la présence d'un piège.
	 * 
	 * @return Un boolean
	 */
	public boolean getPiege() {
		return piege;
	}

	/**
	 * Retourne un int correspondant à l'équipe qui a posé le piège.
	 * 
	 * @return Un int
	 */
	public int getEquipePiege() {
		return equipePiege;
	}

	/**
	 * Modifie la présence du trésor.
	 * 
	 * @param t
	 *            Présence du trésor.
	 */
	public void setTresor(boolean t) {
		tresor = t;
	}

	/**
	 * Modifie la présence de la clé.
	 * 
	 * @param c
	 *            Présence de la clé.
	 */
	public void setCle(boolean c) {
		cle = c;
	}

	/**
	 * Modifie la présence d'un piège.
	 * 
	 * @param c
	 *            Présence d'un piège.
	 * @param num
	 *            Equipe qui a posé le piège.
	 */
	public void setPiege(boolean p, int num) {
		piege = p;
		equipePiege = num;
	}

}
