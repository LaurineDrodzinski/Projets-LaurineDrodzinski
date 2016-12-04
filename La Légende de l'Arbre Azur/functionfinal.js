/* Fonction permettant l'affichage du fond de la carte */
function dessinerCarte(){	
	ctx.drawImage(carte, 0, 0, 960, 640);
}


/* Fonction permettant l'affichage des personnages non joueur et des objets */
function dessinerPnjObjets(){	

for(affichageL=0 ; affichageL<20 ; affichageL++){//AffichageL prend pour valeur initiale 0. Tant que la variable affichageL est inf�rieur � 20 , � chaque tour elle augmente de 1.

	for(affichageC=0 ; affichageC<30 ; affichageC++){//AffichageC prend pour valeur initiale 0. Tant que la variable affichageC est inf�rieur � 30 , � chaque tour elle augmente de 1.

		switch(tab[affichageL][affichageC]){

			case 55://Quand la case est �gale � 55 afficher le personnage de la qu�te <mon objet>
				//Permet de faire pivoter le personnage en fonction de la position du h�ros
				if(tab[l][c]==5){//Si le h�ros se trouve sur une case 5, c'est � dire une case autour du personnage alors...

					if(c==1 && l==8){//Si le h�ros se trouve sur la case au dessus du personnage
						ctx.drawImage(pnjfantomeobjetdos, (affichageC*32)+3, (affichageL*32)+3, 27, 30);					
					}

					if(c==0 && l==9){//Si le h�ros se trouve sur la case � gauche du personnage
						ctx.drawImage(pnjfantomeobjetgauche, (affichageC*32)+3, (affichageL*32)+3, 27, 30);					
					}

					if(c==2 && l==9){//Si le h�ros se trouve sur la case � droite du personnage
						ctx.drawImage(pnjfantomeobjetdroit, (affichageC*32)+3, (affichageL*32)+3, 27, 30);					
					}

					if(c==1 && l==10){//Si le h�ros se trouve sur la case en dessous du personnage
						ctx.drawImage(pnjfantomeobjetface, (affichageC*32)+3, (affichageL*32)+3, 27, 30);					
					}

				}else{//Sinon, lorsque le h�ros n'est pas autour du personnage, alors...
					ctx.drawImage(pnjfantomeobjetface, (affichageC*32)+3, (affichageL*32)+3, 27, 30);
				}
			break;


			case 66://Quand la case est �gale � 66 afficher le personnage de la qu�te <mon nom>
				//Permet de faire pivoter le personnage en fonction de la position du h�ros
				if(tab[l][c]==6){//Si le h�ros se trouve sur une case 6, c'est � dire une case autour du personnage alors...

					if(c==4 && l==19){//Si le h�ros se trouve sur la case en dessous du personnage
						ctx.drawImage(pnjfantomenomface, (affichageC*32)+3, (affichageL*32)+3, 25, 30);					
					}

					if(c==3 && l==18){//Si le h�ros se trouve sur la case � gauche du personnage
						ctx.drawImage(pnjfantomenomgauche, (affichageC*32)+5, (affichageL*32)+3, 22, 30);					
					}

					if(c==5 && l==18){//Si le h�ros se trouve sur la case � droite du personnage
						ctx.drawImage(pnjfantomenomdroit, (affichageC*32)+5, (affichageL*32)+3, 22, 30);					
					}

					if(c==4 && l==17){//Si le h�ros se trouve sur la case au dessus du personnage
						ctx.drawImage(pnjfantomenomdos, (affichageC*32)+3, (affichageL*32)+3, 25, 30);					
					}

				}else{//Sinon, lorsque le h�ros n'est pas autour du personnage, alors...
					ctx.drawImage(pnjfantomenomdos, (affichageC*32)+3, (affichageL*32)+3, 25, 30);
				}
			break;


			case 77://Quand la case est �gale � 77 afficher le personnage de la Mort
				//Permet de faire pivoter le personnage en fonction de la position du h�ros
				if(tab[l][c]==7){//Si le h�ros se trouve sur une case 7, c'est � dire une case autour du personnage alors...

					if(c==7 && l==13){//Si le h�ros se trouve sur la case en dessous du personnage
						ctx.drawImage(pnjmortface, (affichageC*32)+3, (affichageL*32)+3, 28, 30);					
					}

					if(c==8 && l==12){//Si le h�ros se trouve sur la case � droite du personnage
						ctx.drawImage(pnjmortdroit, (affichageC*32)+3, (affichageL*32)+3, 26, 30);					
					}

					if(c==7 && l==11){//Si le h�ros se trouve sur la case au dessus du personnage
						ctx.drawImage(pnjmortdos, (affichageC*32)+3, (affichageL*32)+3, 28, 30);					
					}

				}else{//Sinon, lorsque le h�ros n'est pas autour du personnage, alors...
					ctx.drawImage(pnjmortdroit, (affichageC*32)+3, (affichageL*32)+3, 26, 30);
				}
			break;


			case 88://Quand la case est �gale � 88 afficher le personnage habill� en bleu
				//Permet de faire pivoter le personnage en fonction de la position du h�ros
				if(tab[l][c]==8){//Si le h�ros se trouve sur une case 8, c'est � dire une case autour du personnage alors...

					if(c==23 && l==14){//Si le h�ros se trouve sur la case au dessus du personnage
						ctx.drawImage(pnjBleuDos, (affichageC*32), (affichageL*32));
					}

					if(c==22 && l==15){//Si le h�ros se trouve sur la case � gauche du personnage
						ctx.drawImage(pnjBleuGauche, (affichageC*32), (affichageL*32));
					}

					if(c==24 && l==15){//Si le h�ros se trouve sur la case � droite du personnage
						ctx.drawImage(pnjBleuDroite, (affichageC*32), (affichageL*32));
					}

				}else{//Sinon, lorsque le h�ros n'est pas autour du personnage, alors...
					ctx.drawImage(pnjBleuFace, (affichageC*32), (affichageL*32));
				}
			break;


			case 555://Quand la case est �gale � 555 afficher le bocal
				ctx.drawImage(bocal, (affichageC*32)+2, (affichageL*32)+10, 25, 26);
			break;


			case 999://Quand la case est �gale � 999 afficher le portail servant � terminer le jeu
				ctx.drawImage(portailFin, (affichageC*32)-26, (affichageL*32)-30, 95, 70);
			break;
				
		}
	}
}

}


function dessinerdecors(){

for(affichageL=0 ; affichageL<20 ; affichageL++){//AffichageL prend pour valeur initiale 0. Tant que la variable affichageL est inf�rieur � 20 , � chaque tour elle augmente de 1.

	for(affichageC=0 ; affichageC<30 ; affichageC++){//AffichageC prend pour valeur initiale 0. Tant que la variable affichageC est inf�rieur � 30 , � chaque tour elle augmente de 1.

		switch(tab[affichageL][affichageC]){

			case 11://Quand la case est �gale � 11 afficher le portail du cimeti�re
				ctx.drawImage(portailCim, (affichageC*32)+11, (affichageL*32)-20, 10, 53);
			break;

			case 20://Quand la case est �gale � 20 afficher un arbre mort
				ctx.drawImage(arbre1, (affichageC*32)-15, (affichageL*32)-60, 60, 95);
			break;

			case 21://Quand la case est �gale � 21 afficher un arbre avec feuilles
				ctx.drawImage(arbre2, (affichageC*32)+1, (affichageL*32)-50, 43, 80);
			break;

			case 22://Quand la case est �gale � 22 afficher un arbre avec feuilles
				ctx.drawImage(arbre3, (affichageC*32)-3, (affichageL*32)-46, 43, 80);
			break;

			case 23://Quand la case est �gale � 23 afficher un arbre avec feuilles
				ctx.drawImage(arbre4, (affichageC*32)-22, (affichageL*32)-45, 80, 80);
			break;

			case 24://Quand la case est �gale � 24 afficher un arbre avec feuilles
				ctx.drawImage(arbre5, (affichageC*32)-2, (affichageL*32)-45, 50, 70);
			break;

			case 44://Quand la case est �gale � 44 afficher une maison
				ctx.drawImage(maison, (affichageC*32)-32, (affichageL*32)-65, 96, 100);
			break;

			case 30://Quand la case est �gale � 30 afficher un puit
				ctx.drawImage(puit, (affichageC*32)-15, (affichageL*32)-50, 65, 85);
			break;

			case 31://Quand la case est �gale � 31 afficher un champ de bl�
				ctx.drawImage(champble, (affichageC*32), (affichageL*32), 130, 65);
			break;

			case 32://Quand la case est �gale � 32 afficher une botte de paille
				ctx.drawImage(paille, (affichageC*32)-6, (affichageL*32)-11, 43, 45);
			break;

			case 111://Quand la case est �gale � 111 afficher une statue
				ctx.drawImage(statue1, (affichageC*32)-4, (affichageL*32)-36, 39, 70);
			break;

			case 112://Quand la case est �gale � 112 afficher une statue l�g�rement modifi�e pour que la cl�ture du cimeti�re se situe au dessus de la statue
				ctx.drawImage(statue2, (affichageC*32)-4, (affichageL*32)-36, 39, 70);
			break;

			case 33://Quand la case est �gale � 33 afficher un �tendoir � linge
				ctx.drawImage(linge, (affichageC*32)+5, (affichageL*32)+15, 130, 50);
			break;

			case 25://Quand la case est �gale � 25 afficher le grand arbre magique
				if(ambre==2){//Si ambre=2, c'est � dire lorsque la Mort nous a donn� l'ambre et que nous l'avons ins�r� dans l'arbre � l'aide d'"Entr�e", alors afficher l'arbre avec l'ambre
					ctx.drawImage(arbreAvecAmbre, (affichageC*32)-53, (affichageL*32)-102, 140, 145);
				}else{//Sinon tant que les qu�tes ne sont pas termin�es et que nous n'avons toujours pas ins�r� l'ambre dans l'arbre, alors afficher l'arbre sans l'ambre
					ctx.drawImage(arbreSansAmbre, (affichageC*32)-53, (affichageL*32)-102, 140, 145);
				}
			break;

		}
	}
}

}



/* Fonction permettant l'affichage du h�ros */
function dessinerherosface(){
	ctx.drawImage(herosface, x+5, y+2, 23, 29);
	deplacement=0;//Si le h�ros se d�place vers le bas alors la variable d�placement vaut 0
}

function dessinerherosdos(){
	ctx.drawImage(herosdos, x+5, y+2, 23, 29);
	deplacement=1;//Si le h�ros se d�place vers le haut alors la variable d�placement vaut 1
}

function dessinerherosdroit(){
	ctx.drawImage(herosdroit, x+5, y+2, 23, 29);
	deplacement=2;//Si le h�ros se d�place vers la droite alors la variable d�placement vaut 2
}

function dessinerherosgauche(){
	ctx.drawImage(herosgauche, x+5, y+2, 23, 29);
	deplacement=3;//Si le h�ros se d�place vers la gauche alors la variable d�placement vaut 3
}



/*Permet d'afficher une image dans la zone d�di�e aux portraits en fonction de la personne avec qui le h�ros parle, de l'objet avec lequel il interagit*/
function portrait(){
	if (varPhoto==0){document.getElementById('photoPortrait').src="imgDecors/void.png";}//si =0, image vide
	
	if (varPhoto==1){document.getElementById('photoPortrait').src="imgPersos/Mortefeu.png";}//si =1, fant�me de la qu�te <mon nom> (Mortefeu)
	
	if (varPhoto==2){document.getElementById('photoPortrait').src="imgPersos/Queen.png";}//si =2, fant�me de la qu�te <mon objet>

	if (varPhoto==3){document.getElementById('photoPortrait').src="imgDecors/tetedemort.png";}//si =3, la T�te de mort (Game Over)
	
	if (varPhoto==4){document.getElementById('photoPortrait').src="imgPersos/mort.png";}//si =4, la Mort

	if (varPhoto==5){document.getElementById('photoPortrait').src="imgPersos/hood1.png";}//si =5, le personnage en bleu

	if (varPhoto==6){document.getElementById('photoPortrait').src="imgDecors/tombe1.png";}//si =6, une des tombes

	if (varPhoto==7){document.getElementById('photoPortrait').src="imgDecors/tombe2.png";}//si =7, une des tombes

	if (varPhoto==8){document.getElementById('photoPortrait').src="imgDecors/tombe3.png";}//si =8, une des tombes

	if (varPhoto==9){document.getElementById('photoPortrait').src="imgDecors/tombe4.png";}//si =9, une des tombes
}



/* Permet d'afficher un objet dans l'inventaire lorsqu'il est ramass� */
function inventaire(){
 	if(varAvancementQueteObjet==2){//Si le h�ros ramasse le bocal alors...
  		document.getElementById('photoBocal').src="imgItems/bocal.png";
 	}
 	if(varAvancementQueteObjet==3){//Si le h�ros donne le bocal au fant�me alors...
  		document.getElementById('photoBocal').src="imgItems/bocalombre.png";
 	}
 	if(ambre==1){//Si le h�ros obtient l'ambre alors...
  		document.getElementById('photoAmbre').src="imgItems/ambre.png";
 	}
 	if(ambre==2){//Si le h�ros insert l'ambre dans l'arbre alors...
  		document.getElementById('photoAmbre').src="imgItems/ambreombre.png";
 	}
}



/* Fonction concernant la qu�te <mon objet> */
function queteObjet(){

	if(varEntree==1 && tab[l][c]==5){//Si le h�ros se situe autour du fant�me de la qu�te <mon objet> et qu'il appuie sur "Entr�e" alors...
		varPhoto=2;//Affiche le portrait du fant�me
		p2.style.display='block';//Affiche la bo�te de dialogue
		
		if(varAvancementQueteObjet!=2){//Se d�clenche quand le h�ros parle au fant�me tant qu'il n'a toujours pas ramass� le bocal
			document.getElementById('p2').innerHTML="<br>Quelqu'un a profan� ma tombe... Comment a-t-il os�?!<br> De fait... Je ne peux rejoindre <br> l'Outre-monde puisque ce malotru  m'a vol� le bocal contenant l'oreille de mon arri�re-grand oncle, un artefact d'une valeur sentimentale importante...<br> (Et non, je ne vous dirai pas pourquoi!)";
			varAvancementQueteObjet=1;//Autorise le ramassage de l'objet
		}else{//Fin de la qu�te
     		document.getElementById('p2').innerHTML="<br>Grands dieux! Vous l'avez trouv�! Merci de mille feux !";
			varAvancementQueteObjet=3;//La qu�te est termin�e, au prochain d�placement le fant�me dispara�tra et plus aucune interaction avec lui ne sera possible
			varAvancementQueteMort++;//Augmente de 1 le nombre de qu�tes termin�es
    	}

	}

	if(varAvancementQueteObjet==1 && tab[l][c]==555){//Si le h�ros a d�j� parl� au fant�me et qu'il se trouve sur la case sur laquelle se situe le bocal alors ...
		varAvancementQueteObjet=2;//L'objet est ramass� et apparait dans l'inventaire
		tab[1][11]=0;//La case prend la valeur 0, la case est alors vide
	}

}



/* Fonction concernant la qu�te <mon nom> */
function queteNom(){

	if(varEntree==1){//Si le joueur appuie sur "Entr�e" ...

		if(tab[l][c]==6){//Et si le h�ros se situe sur une case autour du fant�me de la qu�te <mon nom> ...

			varPhoto=1;//Affiche le portrait du personnage donnant la qu�te <mon nom>	

			if(varAvancementQueteNom!=2){//S'active lors du premier dialogue avec le fant�me concern�
				document.getElementById('PhotoEntree').src="imgDecors/void.png";//Tant que la qu�te n'est pas activ�e la touche "Entr�e" n'apparait pas
				varAvancementQueteNom=1;//La qu�te est activ�e et le h�ros peut alors lire ce qu'il y a sur les tombes
				document.getElementById('p2').innerHTML="<br>Bon sang!<br>Je ne parviens pas � rejoindre l'Outre-monde... Quelle peste!...<br> Si j'avais ou�e ne serait-ce que de mon nom alors... Qui sait...<br>Retrouvez mon nom Voyageur !<br>Avant que le Nettoyeur des Mondes n'annihile mon existence!<br>Rappelez-vous! Mon nom!";
			}else{
				saisieReponse.style.display="block";//La zone de saisie s'affiche lorsque le h�ros reparle au fant�me et qu'il a d�j� �t� voir l'une des tombes
				document.getElementById('p2').innerHTML="<br>Alors? Avez-vous trouv�?!";

				document.getElementById('parlerNom').onclick= function(){//Quand on clique sur le bouton "Est-ce cela?"
					var nomVrai="Mortefeu";//Cr�ation de la variable concernant le vrai nom "Mortefeu" pour qu'il soit ensuite compar� avec le nom donn� par le joueur
					var nomTest=document.getElementById('entrerNom').value;//Cr�ation de la variable du nom donn� par le joueur (r�cup�ration du texte entr�) pour qu'il soit ensuite compar� avec "Mortefeu" 

					if(nomTest==nomVrai){//Si le nom donn� est le m�me que "Mortefeu" alors terminer la qu�te, sinon il faut r�essayer
						document.getElementById('p2').innerHTML="<br>Ce nom... Il me dit quelque chose...<br>Oui ! Oui! C'est lui ! Joie, bonheur intense!<br>Merci infiniment!";
						/*La case o� se situe le fant�me ainsi que les cases autour de celui-ci prennent comme valeur 0, ce qui fait dispara�tre le fant�me et les zones d'interaction le concernant */
   						tab[18][4]=0;//-> fant�me
						tab[18][3]=0;//-> zone d'interaction
						tab[17][4]=0;//-> zone d'interaction
						tab[19][4]=0;//-> zone d'interaction
						tab[18][5]=0;//-> zone d'interaction
						saisieReponse.style.display='none';//La zone de saisie disparait
						varAvancementQueteMort++;//augmente de 1 le nombre de qu�tes termin�es		
					}else{//Quand le joueur s'est tromp�
						document.getElementById('p2').innerHTML="<br>Non...<br>Non je ne crois pas...";
					}
				}
			}	
		}
	
		if(varAvancementQueteNom!=0){//S'active seulement si le h�ros a lanc� la qu�te (il faut parler une fois au fant�me)

			if(l==13 && c==1){//S'active si le h�ros se trouve devant la tombe concern�e (cette tombe est celle comportant la r�ponse � l'�nigme)
				varPhoto=6;//Affiche la tombe concern�e dand la zone de portrait
				varAvancementQueteNom=2;//Le h�ros peut alors tenter de donner le bon nom au fant�me
				document.getElementById('p2').innerHTML="<br> Ci-g�t le Baron Mortefeu,<br> que son essence puisse atteindre l'Absolution et franchir les Portes de la V�rit�.<br> Son �me restera �ternelle en nos coeurs.<br> V.J.";
			}

			if(l==11 && c==4){//S'active si le h�ros se trouve devant la tombe concern�e
				varPhoto=7;//Affiche la tombe concern�e dand la zone de portrait
				varAvancementQueteNom=2;//Le h�ros peut alors tenter de donner le bon nom au fant�me
				document.getElementById('p2').innerHTML="<br>'Je n'aurais pas dur� plus que l'�cume <br> Aux l�vres de la vague sur le sable<br> N� sous aucune �toile un soir de lune<br> Mon nom ne fut qu'un sanglot p�rissable'<br> Y.G";
			}

			if(l==17 && c==3){//S'active si le h�ros se trouve devant la tombe concern�e
				varPhoto=8;//Affiche la tombe concern�e dand la zone de portrait
				varAvancementQueteNom=2;//Le h�ros peut alors tenter de donner le bon nom au fant�me
				document.getElementById('p2').innerHTML="<br> A mon Cher ...<br> aimerons pour toujours...<br> fr�re...";
			}

			if(l==19 && c==2){//S'active si le h�ros se trouve devant la tombe concern�e
				varPhoto=9;//Affiche la tombe concern�e dand la zone de portrait
				varAvancementQueteNom=2;//Le h�ros peut alors tenter de donner le bon nom au fant�me
				document.getElementById('p2').innerHTML="<br>...................................................<br>...................................................<br>..................................<br><br><br>La tombe est trop �rod�e pour pouvoir y lire quelque chose.";
			}
		}
	}
}



/* Fonction concernant la qu�te <la Mort> */
function queteMort(){

	if(varEntree==1 && tab[l][c]==7){//Si le h�ros se situe autour du fant�me de la qu�te <la Mort> et qu'il appuie sur "Entr�e" alors...

		varPhoto=4;//Affiche le portrait de la Mort

		if(varAvancementQueteMort!=2){//S'active tant que le h�ros n'a pas termin� les 2 qu�tes
			/* Permet la disparition de la barri�re emp�chant le h�ros de rentrer dans le cimeti�re */
    		tab[13][6]=0;
    		tab[14][6]=0;
    		document.getElementById('p2').innerHTML="<br>Je vous salue, noble Voyageur...<br>J 'aurais un service � vous demander, si vous le voulez bien...<br>Accepteriez-vous de m'aider � sauver quelques �mes en perdition?<br>Si vous le faites, je vous en serai tr�s reconnaissante.<br>Je vous offrirai un pr�sent pour vos bonnes Actions...<br>Il vous suffit d'entrer dans le Cimeti�re que voici, et de chercher ces �mes en peine. <br>Merci, Voyageur.";
    	}else{//Quand le h�ros est sur une zone 7 et que les 2 qu�tes des fant�mes sont termin�es alors activation du dialogue de fin avec la Mort 	
    		ambre=1;//L'ambre apparait dans l'inventaire du joueur, il peut ensuite aller l'ins�rer dans l'arbre
    		varAvancementQueteMort=3;//La Mort donne l'ambre et disparait
    		document.getElementById('p2').innerHTML="<br>Je vous remercie ! Voici un �trange morceau d'ambre, que l'on appelle Pierre de l'Oracle. Je l'ai trouv� dans la demeure d'une divine amie... Peut-�tre en trouverez-vous une utilit�.";
   		}
	}
}



/* Fonction concernant le personnage habill� en bleu */
function personnageBleu(){

	if(varEntree==1 && tab[l][c]==8){//Si le h�ros se situe autour du personnage en bleu et qu'il appuie sur "Entr�e" alors...

		varPhoto=5;//Affiche le portrait du personnage en bleu

		if(ambre==1){//Si le h�ros poss�de d�j� l'ambre
			document.getElementById('p2').innerHTML="<br>Vous! Encore! Par... qu'est-ce donc que cela?!<br> Non! Vous! Comment! Donnez la moi! Tout de suite!<br> Cette roche permet d'ouvrir un portail vers la demeure de la Supr�me Divinit� Ghamina en la fusionnant avec un arbre sacr�! ...<br> Non! Pourquoi l'ai-je dit !? Diable ! ";
		}else{//Si le h�ros ne poss�de pas encore l'ambre ou qu'il l'a d�j� ins�r� dans l'arbre
			document.getElementById('p2').innerHTML="<br>Mecr�ant! Eloignez-vous de moi! Vous m'importunez! Pshht !<br>Revenez quand vous servirez � quelque chose! Houste !";
		}

	}
}



/* Fonction concernant la qu�te <grand arbre> */
function queteGrandArbre(){

	if(varEntree==1 && tab[l][c]==9){//Si le h�ros se situe devant l'arbre et qu'il appuie sur "Entr�e" alors...

		varPhoto=0;//Ne pas afficher de portrait

		if(ambre==0){//Si le h�ros ne poss�de pas encore l'ambre
    		document.getElementById('p2').innerHTML="<br>On peut apercevoir un creux de forme �trange au centre de l'arbre";
    	}
    	if(ambre==1){//Si le h�ros poss�de l'ambre
    		document.getElementById('p2').innerHTML="<br>La pierre semble r�agir avec l'arbre";
    		ambre=2;//Permet d'afficher l'ombre de l'objet dans l'inventaire
    		tab[1][3]=999;//Apparition du portail de sortie
    		tab[1][2]=3;//Obstacle -> bord du portail
 			tab[1][4]=3;//Obstacle -> bord du portail
    	}

    }
}



/*Fonction concernant la fin du jeu */
function finJeu(){

	if(varEntree==1 && tab[l][c]==999){//Si le h�ros se situe dans le portail et qu'il appuie sur "Entr�e" alors...

    	canvas.style.display="none";//Ne plus afficher le canvas
    	PhotoEntree.style.display="none";//Ne plus afficher la photo de la touche "Entr�e"
    	p3.style.display="none";//Ne plus afficher l'inventaire
    	p2.style.display="none";//Ne plus afficher l'inventaire
    	imgfin.style.display="block";//Afficher l'image de fin
		btnRejouer.style.display="block";//Afficher la bouton "Rejouer"

		document.getElementById('btnRejouer').onclick= function(){//Quand on clique sur le bouton "Rejouer" ...
			window.location.reload();//Recharge la page -> nouvelle partie
		}

   	}
}



/* Fonction permettant d'afficher l'ennemi -> c'est une fonction qui se r�p�te toutes les X secondes -> pour que l'ennemi se d�place */
function dessinerEnnemi(){

	if(ennemi==1){//Si l'ennemi est toujours en vie

		if(direction==1){//S'il se d�place vers le bas
			ennemiL++;//augmente de 1 la variable concernant la position de l'ennemi (ligne), il descend d'une case
			ctx.drawImage(herbe, (ennemiC*32)+2, ((ennemiL-1)*32), 28, 29);//Dessine un carr� d'herbe sur la case pr�c�dent l'ennemi, permet d'effacer l'image pr�c�dente
			ctx.drawImage(ennemiFace, (ennemiC*32)+2, (ennemiL*32), 29, 29);//Dessine l'ennemi de face sur la bonne case 

			tab[ennemiL-2][ennemiC]=0;//Efface les anciennes zones d'interaction (deux cases au dessus)
			tab[ennemiL-1][ennemiC-1]=0;//Efface les anciennes zones d'interaction (une case en diagonale en haut vers la gauche)
			tab[ennemiL-1][ennemiC+1]=0;//Efface les anciennes zones d'interaction (une case en diagonale en haut vers la droite)
			tab[ennemiL-1][ennemiC]=10;//Cr�ation d'une zone d'interaction autour de l'ennemi (� gauche de l'ennemi)
			tab[ennemiL+1][ennemiC]=10;//Cr�ation d'une zone d'interaction autour de l'ennemi (� droite de l'ennemi)
			tab[ennemiL][ennemiC-1]=10;//Cr�ation d'une zone d'interaction autour de l'ennemi (au dessus de l'ennemi)
			tab[ennemiL][ennemiC+1]=10;//Cr�ation d'une zone d'interaction autour de l'ennemi (en dessous de l'ennemi)

			if(ennemiL==13){//Quand il arrivera � la ligne 13 du tableau (carte) sa direction changera (direction=0) et il se d�placera alors vers le haut
				direction=0;
			}
		}
			
		if(direction==0){//S'il se d�place vers le haut
			ennemiL--;//diminue de 1 la variable concernant la position de l'ennemi (ligne), il monte d'une case
			ctx.drawImage(herbe, (ennemiC*32)+2, ((ennemiL+1)*32), 28, 29);//Dessine un carr� d'herbe sur la case pr�c�dent l'ennemi, permet d'effacer l'image pr�c�dente
			ctx.drawImage(ennemiDos, (ennemiC*32)+2, (ennemiL*32), 29, 29);//Dessine l'ennemi de dos sur la bonne case 

			tab[ennemiL+2][ennemiC]=0;//Efface les anciennes zones d'interaction (deux case en dessous)
			tab[ennemiL+1][ennemiC+1]=0;//Efface les anciennes zones d'interaction (une case en diagonale en bas vers la droite)
			tab[ennemiL+1][ennemiC-1]=0;//Efface les anciennes zones d'interaction (une case en diagonale en bas vers la gauche)
			tab[ennemiL-1][ennemiC]=10;//Cr�ation d'une zone d'interaction autour de l'ennemi (� gauche de l'ennemi)
			tab[ennemiL+1][ennemiC]=10;//Cr�ation d'une zone d'interaction autour de l'ennemi (� droite de l'ennemi)
			tab[ennemiL][ennemiC-1]=10;//Cr�ation d'une zone d'interaction autour de l'ennemi (au dessus de l'ennemi)
			tab[ennemiL][ennemiC+1]=10;//Cr�ation d'une zone d'interaction autour de l'ennemi (en dessous de l'ennemi)

			if(ennemiL==6){//Quand il arrivera � la ligne 6 du tableau (carte) sa direction changera (direction=1) et il se d�placera alors vers le bas
				direction=1;
			}

		}

		if((l==ennemiL-1 || l==ennemiL+1) && c==ennemiC){//Corrige le fait que le h�ros soit effac� par le carr� d'herbe quand il est derri�re l'ennemi, on redessine donc le personnage en fonction du dernier mouvement effectu�
			if(deplacement==0){//Vers le bas
				ctx.drawImage(herosface, x+5, y+2, 23, 29);
			}
			if(deplacement==1){//Vers le haut
				ctx.drawImage(herosdos, x+5, y+2, 23, 29);
			}
			if(deplacement==2){//Vers la droite
				ctx.drawImage(herosdroit, x+5, y+2, 23, 29);
			}
			if(deplacement==3){//Vers la gauche
				ctx.drawImage(herosgauche, x+5, y+2, 23, 29);
			}
		}
	}
}



/* Fonction s'activant lorsqu'il y a collision entre le h�ros et l'ennemi -> Game Over */
function collisionEnnemi(){
	if(ennemi==1){//Si l'ennemi est toujours en vie
		if(c==ennemiC && l==ennemiL){//Si les coordonn�es de l'ennemi et du h�ros sont les m�mes

			ctx.drawImage(herbe, x, y, 28, 32);//On dessine un carr� d'herbe au dessus du h�ros pour qu'il puisse r�apparaitre au point de d�part

			/*On redessine l'ennemi en fonction de sa direction (car il a �t� effac� pas l'action ci-dessus) */
			if(direction==0){//S'il monte
				ctx.drawImage(ennemiDos, (ennemiC*32)+2, (ennemiL*32), 29, 29);
			}else{//S'il descend
				ctx.drawImage(ennemiFace, (ennemiC*32)+2, (ennemiL*32), 29, 29);
			}

			varPhoto=3;//Affiche la t�te de mort dans la zone d�di�e aux portraits
			portrait();//Active la fonction portrait
			p2.style.display="block";//Affiche la bo�te de dialogue
			document.getElementById('p2').innerHTML="<br><br>GAME OVER";

			/*R�nitialisation des variables concernant la position du h�ros*/
			l=3;
			c=3;
			x=96;
			y=96;
			ctx.drawImage(herosface, x+5, y+2, 23, 29);//Dessine le h�ros au point de d�part
		}
	}
}



/*Fonction qui permet au h�ros de tuer l'ennemi */
function attaquer(){
	if(ennemi==1){//Si l'ennemi est toujours en vie
		if(tab[l][c]==10){//Si le h�ros se situe sur une case autour de l'ennemi
			document.getElementById('PhotoEntree').src="imgDecors/entree.png";//La touche "Entr�e" apparait
			ctx.drawImage(herbe, (c*32), (l*32), 32, 32);//On dessine un carr� d'herbe pour effacer le h�ros -> pour �viter une superposition d'images du h�ros

			/*Dessine le h�ros tenant une �p�e d�s que l'ennemi passe � c�t� de lui */
			if(l==ennemiL && c==ennemiC-1){//Le h�ros se situe � gauche de l'ennemi
    			ctx.drawImage(herosdroitepee, x+5, y+2, 32, 29);
   	 		}

    		if(l==ennemiL && c==ennemiC+1){//Le h�ros se situe � droite de l'ennemi
				ctx.drawImage(herosgaucheepee, x-3, y+2, 31, 29);
    		}

    		if(l==ennemiL-1 && c==ennemiC){//Le h�ros se situe au dessus de l'ennemi
    			ctx.drawImage(herosfaceepee, x+2.5, y+2, 27, 30);
    		}

   	 		if(l==ennemiL+1 && c==ennemiC){//Le h�ros se situe en dessous de l'ennemi
    			ctx.drawImage(herosdosepee, x+2.5, y+1, 28, 30);
    		}	

    		if(varEntree==1){//Si le joueur appuie sur la touche "Entr�e"

    			/* La suite des setTimeout sert � r�aliser une disparition progressive de l'ennemi */
				setTimeout("effacerEnnemi1()", 0);//Se d�clenche au bout de X secondes (1000 = 1 seconde)
				setTimeout("effacerEnnemi2()", 100);
				setTimeout("effacerEnnemi3()", 250);
				setTimeout("effacerEnnemi4()", 400);
				setTimeout("effacerEnnemi5()", 550);

				ennemi=0;//Indique que l'ennemi est mort

    			/* Les cases autour et sur l'ennemi redeviennent de l'herbe normale, plus d'action � r�aliser � ces endroits */
    			tab[ennemiL-1][ennemiC]=0;//En dessous de l'ennemi
				tab[ennemiL+1][ennemiC]=0;//Au dessus de l'ennemi
				tab[ennemiL][ennemiC-1]=0;//A gauche de l'ennemi
				tab[ennemiL][ennemiC+1]=0;//A droite de l'ennemi
				tab[ennemiL][ennemiC]=0;//La case o� se trouvait l'ennemi
    		}
		}

		if(tab[l][c]==0){//Si le h�ros ne se situe pas sur une case autour de l'ennemi
			document.getElementById('PhotoEntree').src="imgDecors/void.png";//La touche "Entr�e" disparait
		}
    }
}



/*Toutes ces fonctions servent � r�aliser une disparition progressive de l'ennemi quand il meurt*/
/* Ce sont toutes les m�mes � juste une diff�rence -> l'image dessin�e est � chaque fois un peu plus transparente */

//ctx.drawImage(image, sx, sy, sLargeur, sHauteur, dx, dy, dLargeur, dHauteur) -> 	//sx: source sur l'image -> axe des x
																					//sy: source sur l'image -> axe des y
																					//sLargeur: source sur l'image -> largeur du morceau d'image selectionn�
																					//sHauteur: source sur l'image -> hauteur du morceau d'image selectionn�
																					//dx: destination dans le canvas -> axe des x
																					//dy: destination dans le canvas -> axe des y
																					//dLargeur: destination dans le canvas -> largeur de l'image
																					//dHauteur: destination dans le canvas -> hauteur de l'image
function effacerEnnemi1(){
	ctx.drawImage(herbe, (ennemiC*32)+2, (ennemiL*32), 28, 32);//Dessine un carr� d'herbe au dessus de l'ennemi, ce qui permet par la suite de redessiner une nouvelle image par dessus
	if(direction==1){//S'il descend
		ctx.drawImage(disparitionEnnemiFace, 0, 0, 32, 32, (ennemiC*32)+4, (ennemiL*32), 29, 29);        
	}else{//S'il monte
		ctx.drawImage(disparitionEnnemiDos, 0, 0, 32, 31, (ennemiC*32)+4, (ennemiL*32), 29, 28);
	}
}

function effacerEnnemi2(){
	ctx.drawImage(herbe, (ennemiC*32)+2, (ennemiL*32), 28, 32);//Dessine un carr� d'herbe au dessus de l'ennemi, ce qui permet par la suite de redessiner une nouvelle image par dessus
	if(direction==1){//S'il descend
		ctx.drawImage(disparitionEnnemiFace, 0, 32, 32, 32, (ennemiC*32)+4, (ennemiL*32), 29, 29);
	}else{//S'il monte
		ctx.drawImage(disparitionEnnemiDos, 0, 31, 32, 31, (ennemiC*32)+4, (ennemiL*32), 29, 28);
	}
}

function effacerEnnemi3(){
	ctx.drawImage(herbe, (ennemiC*32)+2, (ennemiL*32), 28, 32);//Dessine un carr� d'herbe au dessus de l'ennemi, ce qui permet par la suite de redessiner une nouvelle image par dessus
	if(direction==1){//S'il descend
		ctx.drawImage(disparitionEnnemiFace, 0, 64, 32, 32, (ennemiC*32)+4, (ennemiL*32), 29, 29);
	}else{//S'il monte
		ctx.drawImage(disparitionEnnemiDos, 0, 62, 32, 31, (ennemiC*32)+4, (ennemiL*32), 29, 28);
	}
}

function effacerEnnemi4(){
	ctx.drawImage(herbe, (ennemiC*32)+2, (ennemiL*32), 28, 32);//Dessine un carr� d'herbe au dessus de l'ennemi, ce qui permet par la suite de redessiner une nouvelle image par dessus
	if(direction==1){//S'il descend
		ctx.drawImage(disparitionEnnemiFace, 0, 96, 32, 32, (ennemiC*32)+4, (ennemiL*32), 29, 29);
	}else{//S'il monte
		ctx.drawImage(disparitionEnnemiDos, 0, 93, 32, 31, (ennemiC*32)+4, (ennemiL*32), 29, 28);
	}
}

function effacerEnnemi5(){
	ctx.drawImage(herbe, (ennemiC*32)+2, (ennemiL*32), 28, 32);//Dessine un carr� d'herbe au dessus de l'ennemi, on ne redessine rien au dessus -> disparition totale de l'ennemi
}
