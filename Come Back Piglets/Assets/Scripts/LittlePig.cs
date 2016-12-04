using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

public class LittlePig : Pig {

    private float temps;
    private static int nbLP =0;
    private int compteurNbLP;

    void Start () { 
        this.setAnimator(GetComponent<Animator>());
        temps = 0.8f;
        compteurNbLP = 0;
        nbLP++;
    }

	// Update is called once per frame
	void Update () {
        Player player = (Player)GameObject.FindGameObjectWithTag("Player").GetComponent<Player>();

        if(!PauseMenu.showGUI){

        if(player.suivre){//Seulement si "suivre" est actif
            
            if(temps > 0){//Compteur permettant de decaler les mouvements
                temps -= Time.deltaTime;
            }

            Rigidbody2D rb = GetComponent<Rigidbody2D>();

            if(player.mouvements.Count>0 && temps<=0){//S'il reste des mouvements a realiser && si le decalage a ete realise

                int direction = (int)player.sens.First();//Premiere direction de la liste
                player.sensPrecedent = direction;//On enregistre la direction (utile pour le saut)

                float mouvement = (float)player.mouvements.First();//Premier mouvement de la liste

                if(compteurNbLP<nbLP){//Pour parcourir tous les LittlePigs
                    compteurNbLP++;
                }

                if(mouvement==5f && this.getToucheLeSol()){//Si le mouvement vaut 5 (correspond au saut) et s'il touche le sol

                    rb.AddForce(new Vector2(0,200));

                    if(compteurNbLP==nbLP){//Supprime le mouvement si tous les LittlePigs l'ont realise
                        player.mouvements.RemoveAt(0);
                        player.sens.RemoveAt(0);
                        compteurNbLP=0;

                        if(player.mouvements.Count>0){
                            mouvement = (float)player.mouvements.First();
                        }
                    }
                }

                if(player.mouvements.Count>0 && mouvement!=5f){
                    
                    this.getAnimator().SetFloat("speed",Mathf.Abs (1)); 
                    this.getAnimator().SetInteger("direction", direction);

                    if(mouvement>0 && !this.getToucheUnMurDroite()){
                        transform.Translate(mouvement,0,0);
                    }else if(mouvement<0 && !this.getToucheUnMurGauche()){
                        transform.Translate(mouvement,0,0);
                    }

                    if(compteurNbLP==nbLP){//Supprime le mouvement si tous les LittlePigs l'ont realise
                        player.mouvements.RemoveAt(0);
                        player.sens.RemoveAt(0);
                        compteurNbLP=0;
                    }   
                }
            }

        }

        if(player.mouvements.Count<=0){
            this.getAnimator().SetFloat("speed",Mathf.Abs (0)); 
            temps = 0.8f;
        }

         }
	}

    public void ReinitialisationLittlePig(){
        this.setToucheLeSol(false);
        this.setToucheUnMurDroite(false);
        this.setToucheUnMurGauche(false);
        temps = 0.8f;
        compteurNbLP = 0;
        nbLP++;
        nbLP = 0;
    }
}

