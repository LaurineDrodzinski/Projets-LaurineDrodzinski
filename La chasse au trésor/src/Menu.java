
/**
 * Classe permettant de g�rer les parametres rentr�s par l'utilisateur. Lance le menu.
 */
import javax.swing.JOptionPane;

public class Menu {
	
	Jeu l;
	String taille;
	String pourcentage;
	String choix = JOptionPane.showInputDialog(null,
			"Que souhaitez vous faire? \n1)Jouer avec une taille que vous choississez \n2)Jouer avec une taille de 10*10\n3)Jouer contre l'IA\n4)Mode d'emploi\n5)Tests\n6)Quitter");
	boolean bouclePrincipale = false;
	boolean choix1 = false;
	boolean choix2 = false;
	int minimum = 10;
	int maximum = 17;
	
	{
		while (!bouclePrincipale) {
			// Si l'utilisateur choisit d'appuyer sur la croix rouge en haut
			if (choix == null) {
				JOptionPane.showMessageDialog(null, "Au revoir ! �motic�ne smile");
				bouclePrincipale = true;
			}
			// Si l'utilisateur choisit de configurer sa taille
			else if (choix.equals("1")) {
				taille = JOptionPane.showInputDialog(null,
						"Quelle est la taille de votre carte?(Minimum:" + minimum + ", Maximum:" + maximum + ")");
				if (taille == null) {
					JOptionPane.showMessageDialog(null,
							"Vous avez d�cidez de vous enfuir ! Levez l'ancre moussaillon !!");
					bouclePrincipale = true;
					break;
				}
				// Verification si le parametre de la taille est respecte
				while (!choix1) {
					// Si l'utlisateur quitte
					if (taille == null) {
						JOptionPane.showMessageDialog(null,
								"Vous avez d�cidez de vous enfuir ! Levez l'ancre moussaillon !!");
						choix1 = true;
						bouclePrincipale = true;
						break;
					}
					// Si aucun argument
					else if (taille.equals("")) {
						JOptionPane.showMessageDialog(null, "Veuillez rentrer un argument valide s'il vous plait.");
						taille = JOptionPane.showInputDialog(null,
								"Quelle est la taille de votre carte?(Minimum " + minimum + ")");
					}
					// Si caractere non numerique
					else if (!taille.matches("[0-9]*")) {
						JOptionPane.showMessageDialog(null, "Veuillez rentrer un argument valide s'il vous plait.");
						taille = JOptionPane.showInputDialog(null,
								"Quelle est la taille de votre carte?(Minimum " + minimum + ")");
					}
					// Si le parametre est en dessous du minimum
					else if (Integer.parseInt(taille) < minimum) {
						JOptionPane.showMessageDialog(null,
								"Veuillez rentrer une taille sup�rieure � " + minimum + ".");
						taille = JOptionPane.showInputDialog(null, "Quelle est la taille de votre carte?");
					}
					// Si le parametre est au dessus du maximum
					else if (Integer.parseInt(taille) > maximum) {
						JOptionPane.showMessageDialog(null,
								"Veuillez rentrer une taille inf�rieure � " + maximum + ".");
						taille = JOptionPane.showInputDialog(null, "Quelle est la taille de votre carte?");
					} else
						choix1 = true;
				}

				pourcentage = JOptionPane.showInputDialog(null,
						"Choisissez une proportion de rochers? (10<pourcentage<40)");
				// Si l'on souhaite partir
				if (pourcentage == null) {
					JOptionPane.showMessageDialog(null,
							"Vous avez d�cidez de vous enfuir ! Levez l'ancre moussaillon !!");
					break;
				}
				while (!choix2) {
					// Si l'on souhaite partir
					if (pourcentage == null) {
						JOptionPane.showMessageDialog(null,
								"Vous avez d�cidez de vous enfuir ! Levez l'ancre moussaillon !!");
						choix2 = true;
					}
					// Si l'on ne rentre rien
					else if (pourcentage.equals("")) {
						JOptionPane.showMessageDialog(null, "Veuillez rentrer un argument valide s'il vous plait.");
						pourcentage = JOptionPane.showInputDialog(null,
								"Choisissez une proportion de rochers?(20 pour l'instant)");
					}
					// Parametre des rochers a choisir entre 10 et 40
					else if (!pourcentage.matches("[0-9]*") || Integer.parseInt(pourcentage) < 10
							|| Integer.parseInt(pourcentage) > 40) {
						JOptionPane.showMessageDialog(null, "Veuillez rentrer un argument valide s'il vous plait.");
						pourcentage = JOptionPane.showInputDialog(null,
								"Choisissez une proportion de rochers? (10<pourcentage<40)");
					} else {
						choix2 = true;
					}
				}

				l = new Jeu(Integer.parseInt(taille), Integer.parseInt(pourcentage));
				bouclePrincipale = true;
			}

			// Si l'utilisateur utilise la carte par d�faut
			else if (choix.equals("2")) {
				l = new Jeu();
				bouclePrincipale = true;
			}
			//Si l'on souhaite jouer contre l'IA
			else if (choix.equals("3")) {
				bouclePrincipale = true;
				new Ia();
			}

			// Si l'utilisateur choisit d'afficher les regles
			else if (choix.equals("4")) {
				JOptionPane.showMessageDialog(null, 
						"Explorateur (tout au clic gauche):\n"
						+ "- Vie: 30\n"
						+ "- D�placements: 4 directions (-1)\n"
						+ "- Actions sp�cifiques: Soulever les rochers (-5)\n\n"
						+ "Voleur (tout au clic gauche):\n"
						+ "- Vie: 30\n"
						+ "- D�placements: 8 directions (-1)\n"
						+ "- Actions sp�cifiques: Voler la cl� ou le tr�sor � un adversaire (-10)\n\n"
						+ "Guerrier (tout au clic gauche):\n"
						+ "- Vie: 40\n"
						+ "- D�placements: 8 directions (-1)\n"
						+ "- Actions sp�cifiques: Frapper les adversaires (avec arme: 8 d�gats, sans arme: 4 d�gats) (-10)\n"
						+ "- Objets: Arme utilisable une fois (� r�cup�rer au bateau autant que l'on veut, au 1er tour le guerrier n'est pas arm�)\n\n"
						+ "Pi�geur (clic gauche et droit):\n"
						+ "- Vie: 30\n"
						+ "- D�placements: 8 directions (-1)\n"
						+ "- Actions sp�cifiques: Poser et enlever un pi�ge nous appartenant (clic droit) (-10 / -5)\n\n"
						+ "Actions communes (tout au clic gauche):\n"
						+ "- Rester sur place (-0)\n"
						+ "- Donner la cl� ou le tr�sor � un personnage ami (-0)\n"
						+ "- Ramasser la cl� ou le tr�sor par terre (-1)\n"
						+ "- Ouvrir le coffre (-0)\n"
						+ "- Retourner au bateau (+10 � chaque tour)\n"
						);
				
				choix = JOptionPane.showInputDialog(null,
						"Que souhaitez vous faire? \n1)Jouer avec une taille que vous choississez \n2)Jouer avec une taille de 10*10\n3)Jouer contre l'IA\n4)Mode d'emploi\n5)Tests\n6)Quitter");
			}
			// Si l'on souhaite acceder aux tests
			else if (choix.equals("5")) {
				bouclePrincipale = true;
				new Tests();
			}
			// Si l'on souhaite arreter de jouer
			else if (choix.equals("6")) {
				JOptionPane.showMessageDialog(null, "Au revoir ! �motic�ne smile");
				bouclePrincipale = true;
			}

			// A changer en fonction du nombre d'option dans le menu
			else if (!choix.matches("[1-6]")) {
				JOptionPane.showMessageDialog(null, "Veuillez rentrer un argument valide s'il vous plait.");
				choix = JOptionPane.showInputDialog(null,
						"Que souhaitez vous faire? \n1)Jouer avec une taille que vous choississez \n2)Jouer avec une taille de 10*10\n3)Jouer contre l'IA\n4)Mode d'emploi\n5)Tests\n6)Quitter");

			}

		}
	}

}