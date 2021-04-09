package dominion.card.base;

import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Bibliothèque (Library)
 * 
 * Piochez jusqu'à ce que vous ayez 7 cartes en main. Chaque carte Action
 * piochée peut être mise de côté. Défaussez les cartes mises de côté lorsque
 * vous avez terminé de piocher.
 */
public class Library extends ActionCard {

	public Library() {
		// Construction de la carte avec ses caractérisques
		super("Library", 5);
	}

	@Override
	public void play(Player p) {
		// On compte le nombre total de carte dont le joueur dispose
		int nbCardDpD = p.totalCards().size();
		// On initialise un compteur de carte pioché
		int cptP = 0;
		// On initialise un boolean pour savoir si le joueur souhaite refaire un
		// tour de pioche
		boolean drawAgain = true;
		// On initialisate une variablie pour stocké la carte pioché
		Card tmpC;
		// On initialise la liste qui contiendra les réponses
		List<String> choix = Arrays.asList("y", "n");
		// On crée la variable de réponse
		String input;
		// On pioche jusqu'as 7 cartes
		while (p.cardsInHand().size() < 7 && drawAgain) {
			// On incrémente le compteur de carte piochée
			cptP++;
			// On récupère la carte pioché
			tmpC = p.drawCard();
			// Si la carte est nulle alors il n'y a rien à piocher
			if (tmpC == null) {
				// On informe le joueur qu'il n'y a plus de carte à pioche
				System.err.println("Il n'y a pas de carte à piocher");
				// On quitte la boucle
				break;
			}
			// On l'ajoute à la main
			p.cardToHand(tmpC);
			// Si c'est une carte action
			if (tmpC.getTypes().contains(CardType.Action)) {
				// On affiche la carte pioché
				System.out.println("Vous avez piocher la carte : "
						+ tmpC.toString());
				// On demande au joueur s'il veux la conserver
				input = p.choose("Voulez vous conserver cette carte Action : "
						+ tmpC.toString() + " ?", choix, false);
				// Si non
				if (input.equals("n")) {
					// On defausse la carte
					p.discardCard(tmpC);
				}
			}
			// Si on a pioché le nombre de carte disponible dans la pioche et la
			// defausse
			if (cptP > nbCardDpD) {
				// On informe le joueur et on lui demande s'il souhaite
				// continuer
				input = p
						.choose("Vous avez déjà pioché toutes les cartes disponible, voulez vous recommencer ?",
								choix, false);
				// S'il veux s'arrêter
				if (!input.equals("n")) {
					// On remet les cartes compté à 0
					cptP = 0;
					// On prévois de quitter la boucle
					drawAgain = false;
				}
			}
		}
	}
}