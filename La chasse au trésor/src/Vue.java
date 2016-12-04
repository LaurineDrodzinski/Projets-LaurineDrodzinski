import java.util.ArrayList;

/**
 * <b>Vue est la classe repr�sentant la vue de l'ile, cach�e ou non par du brouillard. Une vue par �quipe.</b>
 * <p>
 * Une vue est caract�ris�e par :
 * <ul>
 * <li>Un tableau de boolean permettant de savoir quelles cases sont visibles</li>
 * <li>Une liste contenant les diff�rents personnages de l'�quipe</li>
 * </ul>
 * </p>
 */
public class Vue {
	
	/** Tableau repr�sentant les cases visibles */
	private boolean[][] brouillard;
	
	/** Liste des diff�rents personnages de l'�quipe. */
	private ArrayList<Personnage> personnages;
	
	
	/** Constructeur vide de Vue. */
	public Vue() {
	}
	
	/**
	 * Constructeur de Vue.
	 * 
	 * @param taille
	 *            Taille de l'ile.
	 * @param personnages
	 *            Liste des personnages avec lesquels il faut g�rer la vue.
	 */
	public Vue(int taille, ArrayList<Personnage> personnages){
		
		brouillard = new boolean[taille][taille];
		
		for(int i=0;i<brouillard.length;i++){
			for(int j=0;j<brouillard[0].length;j++){
				brouillard[i][j]=false;
			}
		}
		
		this.personnages=personnages;
	}
	
	/** Permet de mettre � jour la vue, le brouillard.*/
	public void updateVue(){
		int xPerso;
		int yPerso;
		for(Personnage p : personnages){
			xPerso=p.getX();
			yPerso=p.getY();
			for(int i=-1;i<2;i++){
				for(int j=-1;j<2;j++){
					brouillard[j+xPerso][i+yPerso]=true;
				}
			}
		}
	}
	
	/**
	 * Renvoie la vue sous forme d'un tableau de boolean repr�sentant le brouillard et les cases visibles.
	 * 
	 * @return Retourne un tableau de boolean.
	 */
	public boolean[][] getBrouillard(){
		return brouillard;
	}
	
	/**
	 * Modifie la liste des personnages afin d'adapter la vue.
	 * 
	 * @param p
	 *           Liste voulue.
	 */
	public void setTeam(ArrayList<Personnage> p){
		personnages=p;
	}
}
