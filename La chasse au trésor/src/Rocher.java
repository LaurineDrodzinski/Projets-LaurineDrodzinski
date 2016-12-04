
/**
 * <b>Rocher est un type de Parcelle</b>
 * <p>
 * Le rocher est caractérisé par :
 * <ul>
 * <li>Un objet Cle</li>
 * <li>Un objet Coffre</li>
 * </ul>
 * </p>
 * 
 * @see Parcelle
 */
public class Rocher extends Parcelle {

	/**
	 * Représente la clé. Vaut false si il n'y a pas de coffre sous le rocher.
	 */
	private boolean cle = false;

	/**
	 * Représente la découverte de la clé. Vaut false si la clé n'a pas encore
	 * été découverte.
	 */
	private boolean decouverteCle = false;

	/**
	 * Représente le coffre. Vaut null si il n'y a pas de coffre sous le rocher.
	 * 
	 * @see Coffre
	 */
	private Coffre coffre = null;

	/**
	 * Représente la découverte du coffre. Vaut false si le coffre n'a pas
	 * encore été découvert.
	 */
	private boolean decouverteCoffreEquipe1 = false;
	
	/**
	 * Représente la découverte du coffre. Vaut false si le coffre n'a pas
	 * encore été découvert.
	 */
	private boolean decouverteCoffreEquipe2 = false;

	/** Constructeur vide de Rocher */
	public Rocher() {
	}

	/**
	 * Constructeur de Rocher. En fonction des boolean passés en paramètre il y
	 * aura instanciation ou non d'une clé ou d'un coffre.
	 * 
	 * @param presenceCle
	 *            Si true alors il y a une clé
	 * 
	 * @param presenceCoffre
	 *            Si true alors il y a un coffre
	 */
	public Rocher(boolean presenceCle, boolean presenceCoffre) {
		if (presenceCle) {
			cle = true;
		} else if (presenceCoffre) {
			coffre = new Coffre();
		}
	}

	/**
	 * Retourne un boolean correspondant à la présence de la clé sous le rocher.
	 * 
	 * @return Un boolean
	 * 
	 * @see Rocher#cle
	 */
	public boolean getPresenceCle() {
		return cle;
	}

	/**
	 * Retourne un boolean correspondant à la présence du coffre sous le rocher.
	 * 
	 * @return Un boolean
	 * 
	 * @see Rocher#coffre
	 */
	public boolean getPresenceCoffre() {
		if (coffre != null) {
			return true;
		}
		return false;
	}

	/**
	 * Retourne le coffre.
	 * 
	 * @return Un "Coffre"
	 * 
	 * @see Rocher#coffre
	 */
	public Coffre getCoffre() {
		return coffre;
	}

	/**
	 * Retourne un boolean correspondant à la découverte de la clé.
	 * 
	 * @see Rocher#decouverteCle
	 */
	public boolean getDecouverteCle() {
		return decouverteCle;
	}

	/**
	 * Retourne un boolean correspondant à la découverte du coffre.
	 * 
	 * @see Rocher#decouverteCoffre
	 */
	public boolean getDecouverteCoffre(int equipe) {
		if(equipe==1){
			return decouverteCoffreEquipe1;
		}else{
			return decouverteCoffreEquipe2;
		}
	}

	/**
	 * Rammasse la clé si elle est présente.
	 * 
	 * @return Retourne un boolean, qui servira à déterminer si le ramassage de
	 *         la clé était possible ou non.
	 */
	public boolean prendreCle() {
		if (cle) {
			cle = false;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Decouvre la clé et donc passe l'attribut decouverteCle à true.
	 * 
	 * @see Rocher#decouverteCle
	 */
	public void decouvreCle() {
		decouverteCle = true;
	}

	/**
	 * Decouvre le coffre et donc passe l'attribut decouverteCoffre à true.
	 * 
	 * @see Rocher#decouverteCoffre
	 */
	public void decouvreCoffre(int equipe) {
		if(equipe==1){
			decouverteCoffreEquipe1=true;
		}else{
			decouverteCoffreEquipe2=true;
		}
	}
	

}
