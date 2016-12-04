using UnityEngine;

public class DisparitionPlateformes : MonoBehaviour {

    private bool estSurDeclencheur = false;
    private bool actif = false;
    float timeLeft=0.4f;

    BoxCollider2D bD1=null;
    BoxCollider2D bD2=null;
    BoxCollider2D bD3=null;
    BoxCollider2D bD4=null;
    BoxCollider2D bD5=null;

    void Update(){
        if(!PauseMenu.showGUI){
    	   CollisionPlat();
             if(timeLeft < 0){
                triggerTrue();
             }
        }

        
    }

    void CollisionPlat(){
        if(estSurDeclencheur){
            GameObject d1 = GameObject.Find("d1");
            GameObject d2 = GameObject.Find("d2");
            GameObject d3 = GameObject.Find("d3");
            GameObject d4 = GameObject.Find("d4");
            GameObject d5 = GameObject.Find("d5");

            Animator animatorD1 = d1.GetComponent<Animator>();
            Animator animatorD2 = d2.GetComponent<Animator>();
            Animator animatorD3 = d3.GetComponent<Animator>();
            Animator animatorD4 = d4.GetComponent<Animator>();
            Animator animatorD5 = d5.GetComponent<Animator>();

            animatorD1.SetBool("declencheur",true); 
            animatorD2.SetBool("declencheur",true); 
            animatorD3.SetBool("declencheur",true); 
            animatorD4.SetBool("declencheur",true); 
            animatorD5.SetBool("declencheur",true); 

            bD1 = d1.GetComponent<BoxCollider2D>();
            bD2 = d2.GetComponent<BoxCollider2D>();
            bD3 = d3.GetComponent<BoxCollider2D>();
            bD4 = d4.GetComponent<BoxCollider2D>();
            bD5 = d5.GetComponent<BoxCollider2D>();
          
            actif=true;
        }
        if(actif){
            timeLeft = timeLeft - Time.deltaTime;  

        }
            
    }

    public void triggerTrue(){
            bD1.isTrigger =true;
            bD2.isTrigger =true;
            bD3.isTrigger =true;
            bD4.isTrigger =true;
            bD5.isTrigger =true;
    }

    public void setEstSurDeclencheur(bool estSurDeclencheur){
        this.estSurDeclencheur = estSurDeclencheur;
    }

    public bool getEstSurDeclencheur(){
        return estSurDeclencheur;
    }

}