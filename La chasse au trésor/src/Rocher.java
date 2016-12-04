
/**
 * <b>Rocher est un type de Parcelle</b>
 * <p>
 * Le rocher est caract�ris� par :
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
	 * Repr�sente la cl�. Vaut false si il n'y a pas de coffre sous le rocher.
	 */
	private boolean cle = false;

	/**
	 * Repr�sente la d�couverte de la cl�. Vaut false si la cl� n'a pas encore
	 * �t� d�couverte.
	 */
	private boolean decouverteCle = false;

	/**
	 * Repr�sente le coffre. Vaut null si il n'y a pas de coffre sous le rocher.
	 * 
	 * @see Coffre
	 */
	private Coffre coffre = null;

	/**
	 * Repr�sente la d�couverte du coffre. Vaut false si le coffre n'a pas
	 * encore �t� d�couvert.
	 */
	private boolean decouverteCoffreEquipe1 = false;
	
	/**
	 * Repr�sente la d�couverte du coffre. Vaut false si le coffre n'a pas
	 * encore �t� d�couvert.
	 */
	private boolean decouverteCoffreEquipe2 = false;

	/** Constructeur vide de Rocher */
	public Rocher() {
	}

	/**
	 * Constructeur de Rocher. En fonction des boolean pass�s en param�tre il y
	 * aura instanciation ou non d'une cl� ou d'un coffre.
	 * 
	 * @param presenceCle
	 *            Si true alors il y a une cl�
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
	 * Retourne un boolean correspondant � la pr�sence de la cl� sous le rocher.
	 * 
	 * @return Un boolean
	 * 
	 * @see Rocher#cle
	 */
	public boolean getPresenceCle() {
		return cle;
	}

	/**
	 * Retourne un boolean correspondant � la pr�sence du coffre sous le rocher.
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
	 * Retourne un boolean correspondant � la d�couverte de la cl�.
	 * 
	 * @see Rocher#decouverteCle
	 */
	public boolean getDecouverteCle() {
		return decouverteCle;
	}

	/**
	 * Retourne un boolean correspondant � la d�couverte du coffre.
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
	 * Rammasse la cl� si elle est pr�sente.
	 * 
	 * @return Retourne un boolean, qui servira � d�terminer si le ramassage de
	 *         la cl� �tait possible ou non.
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
	 * Decouvre la cl� et donc passe l'attribut decouverteCle � true.
	 * 
	 * @see Rocher#decouverteCle
	 */
	public void decouvreCle() {
		decouverteCle = true;
	}

	/**
	 * Decouvre le coffre et donc passe l'attribut decouverteCoffre � true.
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
