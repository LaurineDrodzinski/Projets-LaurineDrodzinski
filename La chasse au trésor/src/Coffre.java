/**
 * <b>Coffre est la classe représentant le coffre contenant le trésor. Le coffre
 * se situe sous un Rocher.</b>
 * <p>
 * Le coffre est caractérisé par :
 * <ul>
 * <li>Un boolean permettant de savoir si le trésor a déjà été ramassé</li>
 * </ul>
 * </p>
 * <p>
 * Il est possible de ramasser le trésor situé dans le coffre et de regarder la
 * valeur de son attribut (trésor).
 * </p>
 * 
 * @see Rocher
 */
public class Coffre {

	/**
	 * Cet attribut détermine si le trésor est toujours présent dans le coffre.
	 */
	private boolean tresor = true;

	/**
	 * Rammasse le trésor si le trésor est toujours présent.
	 * 
	 * @return Retourne un booléen, qui servira à déterminer si le ramassage du
	 *         trésor était possible ou non.
	 * 
	 * @see Coffre#tresor
	 */
	public boolean prendreTresor() {
		if (tresor) {
			tresor = false;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Retourne la valeur de l'attribut "tresor" (si il est toujours présent ou
	 * non).
	 * 
	 * @return Retourne un booléen.
	 * 
	 * @see Coffre#tresor
	 */
	public boolean getTresor() {
		return tresor;
	}
}
