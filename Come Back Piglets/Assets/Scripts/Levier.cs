using UnityEngine;

public class Levier : MonoBehaviour {

	//Pour la porte
    private int etatPorte = 0;//etat initial

    //Pour le levier
    private bool estSurLevier = false;
    private float tempsEntreLevier = 0.7f;

    void Update(){
        if(!PauseMenu.showGUI){
            CollisionLevier();
        }
    }

    void CollisionLevier(){
        if (tempsEntreLevier > 0){
            tempsEntreLevier -= Time.deltaTime;
        }
        if(estSurLevier && Input.GetKeyDown(KeyCode.RightAlt) && tempsEntreLevier<=0){

            GameObject porte;
            porte = GameObject.FindWithTag("Porte");
            Animator animatorPorte = porte.GetComponent<Animator>();

            GameObject levier;
            levier = GameObject.FindWithTag("Levier");
            Animator animatorLevier = levier.GetComponent<Animator>();

            if(etatPorte==2 || etatPorte==0){
                etatPorte=1;//descendue          
            }else{
                etatPorte=2;//pas descendue     
            }

            tempsEntreLevier=0.7f; 
            animatorPorte.SetInteger("etatPorte",etatPorte); 
            animatorLevier.SetInteger("etatLevier",etatPorte);  
            
        }
        
    }

    public void setEstSurLevier(bool estSurLevier){
    	this.estSurLevier = estSurLevier;
    }

    public bool getEstSurLevier(){
    	return estSurLevier;
    }
}