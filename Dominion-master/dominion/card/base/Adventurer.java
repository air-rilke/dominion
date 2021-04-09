package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Aventurier (Adventurer) Coût : 6
 * 
 * Dévoilez des cartes de votre deck jusqu'à ce que 2 cartes Trésor soient
 * dévoilées. Ajoutez ces cartes Trésor à votre main et défaussez les autres
 * cartes dévoilées.
 */
public class Adventurer extends ActionCard {

	public Adventurer() {
		// Construction de la carte avec ses caractérisques
		super("Adventurer", 6);
	}

	@Override
	public void play(Player p) {
		// On init une carte
		Card tmpC;
		// On compte le nombre total de carte dont le joueur dispose
		int nbCardDpD = p.totalCards().size();
		// On init un compteur de trésor
		int cptT = 0;
		// On initialise un compteur de carte pioché
		int cptP = 0;
		// Tant que le compteur est <= 2 ou que le nombre de carte piochée
		// Est inférieure a {@code nbCardDpD}
		while (cptT < 2 && cptP < nbCardDpD) {
			// On pioche
			tmpC = p.drawCard();
			// Si la carte est nulle alors il n'y a rien à piocher
			if (tmpC == null) {
				// On informe le joueur qu'il n'y a plus de carte à pioche
				System.err.println("Il n'y a pas de carte à piocher");
				// On quitte la boucle
				break;
			}
			// On incrémente le nombre de carte pioché
			cptP++;
			// On dévoile la carte pioché
			System.out.println("Carte piochée : " + tmpC.toString());
			// Si c'est une carte Trésor, on l'ajouta a la main & on incrémente
			// le compteur
			if (tmpC.getTypes().contains(CardType.Treasure)) {
				p.cardToHand(tmpC);
				cptT++;
			} else {
				// Sinon, on la défausse
				p.discardCard(tmpC);
			}
		}
	}
}