using UnityEngine;

public class Pig : MonoBehaviour {
 
    //Animations
    private Animator animator;

    //Collision avec le sol
    public Transform checkSol;
    public LayerMask Sol;
    private bool toucheLeSol = false;
    private float rayonSol = 0.2f;

    //Collision avec un mur
    public Transform checkMurDroite;
    public Transform checkMurGauche;
    public LayerMask Mur;
    private bool toucheUnMurDroite = false;
    private bool toucheUnMurGauche = false;
    private float rayonMur = 0.1f;

    /*Pour gerer les animations---------------------------------------*/
    public void setAnimator(Animator animator){
        this.animator=animator;
    }  

    public Animator getAnimator(){
        return animator;
    }   


    /*Collision avec le sol------------------------------------------*/
    public void setToucheLeSol(bool toucheLeSol){
        this.toucheLeSol=toucheLeSol;
    }  

    public void setRayonSol(float rayonSol){
        this.rayonSol=rayonSol;
    }

    public bool getToucheLeSol(){
        return toucheLeSol;
    } 

    public float getRayonSol(){
        return rayonSol;
    }    


    /*Collision avec un mur------------------------------------------*/
    public void setToucheUnMurDroite(bool toucheUnMurDroite){
        this.toucheUnMurDroite = toucheUnMurDroite;
    } 

    public void setToucheUnMurGauche(bool toucheUnMurGauche){
        this.toucheUnMurGauche = toucheUnMurGauche;
    } 

    public void setRayonMur(float rayonMur){
        this.rayonMur=rayonMur;
    } 

    public bool getToucheUnMurDroite(){
        return toucheUnMurDroite;
    }

    public bool getToucheUnMurGauche(){
        return toucheUnMurGauche;
    } 

    public float getRayonMur(){
        return rayonMur;
    }            

    /*Pour la physique*/
    void FixedUpdate(){
        bool tSol = Physics2D.OverlapCircle(checkSol.position, this.getRayonSol(), Sol);
        this.setToucheLeSol(tSol);
        this.getAnimator().SetBool("sol",tSol);

        bool tMD = Physics2D.OverlapCircle(checkMurDroite.position, this.getRayonMur(), Mur);
        this.setToucheUnMurDroite(tMD);

        bool tMG = Physics2D.OverlapCircle(checkMurGauche.position, this.getRayonMur(), Mur);
        this.setToucheUnMurGauche(tMG);
    }
}