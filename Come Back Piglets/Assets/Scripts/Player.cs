using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

public class Player : Pig {

    //Vitesse
    public float speed;

    //Pour le deplacement
    public float x;
    public List<float> mouvements; 
    public List<int> sens;
    public int sensPrecedent;

    //Activation ou non des LittlePigs qui suivent le joueur
    public bool suivre;

    //Demarrage
    void Start () {
        this.setAnimator(GetComponent<Animator>());
        speed = 5;
        mouvements = new List<float>();
        sens = new List<int>();
        sensPrecedent = 1;
        suivre = true;
    }

    //Une fois par frame
    void Update () {

        x = Input.GetAxis("Horizontal");

        Rigidbody2D rb = GetComponent<Rigidbody2D>();

        this.getAnimator().SetFloat("speed",Mathf.Abs(x));

        if(!PauseMenu.showGUI){

        if(Input.GetKeyDown (KeyCode.LeftAlt)){//Si on appuie sur "Alt gauche"
            if(suivre){
                suivre = false;//Les cochons ne suivent plus
            }else{
                suivre = true;//Les cochons suivent
            }
            mouvements = new List<float>();//Reinisialisation des mouvements
            sens = new List<int>();//Reinisialisation des directions
            LittlePig lp = (LittlePig)GameObject.FindGameObjectWithTag("LittlePig").GetComponent<LittlePig>();
            lp.getAnimator().SetFloat("speed",Mathf.Abs (0)); 
        }

        if(Input.GetButtonDown("Jump") && this.getToucheLeSol()){//Si on appuie sur "espace" et le cochon touche le sol
            mouvements.Add(5f);//On ajoute dans la liste une valeur propre au saut
            sens.Add(sensPrecedent);//On ajoute le sens precedent a la liste
            rb.AddForce(new Vector2(0,200));//Force verticale pour donner l'impression d'un saut
        }


        if(x<0){//vers la gauche

            this.getAnimator().SetInteger("direction", 1);//On modifie sa direction

            if(!this.getToucheUnMurGauche()){
                transform.Translate(x*speed*Time.deltaTime,0,0);//On deplace l'image
            }

            mouvements.Add(x*speed*Time.deltaTime);//On enregistre les mouvements
            sens.Add(1);//On enregistre la direction

        }else if(x>0){//vers la droite

            this.getAnimator().SetInteger("direction", 2);//On modifie sa direction

            if(!this.getToucheUnMurDroite()){
                transform.Translate(x*speed*Time.deltaTime,0,0);//On deplace l'image
            }

            mouvements.Add(x*speed*Time.deltaTime);//On enregistre les mouvements
            sens.Add(2);//On enregistre la direction
        }

        }
    }

    public void ReinitialisationPlayer(){
        this.setToucheLeSol(false);
        this.setToucheUnMurDroite(false);
        this.setToucheUnMurGauche(false);
        mouvements = new List<float>();
        sens = new List<int>();
        sensPrecedent = 1;
        suivre = true;
    }

    void OnTriggerEnter2D(Collider2D obj){
        if (obj.gameObject.tag == "DeclencheurDisparition"){
            DisparitionPlateformes zoneDeclenche = (DisparitionPlateformes)GameObject.FindGameObjectWithTag("DeclencheurDisparition").GetComponent<DisparitionPlateformes>();
            zoneDeclenche.setEstSurDeclencheur(true);
        }
    }

    void OnTriggerStay2D(Collider2D obj){
        if (obj.gameObject.tag == "Levier"){
            Levier levier = (Levier)GameObject.FindGameObjectWithTag("Levier").GetComponent<Levier>();
            levier.setEstSurLevier(true);
        }
    }

    void OnTriggerExit2D(Collider2D obj){
        if (obj.gameObject.tag == "Levier"){
            Levier levier = (Levier)GameObject.FindGameObjectWithTag("Levier").GetComponent<Levier>();
            levier.setEstSurLevier(false);
        }
    }

}