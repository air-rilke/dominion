package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Mine
 * 
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant
 * jusqu'à 3 Pièces de plus ; ajoutez cette carte à votre main.
 */
public class Mine extends ActionCard {

	public Mine() {
		// Construction de la carte avec ses caractérisques
		super("Mine", 5);
	}

	@Override
	public void play(Player p) {
		// On recupère les cartes trésor en main
		CardList handTre = p.getTreasureCards();
		// On creer une liste pour les cartes qui pourront être récupérée
		CardList tmpTre = new CardList();
		// On creer une variable pour stocké la carte choisi
		Card tmpC;
		// Si le joueur à des cartes Trésor en main
		if (!handTre.isEmpty()) {
			// On demande au joueur quelle carte Trésor de sa main il souhaite
			// écarter
			String input = p
					.chooseCard("Quelle carte trésor voulez-vous écarter ?",
							handTre, false);
			// On écarte cette carte
			tmpC = p.trashCard(input, "hand");
			// On regarde dans les cartes disponibles à l'achat
			for (Card c : p.getGame().availableSupplyCards()) {
				// S'il y a une TreasureCard
				if (c.getTypes().contains(CardType.Treasure)) {
					// Si la carte trouvé à un coup inferieur a la carté ecarté
					// + 3
					if (c.getCost() <= tmpC.getCost() + 3) {
						// On ajoute la carte si elle est achetable
						tmpTre.add(c);
					}
				}
			}
		}
		// On demande au joueur quelle carte il veux récuperer
		String input = p.chooseCard("Quelle carte voulez vous récuperer ?",
				tmpTre, false);
		// On enlève cette carte de la réserve
		p.getGame().removeFromSupply(input);
		// On ajoute cette carte à la main de {@code p}
		p.supplyToHand(input);
	}
}